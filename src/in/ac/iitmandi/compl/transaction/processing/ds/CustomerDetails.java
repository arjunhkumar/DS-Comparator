/**
 * 
 */
package in.ac.iitmandi.compl.transaction.processing.ds;

/**
 * @author arjun
 *
 */
public class CustomerDetails {

	private String CustomerID;
	private String CustomerDOB;
	private String CustGender;
	private String CustLocation;
	
	/**
	 * 
	 */
	public CustomerDetails() {
		
	}
	
	
	/**
	 * @param customerID
	 * @param customerDOB
	 * @param custGender
	 * @param custLocation
	 */
	public CustomerDetails(String customerID, String customerDOB, String custGender, String custLocation) {
		CustomerID = customerID;
		CustomerDOB = customerDOB;
		CustGender = custGender;
		CustLocation = custLocation;
	}


	/**
	 * @return the customerID
	 */
	public String getCustomerID() {
		return CustomerID;
	}


	/**
	 * @param customerID the customerID to set
	 */
	public void setCustomerID(String customerID) {
		CustomerID = customerID;
	}


	/**
	 * @return the customerDOB
	 */
	public String getCustomerDOB() {
		return CustomerDOB;
	}


	/**
	 * @param customerDOB the customerDOB to set
	 */
	public void setCustomerDOB(String customerDOB) {
		CustomerDOB = customerDOB;
	}


	/**
	 * @return the custGender
	 */
	public String getCustGender() {
		return CustGender;
	}


	/**
	 * @param custGender the custGender to set
	 */
	public void setCustGender(String custGender) {
		CustGender = custGender;
	}


	/**
	 * @return the custLocation
	 */
	public String getCustLocation() {
		return CustLocation;
	}


	/**
	 * @param custLocation the custLocation to set
	 */
	public void setCustLocation(String custLocation) {
		CustLocation = custLocation;
	}

	
}
