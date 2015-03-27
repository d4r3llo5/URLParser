import java.io.BufferedReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import bbb_data_types.BBBCompanyData;

/**
 * This class is used to parse a company's info on the
 * 	BBB Directory city list: 
 * 		"http://www.bbb.org/city_url_path/accredited-business-directory/contractors-general"
 *  
 * @author d4r3llo5
 *
 */
public class BBBCompanyParser
{
	/**
	 * getCompanyName: get the company name from the HTML
	 * 
	 * Format:
	 * 	"--- SPACES --- company_name</a>"
	 * 
	 * @param htmlLine (String): HTML line we got
	 * @param compData (BBBCompanyData): company data structure
	 */
	public void getCompanyName( String htmlLine, BBBCompanyData compData ) {
		String name = "";
		int endChar = 0;
		if ( htmlLine.contains("</a>") ) {
			endChar = htmlLine.indexOf("</a>");
			name = htmlLine.substring(0, endChar);
			compData.setCompanyName(name.trim());
		}
	}
	
	/**
	 * getCompanyAddr: get the company address from the HTML
	 * 	This is all done on one line, each field is preambled
	 * 	by the HTML class "nobr". With this in mind, split
	 * 	the HTML string into arrays at this value, and cherry
	 * 	pick the text out of there. 
	 * 
	 * Format:
	 * 	Address is split by the text nobr (which is an HTML class)
	 * 	Address line one: "\">address_line_one<"
	 * 	City name: "\">Spencerport<
	 *	State name: "\">NY<"
	 *	Zip code: "\">14559<"
	 * 
	 * @param htmlLine (String): HTML line we got
	 * @param compData (BBBCompanyData): object to contain the phone number
	 */
	public void getCompanyAddr( String htmlLine, BBBCompanyData compData ) {
		if ( htmlLine.contains("class=\"mapme\"") ) {		// This class indicates 
															//	we're at an address
			String Data[] = htmlLine.split("nobr");
			compData.setAddrName(this.getCompDataInfo(Data[1]));	// Parse the address
			compData.setCityName(this.getCompDataInfo(Data[2]));	// Parse the city name
			compData.setStateName(this.getCompDataInfo(Data[3]));	// Parse the state
			compData.setZipCode(this.getCompDataInfo(Data[4]));		// Parse the zip code
		}
	}

	/**
	 * getCompPhoneNum: get the company phone number
	 * 
	 * Format: "--- SPACES --- XXX-XXX-XXXX"
	 * 
	 * @param htmlLine (String): HTML line read in
	 * @param compData (BBBCompanyData): object to contain the phone number
	 */
	public void getCompPhoneNum( String htmlLine, BBBCompanyData compData ) {
			/*
			 *  Format for the phone number is in '--- spaces --- XXX-XXX-XXXX'
			 *  	Use regex to figure out if the line we found matches a phone no.
			 *  
			 *  	[0-9]{3}?-> [0-9]: any number, {X}?-> repeat prior expression X times
			 */
		Pattern phoneNum = Pattern.compile("[0-9]{3}?-[0-9]{3}?-[0-9]{4}?");
		// Matcher actually checks the regex vs the string
		Matcher isPhoneNum = phoneNum.matcher(htmlLine.trim());
		if ( isPhoneNum.matches() ) {					// It was the phone number!
			compData.setPhoneNum(htmlLine.trim());
		}
	}
	
	/**
	 * getCompAccrDate: get the accredited date
	 * 
	 * Format: "--- SPACES --- (19|20)XX"
	 * 
	 * @param htmlLine (String): HTML line read in
	 * @param compData (BBBCompanyData): object to contain the phone number
	 */
	public void getCompAccrDate( String htmlLine, BBBCompanyData compData ) {
		/*
		 *  Format for the accredited year is in '--- spaces --- 19|20XX'
		 *  	Use regex to figure out if the line we found matches a phone no.
		 *  
		 *  	(19)|(20)[0-9]{2}?: 
		 *  		(XY)-> group X and Y as one block
		 *  		X|Y-> Look for either X or Y
		 *  		[0-9]-> Any number
		 *  		{X}?-> Repeat prior match X times   
		 */
		Pattern accrDate = Pattern.compile("(19)|(20)[0-9]{2}?");
		Matcher isAccrDate = accrDate.matcher(htmlLine.trim());
		if ( isAccrDate.matches() ) {				// Set the date accredited
			compData.setAccrDate(htmlLine.trim());
		}
	}
	
		/* helper methods */ 
	/**
	 * getCompDataInfo: Get the address line given it has been split
	 * 
	 * @param line (String): parsed address line
	 * @return String: parsed string only containing address info
	 */
	private String getCompDataInfo(String line) {
		int startChar = 2;
		int endChar = 0;
		String name = "";
		endChar = line.indexOf("<");
		name = line.substring(startChar, endChar);
		return name;
	}
}
