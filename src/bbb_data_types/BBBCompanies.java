package bbb_data_types;
import java.util.LinkedList;

/**
 * BBBCities: LinkedList of BBBcompanyData objects
 * 	This will be used to access the companies object.
 * @author d4r3llo5
 *
 */
public class BBBCompanies
{
	private LinkedList<BBBCompanyData> companies;
	
		/* Constructor */
	/**
	 * Constructor: Initialize this object
	 */
	public BBBCompanies() {
		companies = new LinkedList<BBBCompanyData>();
	}
	
		/* mutators */
	/**
	 * addCompany: add a company to the linked list
	 * @param company (String): company name
	 * @param state (String): State name
	 * @param url (String): company url name on bbb webpage
	 * @return pass/fail
	 */
	public boolean addCompany( BBBCompanyData company ) {
		boolean didAdd = false;
		
			// Adding to this can throw an exception
		try {
			didAdd = companies.add(company);
		} catch (Exception e) {
			System.err.println("Could not add company: " + 	// Error message? 
					company.getCompName() + "\n\tError message: " + e.getMessage());
			throw new RuntimeException(e);			// Kill it!
		}
		return didAdd;
	}

	/**
	 * removecompany: remove a company to the linked list
	 * @param company (String): Company name to remove
	 * @return pass/fail
	 */
	public boolean removeCompany( String company ) {
		boolean didRemove = false;
		
		// Adding to this can throw an exception
		for (BBBCompanyData companyData : companies) {
			if ( companyData.getCompName().equals(company) ) {
				try {
					didRemove = companies.remove(companyData);
				} catch (Exception e) {
					System.err.println("Could not remove company: " + 	// Error message? 
							company + "\n\tError message: " + e.getMessage());
					throw new RuntimeException(e);			// Kill it!
				}
				break;
			}
		}
		companies = new LinkedList<BBBCompanyData>();
				
		return didRemove;
	}

	/**
	 * removeAllCompanies: remove all companies from the linked list
	 * @return pass/fail
	 */
	public boolean removeAllCompanies() {
		boolean didRemove = false;
		
		for (BBBCompanyData companyData : companies) {
			// Adding to this can throw an exception
			try {
				didRemove = companies.remove(companyData);
			} catch (Exception e) {
				System.err.println("Could not remove company: " + 	// Error message? 
						companyData.getCompName() + "\n\tError message: " + e.getMessage());
				throw new RuntimeException(e);			// Kill it!
			}
		}				
		return didRemove;
	}
	
		/* accessor */
	/**
	 * listAllCities: return all of the companies in the list (Iterable)
	 * @return LinkedList<BBBCompanyData>: Iterable list of all of the cities 
	 */
	public LinkedList<BBBCompanyData> listAllCompanies() {				
		return companies;
	}
	
	/**
	 * printAllObjects: print all company data from the linked list
	 * 	 (inherited abstract method)
	 */
	public void printAllObjects() {		
		for (BBBCompanyData companyData : companies) {
			// Adding to this can throw an exception
			companyData.printCompanyInfo();
		}				
	}
}
