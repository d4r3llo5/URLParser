import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.io.IOException;
import java.net.URL;

import java.util.LinkedList;
import bbb_data_types.BBBCities;
import bbb_data_types.BBBCityData;
import bbb_data_types.BBBCompanies;
import bbb_data_types.BBBCompanyData;

/**
 * This class will be used to generate a Java URL
 * 	to grab content. The URL will be loaded from a constructor.
 * 
 * 	The URL will then be parsed out in another class
 * 
 * @author d4r3llo5
 *
 */
public class BBBUrlParser
{
	/**
	 * SearchType: What kind of data are we looking for in the URL
	 * 	this will be sent in as a parameter when calling searchUrl  
	 * @author d4r3llo5
	 *
	 */
	public enum SearchType {
		SEARCH_CITIES,				// Looking for cities in URL
		SEARCH_COMPANIES			// Looking for companies in URL
	}
		/* private class variables */
	private String _urlPath; 
	private URL _loadedUrl;
	private BBBCities _cities;
	private BBBCompanies _companies;
	
		/* Constructors */
	/**
	 * Constructor: Default constructor, set _loadedUrl to null.
	 * 	Setting variables to null ensures that we don't try and
	 * 	use them with bad values.
	 */
	public BBBUrlParser() {
		_urlPath = "";
		_loadedUrl = null;
		_cities = new BBBCities();
		_companies = new BBBCompanies();
	}
	
	/**
	 * Constructor: Try and create a new URL, this will cause
	 * 	a runtime exception if it doesn't work, so the instantiating 
	 * 	method needs to catch the exception and handle it appropriately.
	 * @param url (String): String of the URL to try and load.
	 */
	public BBBUrlParser(String url) {
		_urlPath = url;
		loadUrl();
		_cities = new BBBCities();
		_companies = new BBBCompanies();
	}
	
	/**
	 * loadUrl: 'Load' the url, not sure what all loading does.
	 * 	Much like the non-default constructor, this will cause
	 * 	a runtime exception if it doesn't work, so the calling 
	 * 	method needs to catch the exception and handle it appropriately.
	 */
	public void loadUrl() {
		try {											// Try to load the url
			_loadedUrl = new URL(_urlPath);
		} catch (MalformedURLException badUrl) {		// Throw, exit on fail
			System.err.println("Bad url for: " + _urlPath + 	// Good error message?
					"\n\tError message: " + badUrl.getMessage());
			throw new RuntimeException(badUrl);			// Kill it!
		}
	}
	
		/* Class methods */
	/**
	 * searchUrl: search the HTML page for a certain State
	 * 	
	 * @param state (String): state containing cities we're looking for
	 * @param searchKey (SearchType): what kind of search (city or company)
	 * 
	 *  @return (LinkedLIst<BBBCityData>): returns all of the city data
	 */
	public void searchUrl(String searchKey, SearchType search) {
		InputStream urlStream;
		try {								// openStream can throw an exception 
			urlStream = _loadedUrl.openStream();	// Open the input stream 
		} catch (IOException e) {
			System.err.println("Could not open URL stream: " + 	// Error message?
					"\n\tError message: " + e.getMessage());
			throw new RuntimeException(e);			// Kill it!	
		}
		
		/* Now read from the input stream and send it to a buffered read block */
		InputStreamReader openStream = new InputStreamReader(urlStream);
		BufferedReader htmlData = new BufferedReader(openStream);
		this.readBufferedURL(htmlData, searchKey, search);
		
	}
	
		/* private helper methods */
	/*
	 * readBufferedURL: Read data from the HTML buffer 
	 * 	(called from public readURl)
	 * @param htmlData (BufferedHtmlData): The Reader containing the
	 * 	parsed HTML data 
	 * @param searchKey (String): state containing cities we're looking for
	 * @param searchConditions (SearchType): What kind of data are we
	 * 	looking for (comany listings or city listings)
	 */
	private void readBufferedURL(BufferedReader htmlData, String searchKey, SearchType searchConditions) {
		/* While there is still data in the buffer, grab it */
		while (true) {			// Run forever (in-loop if statement to exit)
			String htmlLine = "";
			try {		// Try and grab the next line
				htmlLine = htmlData.readLine();
			} catch (IOException e) {		// Catch generic IOException
				System.err.println("Could not read HTML: " + // Error message?
						"\n\tError message: " + e.getMessage());
				throw new RuntimeException(e);			// Kill it!
			}
			if ( htmlLine == null ) {		// This is our exit condition
				break;
			}
			// Do something with the found line!
			this.parseHTMLLine(htmlLine, searchKey, htmlData, searchConditions);
		}
	}
	
