/**
 * 
 */
package in.ac.iitmandi.compl.transaction.processing;

import in.ac.iitmandi.compl.common.CommonUtils;
import in.ac.iitmandi.compl.transaction.processing.ds.CustomerDetails;
import in.ac.iitmandi.compl.transaction.processing.ds.JSONResult;

/**
 * @author arjun
 *
 */
public class NonValueTransaction {

	private String TransactionID;
	private CustomerDetails custDetails;
	private NonValuePaymentInfo paymentInfo;
	private NonValuePaymentInfo feeInfo;
	/**
	 * 
	 */
	public NonValueTransaction() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param transactionID
	 * @param custDetails
	 * @param transactionStatus
	 * @param transactionFee
	 */
	public NonValueTransaction(String transactionID, CustomerDetails custDetails, NonValuePaymentInfo paymentInfo) {
		this.TransactionID = transactionID;
		this.custDetails = custDetails;
		this.paymentInfo = paymentInfo;
		this.feeInfo = new NonValuePaymentInfo(paymentInfo.getCustAccountBalance(),paymentInfo.getTransactionDate(),paymentInfo.getTransactionTime(),0,paymentInfo.getTransactionFeeRate(),false);
	}
	
	public double getTransactionAmount() {
		return this.getNonValuePaymentInfo().getTransactionAmount();
	}

	public double getCustAcctBalance() {
		return this.getpaymentInfo().getCustAccountBalance();
	}

	public boolean getTransactionStatus() {
		return this.getNonValuePaymentInfo().isTransactionStatus();
	}

	protected void computeAverageTime(long iterTime) {
		CommonUtils.averageTime+=iterTime;
	}
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
	 * @return the custDetails
	 */
	public CustomerDetails getCustDetails() {
		return custDetails;
	}

	/**
	 * @param custDetails the custDetails to set
	 */
	public void setCustDetails(CustomerDetails custDetails) {
		this.custDetails = custDetails;
	}
	
	public NonValuePaymentInfo createNewPaymentObject(double processingFee) {
		return new NonValuePaymentInfo(this.getNonValuePaymentInfo().getCustAccountBalance(), this.getNonValuePaymentInfo().getTransactionDate(), this.getNonValuePaymentInfo().getTransactionTime(), processingFee, this.getNonValuePaymentInfo().getTransactionFeeRate(), false); 
		}

	public void resetFeeInfo(NonValuePaymentInfo paymentInfo) {
		this.setFeeInfo((NonValuePaymentInfo)paymentInfo);
	}

	public void updateTransactionStatus(boolean status) {
		this.setFeeInfo(new NonValuePaymentInfo(this.getFeeInfo().getCustAccountBalance(), this.getFeeInfo().getTransactionDate(), this.getFeeInfo().getTransactionTime(), this.getFeeInfo().getTransactionAmount(), this.getNonValuePaymentInfo().getTransactionFeeRate(), status));
	}

	public NonValueTransaction convertToTransactionObject(JSONResult result) {
		CustomerDetails cDetails = new CustomerDetails(result.getCustomerID(), result.getCustomerDOB(), result.getCustGender(), result.getCustLocation());
		NonValuePaymentInfo pi = createValueNonValuePaymentInfo(result);
		return new NonValueTransaction(result.getTransactionID(), cDetails, pi);
	}
	
	public double computeFieldSum(int n_iterations) {
		return this.getFieldSum(n_iterations);
	}
	
	private NonValuePaymentInfo createValueNonValuePaymentInfo(JSONResult result) {
		double cAccBalance = 0;
		if(result.getCustAccountBalance() != null && !result.getCustAccountBalance().isEmpty()) {
			cAccBalance =  Double.parseDouble(result.getCustAccountBalance());
		}
		int paymentDate = CommonUtils.formatDateString(result.getTransactionDate());
		int paymentTime = result.getTransactionTime();
		return new NonValuePaymentInfo(cAccBalance, paymentDate, paymentTime, result.getTransactionAmount(), 0, false);
	}
	
	private double getFieldSum(int iterVal) {
		long startTime;
		long finishTime;
		startTime = System.nanoTime();
		double sum = 0;
		for(int i =0; i<iterVal;i++) {
//			i1 = System.nanoTime();
			sum += this.getpaymentInfo().getCustAccountBalance();
//			i2 = System.nanoTime();
			sum += this.getNonValuePaymentInfo().getTransactionAmount();
////			i3 = System.nanoTime();
			sum += this.getpaymentInfo().getTransactionDate();
////			i4 = System.nanoTime();
			sum += this.getNonValuePaymentInfo().getTransactionFeeRate();
////			i5 = System.nanoTime();
			sum += this.getpaymentInfo().getTransactionTime();
//			i6 = System.nanoTime();
		}
		finishTime = System.nanoTime();
		CommonUtils.computeAverageTime(finishTime - startTime);
//		System.out.println(CommonUtils.generateLogMsg(
//				 String.format("I0 "
//						+ "%d ms", i2 - i1)));
//		System.out.println(CommonUtils.generateLogMsg(
//				String.format("I1 "
//						+ "%d ms", i3 - i2)));
//		System.out.println(CommonUtils.generateLogMsg(
//				String.format("I2 "
//						+ "%d ms", i4 - i3)));
//		System.out.println(CommonUtils.generateLogMsg(
//				String.format("I3 "
//						+ "%d ms", i5 - i4)));
//		System.out.println(CommonUtils.generateLogMsg(
//				String.format("I4 "
//						+ "%d ms", i6 - i5)));
		System.out.println(CommonUtils.generateLogMsg(
				String.format("Field sum computation took "
						+ "%d ns", finishTime - startTime)));
		return sum;
	}

	/**
	 * @return the paymentInfo
	 */
	public NonValuePaymentInfo getpaymentInfo() {
		return paymentInfo;
	}

	/**
	 * @param paymentInfo the paymentInfo to set
	 */
	public void setpaymentInfo(NonValuePaymentInfo paymentInfo) {
		this.paymentInfo = paymentInfo;
	}

	/**
	 * @return the NonValuePaymentInfo
	 */
	public NonValuePaymentInfo getNonValuePaymentInfo() {
		return paymentInfo;
	}

	/**
	 * @param NonValuePaymentInfo the NonValuePaymentInfo to set
	 */
	public void setNonValuePaymentInfo(NonValuePaymentInfo NonValuePaymentInfo) {
		this.paymentInfo = NonValuePaymentInfo;
	}

	public NonValuePaymentInfo getFeeInfo() {
		return feeInfo;
	}

	public void setFeeInfo(NonValuePaymentInfo feeInfo) {
		this.feeInfo = feeInfo;
	}

	

}
