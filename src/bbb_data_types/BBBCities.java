package bbb_data_types;

import java.util.LinkedList;

/**
 * BBBCities: LinkedList of BBBCityData objects
 * 	This will be used to access the cities object.
 * @author d4r3llo5
 *
 */
public class BBBCities
{
	private LinkedList<BBBCityData> cities;
	
		/* Constructor */
	/**
	 * Constructor: Initialize this object
	 */
	public BBBCities() {
		cities = new LinkedList<BBBCityData>();
	}
	
		/* mutators */
	/**
	 * addCity: add a city to the linked list
	 * @param city (String): City name
	 * @param state (String): State name
	 * @param url (String): city url name on bbb webpage
	 * @return pass/fail
	 */
	public boolean addCity( String city, String state, String url ) {
		boolean didAdd = false;
		BBBCityData cityData = new BBBCityData(city, state, url);
		
			// Adding to this can throw an exception
		try {
			didAdd = cities.add(cityData);
		} catch (Exception e) {
			System.err.println("Could not add city: " + 	// Error message? 
					city + "\n\tError message: " + e.getMessage());
			throw new RuntimeException(e);			// Kill it!
		}
		return didAdd;
	}

	/**
	 * removeCity: remove a city to the linked list
	 * @param city (String): City name to remove
	 * @return pass/fail
	 */
	public boolean removeCity( String city ) {
		boolean didRemove = false;
		
		// Adding to this can throw an exception
		for (BBBCityData cityData : cities) {
			if ( cityData.getCityName().equals(city) ) {
				try {
					didRemove = cities.remove(cityData);
				} catch (Exception e) {
					System.err.println("Could not remove city: " + 	// Error message? 
							city + "\n\tError message: " + e.getMessage());
					throw new RuntimeException(e);			// Kill it!
				}
				break;
			}
		}
		cities = new LinkedList<BBBCityData>();
				
		return didRemove;
	}

	/**
	 * removeAllCities: remove all cities from the linked list
	 * @return pass/fail
	 */
	public boolean removeAllCities() {
		boolean didRemove = false;
		
		for (BBBCityData cityData : cities) {
			// Adding to this can throw an exception
			try {
				didRemove = cities.remove(cityData);
			} catch (Exception e) {
				System.err.println("Could not remove city: " + 	// Error message? 
						cityData.getCityName() + "\n\tError message: " + e.getMessage());
				throw new RuntimeException(e);			// Kill it!
			}
		}				
		return didRemove;
	}
	
		/* accessor */
	/**
	 * listAllCities: return all of the cities in the list (Iterable)
	 * @return LinkedList<BBBCityData>: Iterable list of all of the cities 
	 */
	public LinkedList<BBBCityData> listAllCities() {				
		return cities;
	}
	
	/**
	 * printAllObjects: print all city data from the linked list
	 * 	 (inherited abstract method)
	 */
	public void printAllObjects() {
		
		for (BBBCityData cityData : cities) {
			// Adding to this can throw an exception
			cityData.printCityInfo();
		}				
	}
}