	/*
	 * parseHTMLLine: Parse the HTML line based on search conditions
	 * 
	 * For city the HTML tag containing cities we're looking for is: 
	 * 	<div style="text-align: left">
	 * 	
	 * @param htmlLine (String): HTML line read in by buffer
	 * @param searchKey (String): string containing data we're looking for
	 * @param htmlData (BufferedReader): the Buffered reader to grab more data
	 * 	This is because the best way I found to grab the information is to look for
	 * 	is the tag style/classes
	 * @param searchConditions (SearchType): class defined enum to determine
	 * 	what type of search we're doing.
	 * @return boolean: Pass/Fail (Invalid enum type)
	 */
	private boolean parseHTMLLine(String htmlLine, String searchKey, BufferedReader htmlData, SearchType searchConditions) {
		if (searchConditions == SearchType.SEARCH_CITIES) {
			// Use code tailored to look for cities in the BBB dierectory url
			if ( htmlLine.contains("<div style=\"text-align: left\">") ) {
				// This is the tag that preceeds any city name in the BBB directory
				// We've found a city, get the city then				
				this.getCityInfo(htmlData, searchKey);
			}
		} else if (searchConditions == SearchType.SEARCH_COMPANIES) {
			// Use code tailored to find companies in the BBB Company listings URL
			if ( htmlLine.contains("<div class=\"absince2\">") ) {
				// This is the tag that preceeds any city name in the BBB directory
				// We've found a city, get the city then				
				this.getCompanyInfo(htmlData, searchKey);
			}
			// Look for a page number here
		} else {
			return false;
		}
		return true;
	}
	
	/*
	 * getCityInfo: returns the name of the city found in the HTML page, 
	 * 	given we already found the initial tag above it.
	 * 
	 * 	Parse content from this format:
	 * 		<a href="/city_name/?id=142101">city_name, state_name</a><br 
	 * 
	 * @param htmlData (BufferedReader): the Buffered reader to grab the name from
	 * 	it is probaby guaranteed that the next line is the name.
	 * @param state (String): state containing cities we're looking for
	 * @return
	 */
	private String getCityInfo(BufferedReader htmlData, String state) {
		String htmlLine = "";
		String foundState = "";
		BBBCityStateParser stateParser = new BBBCityStateParser();
		try {		// Try and grab the next line
			htmlLine = htmlData.readLine();
		} catch (IOException e) {		// Catch generic IOException
			System.err.println("Could not read HTML: " + // Error message?
					"\n\tError message: " + e.getMessage());
			throw new RuntimeException(e);			// Kill it!
		}
		
		foundState = stateParser.getStateString(htmlLine);
		if ( foundState.equals(state) ) {
			_cities.addCity(stateParser.getCityStateString(htmlLine), foundState, stateParser.getCityUrlString(htmlLine));
		}
		return htmlLine;
	}
	
	private void getCompanyInfo(BufferedReader htmlData, String city) {
		String htmlLine = "";
		BBBCompanyData compData = new BBBCompanyData();
		int i;
		i = 0;
		while ( i < 4) {
			try {		// Try and grab the next line
				htmlLine = htmlData.readLine();
			} catch (IOException e) {		// Catch generic IOException
				System.err.println("Could not read HTML: " + // Error message?
						"\n\tError message: " + e.getMessage());
				throw new RuntimeException(e);			// Kill it!
			}
			if ( this.setCompanyInfo(i, htmlLine, compData) ) {
				i += 1;
			}
		}
		_companies.addCompany(compData);
	}
	
	private boolean setCompanyInfo( int i, String htmlLine, BBBCompanyData compData  ) {
		BBBCompanyParser compParser = new BBBCompanyParser();
		switch (i) {
			case 0:
				compParser.getCompanyName(htmlLine, compData);
				if ( !(compData.getCompName().equals("")) ) {
					return true;
				}
				break;
			case 1:
				compParser.getCompanyAddr(htmlLine, compData);
				if ( !(compData.toStringCityInfo().equals("")) ) {
					return true;
				}
				break;
			case 2:
				compParser.getCompPhoneNum(htmlLine, compData);
				if (!(compData.getPhoneNum().equals(""))) {
					return true;
				}
				break;
			case 3:
				compParser.getCompAccrDate(htmlLine, compData);
				if (!(compData.getAccrDate().equals(""))) {
					return true;
				}
				break;
			default:
				break;
		}
		return false;
	}
		/* mutator methods */
	/**
	 * setUrlPath: change the URL string
	 * @param url (String): the url to load  
	 */
	public void setUrlPath(String url) {
		_urlPath = url;
	}
	
		/* accessor methods */
	/**
	 * getUrlPath: returns the URL string
	 * @return (String): the url string to load  
	 */
	public String getUrlPath() {
		return _urlPath;
	}
	
	/**
	 * Return all of the cities we've found
	 * @return
	 */
	public BBBCities getAllCities () {
		return _cities;
	}
	
	/**
	 * list all of the cities found by our query
	 */
	public void listAllCities() {
		for (BBBCityData cityData : _cities.listAllCities()) {
			cityData.printCityInfo();
		}
	}

	/**
	 * Return all of the companies we've found
	 * @return
	 */
	public BBBCompanies getAllCompanies () {
		return _companies;
	}
	
	/**
	 * list all of the cities found by our query
	 */
	public void listAllCompanies() {
		for (BBBCompanyData companyData : _companies.listAllCompanies()) {
			companyData.printCompanyInfo();
		}
	}
}
