import bbb_data_types.BBBCities;
import bbb_data_types.BBBCityData;
import bbb_data_types.BBBCompanies;
import bbb_data_types.BBBCompanyData;

/**
 * This class will be used to test the functionality
 * 	of the url parser for the BBBScraper
 * 
 *   Contents of this file will be to auto-generate
 *   dummy code for user testing.
 * @author d4r3llo5
 *
 */
public class BBBSandbox {

	/** 
	 * main method for testing the program
	 * @param args
	 */
	public static void main (String[] args) {
		BBBUrlParser urlParser = new BBBUrlParser("http://www.bbb.org/bbb-directory/");
		BBBCities cities;
		
		urlParser.searchUrl("NY", BBBUrlParser.SearchType.SEARCH_CITIES);
		cities = urlParser.getAllCities();
		
		for ( BBBCityData city : cities.listAllCities() ) {
			BBBCompanies companies;
			String cityBusinessUrl;
			cityBusinessUrl = "http://www.bbb.org/" + city.getUrlName() + "/accredited-business-directory/contractors-general/";
			urlParser.setUrlPath(cityBusinessUrl);
			urlParser.loadUrl();
			urlParser.searchUrl(city.getUrlName(), BBBUrlParser.SearchType.SEARCH_COMPANIES);
			companies = urlParser.getAllCompanies();
			city.setCompanies(companies);
		}
		
		for ( BBBCityData city : cities.listAllCities() ) {
			city.printCityInfo();
			for ( BBBCompanyData company : city.getCompanies().listAllCompanies() ) {
				company.printCompanyInfo();
			}
			System.out.println("End of city companies");
		}
		
		return;
	}

}
