import java.io.BufferedReader;
import java.io.IOException;

import bbb_data_types.BBBCities;
import bbb_data_types.BBBCityData;

/**
 * This class is used to parse a list of City names from the
 * 	BBB Directory home path "http://www.bbb.org/bbb-directory/"
 * 
 *  The "city url path" is the url that the BBB website uses
 *  	to identify that city for their URL searches
 *  
 *  The city name is the actual city name
 *  
 *  State name is the state name
 *  
 * @author d4r3llo5
 *
 */
public class BBBCityStateParser
{
	/**
	 * getStateString: get the state abbreviation from the URL path
	 * 
	 * 	Format:
	 * 		", state_name</a>"

	 * @param htmlLine (String): the read in HTML
	 * @return State name
	 */
	public String getStateString(String htmlLine) {
		int startIndex = 0;
		int endingIndex = 0;
		
		// Grab the url path
		startIndex = htmlLine.indexOf(", ") + 2;		// Starting index of the City, State
		endingIndex = htmlLine.indexOf("</a>");		// Ending index of the City, State
		return htmlLine.substring(startIndex, endingIndex);
	}
	
	/**
	 * getCityUrlString: get the city url name from the URL path
	 * 
	 * 	Format:
	 * 		"/city_name_url/?id"

	 * @param htmlLine (String): the read in HTML
	 * @return String city url path
	 */
	public String getCityUrlString(String htmlLine) {
		int startIndex = 0;
		int endingIndex = 0;
		
		// Grab the url path
		startIndex = htmlLine.indexOf("/") + 1;		// Starting index of the City, State
		endingIndex = htmlLine.indexOf("/?id");		// Ending index of the City, State
		return htmlLine.substring(startIndex, endingIndex);
	}
	
	/**
	 * getStateString: get the city name from the URL path
	 * 
	 * 	Format:
	 * 		">city_name,"

	 * @param htmlLine (String): the read in HTML
	 * @return city name
	 */
	public String getCityStateString(String htmlLine) {
		int startIndex = 0;
		int endingIndex = 0;
		
		// Grab the City name
		startIndex = htmlLine.indexOf(">") + 1;		// Starting index of the City, State
		endingIndex = htmlLine.indexOf(",");		// Ending index of the City, State
		return htmlLine.substring(startIndex, endingIndex);
	}
	
}
