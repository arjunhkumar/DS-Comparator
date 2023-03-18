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
public class ValueTransaction {

	private String TransactionID;
	private CustomerDetails custDetails;
	private PaymentInfo paymentInfo;
	private PaymentInfo feeInfo;
	/**
	 * 
	 */
	public ValueTransaction() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param transactionID
	 * @param custDetails
	 * @param transactionStatus
	 * @param transactionFee
	 */
	public ValueTransaction(String transactionID, CustomerDetails custDetails, PaymentInfo paymentInfo) {
		this.TransactionID = transactionID;
		this.custDetails = custDetails;
		this.paymentInfo = paymentInfo;
		this.feeInfo = new PaymentInfo(paymentInfo.getCustAccountBalance(),paymentInfo.getTransactionDate(),paymentInfo.getTransactionTime(),0,paymentInfo.getTransactionFeeRate(),false);
	}
	
	public double getTransactionAmount() {
		return this.getPaymentInfo().getTransactionAmount();
	}

	public double getCustAcctBalance() {
		return this.getpaymentInfo().getCustAccountBalance();
	}

	public boolean getTransactionStatus() {
		return this.getPaymentInfo().isTransactionStatus();
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
	
	public PaymentInfo createNewPaymentObject(double processingFee) {
		return new PaymentInfo(this.getPaymentInfo().getCustAccountBalance(), this.getPaymentInfo().getTransactionDate(), this.getPaymentInfo().getTransactionTime(), processingFee, this.getPaymentInfo().getTransactionFeeRate(), false); 
		}

	public void resetFeeInfo(PaymentInfo paymentInfo) {
		this.setFeeInfo((PaymentInfo)paymentInfo);
	}

	public void updateTransactionStatus(boolean status) {
		this.setFeeInfo(new PaymentInfo(this.getFeeInfo().getCustAccountBalance(), this.getFeeInfo().getTransactionDate(), this.getFeeInfo().getTransactionTime(), this.getFeeInfo().getTransactionAmount(), this.getPaymentInfo().getTransactionFeeRate(), status));
	}

	public ValueTransaction convertToTransactionObject(JSONResult result) {
		CustomerDetails cDetails = new CustomerDetails(result.getCustomerID(), result.getCustomerDOB(), result.getCustGender(), result.getCustLocation());
		PaymentInfo pi = createValuePaymentInfo(result);
		return new ValueTransaction(result.getTransactionID(), cDetails, pi);
	}
	
	public double computeFieldSum(int n_iterations) {
		return this.getFieldSum(n_iterations);
	}
	
	private PaymentInfo createValuePaymentInfo(JSONResult result) {
		double cAccBalance = 0;
		if(result.getCustAccountBalance() != null && !result.getCustAccountBalance().isEmpty()) {
			cAccBalance =  Double.parseDouble(result.getCustAccountBalance());
		}
		int paymentDate = CommonUtils.formatDateString(result.getTransactionDate());
		int paymentTime = result.getTransactionTime();
		return new PaymentInfo(cAccBalance, paymentDate, paymentTime, result.getTransactionAmount(), 0, false);
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
			sum += this.getPaymentInfo().getTransactionAmount();
////			i3 = System.nanoTime();
			sum += this.getpaymentInfo().getTransactionDate();
////			i4 = System.nanoTime();
			sum += this.getPaymentInfo().getTransactionFeeRate();
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
	public PaymentInfo getpaymentInfo() {
		return paymentInfo;
	}

	/**
	 * @param paymentInfo the paymentInfo to set
	 */
	public void setpaymentInfo(PaymentInfo paymentInfo) {
		this.paymentInfo = paymentInfo;
	}

	/**
	 * @return the PaymentInfo
	 */
	public PaymentInfo getPaymentInfo() {
		return paymentInfo;
	}

	/**
	 * @param PaymentInfo the PaymentInfo to set
	 */
	public void setPaymentInfo(PaymentInfo PaymentInfo) {
		this.paymentInfo = PaymentInfo;
	}

	public PaymentInfo getFeeInfo() {
		return feeInfo;
	}

	public void setFeeInfo(PaymentInfo feeInfo) {
		this.feeInfo = feeInfo;
	}

	

}
