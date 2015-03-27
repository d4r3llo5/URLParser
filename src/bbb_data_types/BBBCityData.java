package bbb_data_types;

/**
 * BBBCityData:
 * 	Container for the LinkedList of cities. This class creates objects that contain 
 * 	all pertinent data for a City registered by the BBB
 * @author d4r3llo5
 *
 */
public class BBBCityData
{
		/* private methods */
	private String _urlName;
	private String _cityName;
	private String _stateName;
	
	private BBBCompanies _companies;
	
		/* Constructors */
	/**
	 * Default constructor: This class initializes
	 * 	all of the Strings to empty strings
	 */
	public BBBCityData() {
		_urlName = "";
		_cityName = "";
		_stateName = "";
		_companies = new BBBCompanies();
	}
	
	/**
	 * Constructor: This class initializes
	 * 	all of the Strings to empty strings
	 * 
	 * @param city (String): City name 
	 * @param state (String): State name 
	 * @param url (String): url path to set to 
	 */
	public BBBCityData( String city, String state, String url ) {	
		_cityName = city;
		_stateName = state;
		_urlName = url;
		_companies = new BBBCompanies();
	}
	
		/* mutators */
	/**
	 * setCityName: set the city name for the object
	 * @param city (String): city name to set it to
	 */
	public void setCityName(String city) {
		_cityName = city;
	}
	
	/**
	 * setStateName: set the state name for the object
	 * @param state (String): state name to set it to
	 */
	public void setStateName(String state) {
		_stateName = state;
	}
	
	/**
	 * setUrlName: set the url name for the object
	 * @param url (String): url name to set
	 */
	public void setUrlName(String url) {
		_urlName = url;
	}
	
	/**
	 * setCompaniesList: set the BBBCompanies iterator for this city.
	 * This clears the current list!
	 * @param companies (BBBCompanies): Companies to set the list to
	 */
	public void setCompanies(BBBCompanies companies) {
		// If there are cities attached, remove them all
		if (_companies.listAllCompanies().size() != 0 ) { 
			_companies.removeAllCompanies();
		}
		_companies = companies;
	}
	
		/* accessors */
	/**
	 * getCityName: return the city name to the caller
	 * @return String: city name
	 */
	public String getCityName() {
		return _cityName;
	}
	
	/**
	 * getStateName: return the state name to the caller
	 * @return String: city name
	 */
	public String getStateName() {
		return _stateName;
	}
	
	/**
	 * getUrlName: return the url name to the caller
	 * @return String: url name
	 */
	public String getUrlName() {
		return _urlName;
	}
	
	/**
	 * getCompanies: returns all of the companies as an iterator
	 * @return BBBCompanies: iterator of companies
	 */
	public BBBCompanies getCompanies() {
		return _companies;
	}
	
	/**
	 * printCityInfo: print all city data
	 */
	public void printCityInfo() {
		System.out.println("City found: '" + this.getCityName() + 
					"', '" + this.getStateName() + "' @ '" + this.getUrlName() + "'");				
	}
}