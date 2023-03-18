/**
 * 
 */
package in.ac.iitmandi.compl.transaction.processing.ds;

import java.io.Serializable;

/**
 * @author arjun
 *
 */
public class JSONResult implements Serializable {

	private static final long serialVersionUID = 4212944933784067168L;
	public String TransactionID;
	public String CustomerID;
	public String CustomerDOB;
	public String CustGender;
	public String CustLocation;
	public String CustAccountBalance;
	public String TransactionDate;
	public Integer TransactionTime;
	public Double TransactionAmount;
	
	/**
	 * @return the transactionID
	 */
	public String getTransactionID() {
		return TransactionID;
	}
	/**
	 * @param transactionID the transactionID to set
	 */
	public void setTransactionID(String transactionID) {
		TransactionID = transactionID;
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
	/**
	 * @return the custAccountBalance
	 */
	public String getCustAccountBalance() {
		return CustAccountBalance;
	}
	/**
	 * @param custAccountBalance the custAccountBalance to set
	 */
	public void setCustAccountBalance(String custAccountBalance) {
		CustAccountBalance = custAccountBalance;
	}
	/**
	 * @return the transactionDate
	 */
	public String getTransactionDate() {
		return TransactionDate;
	}
	/**
	 * @param transactionDate the transactionDate to set
	 */
	public void setTransactionDate(String transactionDate) {
		TransactionDate = transactionDate;
	}
	/**
	 * @return the transactionTime
	 */
	public Integer getTransactionTime() {
		return TransactionTime;
	}
	/**
	 * @param transactionTime the transactionTime to set
	 */
	public void setTransactionTime(Integer transactionTime) {
		TransactionTime = transactionTime;
	}
	/**
	 * @return the transactionAmount
	 */
	public Double getTransactionAmount() {
		return TransactionAmount;
	}
	/**
	 * @param transactionAmount the transactionAmount to set
	 */
	public void setTransactionAmount(Double transactionAmount) {
		TransactionAmount = transactionAmount;
	}
	
}
