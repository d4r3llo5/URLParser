package bbb_data_types;

/**
 * BBBCompanyData:
 * 	Container for the linked list of companies
 * 	This will hold all pertinent information for
 * 	this specific company
 * @author d4r3llo5
 *
 */
public class BBBCompanyData
{
		/* private methods */
	private String _companyName;
	private String _streetName;
	private String _cityName;
	private String _stateName;
	private String _zipCode;
	private String _phoneNum;
	private String _accrDate;
		/* Constructors */
	/**
	 * Default constructor: This class initializes
	 * 	all of the Strings to empty strings
	 */
	public BBBCompanyData() {
		_companyName = "";
		_streetName = "";
		_cityName = "";
		_stateName = "";
		_zipCode = "";
		_phoneNum = "";
		_accrDate = "";
	}
	
	/**
	 * Constructor: This class initializes
	 * 	all of the Strings to empty strings
	 * 
	 * @param name (String): company name
	 * @param street (String): Street address for the company
	 * @param city (String): City name for the company
	 * @param state (String): State name for the company
	 * @param zip (String): zip code for the company
	 * @param phoneNum (String): phone number for the company\
	 * @param accrDate (String): accredited date for the company\
	 */
	public BBBCompanyData( String name, String street, String city, String state, String zip, String phoneNum, String accrDate) {
		_companyName = name;
		_streetName = street;
		_cityName = city;
		_stateName = state;
		_zipCode = zip;
		_phoneNum = phoneNum;
		_accrDate = accrDate;
	}
	
		/* mutators */
	/**
	 * setCompName: set the company name for the object
	 * @param addr (String): street name to set it to
	 */
	public void setCompanyName(String name) {
		_companyName = name;
	}
	
	/**
	 * setAddrName: set the addr name for the object
	 * @param addr (String): street name to set it to
	 */
	public void setAddrName(String addr) {
		_streetName = addr;
	}
	
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
	public void setZipCode(String zip) {
		_zipCode = zip;
	}

	/**
	 * setPhoneNum: set the phone number for the object
	 * @param phone (String): phone number to set it to
	 */
	public void setPhoneNum(String phoneNum) {
		_phoneNum = phoneNum;
	}
	
	/**
	 * setAccrDate: set the date accredited for the object
	 * @param accrDate (String): accredited date to set
	 */
	public void setAccrDate(String accrDate) {
		_accrDate = accrDate;
	}
	
		/* accessors */
	/**
	 * getCompName: return the company name to the caller
	 * @return String: company name
	 */
	public String getCompName() {
		return _companyName;
	}
	
	/**
	 * getAddrName: return the addr name to the caller
	 * @return String: addr name
	 */
	public String getAddrName() {
		return _streetName;
	}
	
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
	 * getZipCode: return the zip name to the caller
	 * @return String: zip code
	 */
	public String getZipCode() {
		return _zipCode;
	}

	/**
	 * getPhoneNum: return the phone number for the company
	 * @return String: phoneNum
	 */
	public String getPhoneNum() {
		return _phoneNum;
	}
	
	/**
	 * getAccrDate: return the accredited date 
	 * @return String: date accredited 
	 */
	public String getAccrDate() {
		return _accrDate;
	}
	
	/**
	 * toStringAddrInfo: string all city data
	 */
	public String toStringCityInfo() {
		if ( _streetName.equals("") )
			return "";
		if ( _cityName.equals("") )
			return "";
		if ( _stateName.equals("") )
			return "";
		if ( _zipCode.equals("") )
			return "";

		return "\t" + this.getAddrName() + "\n\t" + this.getCityName() + ", " + this.getStateName() + "\n\t" + this.getZipCode();				
	}
	
	/**
	 * printCompanyInfo: print out company info
	 */
	public void printCompanyInfo() {
		System.out.println( '\t' + this.getCompName() + "\n\t" + this.getAddrName() + "\n\t" + this.getCityName() + ", " + this.getStateName() + "\n\t" + this.getZipCode());	
	}

	/**
	 * listCompanyInfo: print out company info in a String
	 */
	public String listCompanyInfo() {
		return this.getAddrName() + "\n" + this.getCityName() + ", " + this.getStateName() + "\n" + this.getZipCode();	
	}
}
