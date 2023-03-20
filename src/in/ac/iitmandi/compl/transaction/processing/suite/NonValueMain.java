/**
 * 
 */
package in.ac.iitmandi.compl.transaction.processing.suite;

import java.util.ArrayList;
import java.util.List;

import in.ac.iitmandi.compl.common.CommonUtils;
import in.ac.iitmandi.compl.transaction.processing.ds.Dataset;
import in.ac.iitmandi.compl.transaction.processing.ds.JSONResult;
import in.ac.iitmandi.compl.transaction.processing.ds.NonValueTransaction;



/**
 * @author arjun
 *
 */
public class NonValueMain{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		long startTime;
		long finishTime;
		startTime = System.currentTimeMillis();
		NonValueMain mainObj = new NonValueMain();
		if(CommonUtils.validateArgs(args)) {
			startTime = System.currentTimeMillis();
			Dataset ds = CommonUtils.loadDataSet();
			mainObj.executeAnalysis(ds);
			System.out.println(CommonUtils.generateLogMsg(
					String.format("Average time for field sum computation:"
							+ " %d ns", (CommonUtils.averageTime/CommonUtils.ITERSIZE))));
			finishTime = System.currentTimeMillis();
			System.out.println(CommonUtils.generateLogMsg(String.format("Total execution took %d ms", finishTime - startTime)));
		}
	}
	
	public void executeAnalysis(Dataset ds) {
		long startTime;
		long finishTime;
		List<NonValueTransaction> valueList = convertToTransaction(ds, new NonValueTransaction());
		startTime = System.currentTimeMillis();
		double sum =0;
//		for(int i = 1; i<=GlobalStorage.ITERSIZE; i++) {
			sum += processTransactions(valueList,1);
//		}
		System.out.println("Final value: "+sum);
		finishTime = System.currentTimeMillis();
		System.out.println(CommonUtils.generateLogMsg(String.format("Analysis execution took %d ms", finishTime - startTime)));
	}

	
	 List<NonValueTransaction> convertToTransaction(Dataset ds,NonValueTransaction transaction) {
		long startTime;
		long finishTime;
		startTime = System.currentTimeMillis();
		List<NonValueTransaction> transactionList = null;
		if(null != ds && null != ds.getResults() && ds.getResults().length > 0) {
			transactionList = new ArrayList<>();
			for (JSONResult transactionData : ds.getResults()) {
				NonValueTransaction nonNonValueTransaction = transaction.convertToTransactionObject(transactionData);
				transactionList.add(nonNonValueTransaction);
			}
		}
		finishTime = System.currentTimeMillis();
		System.out.println(CommonUtils.generateLogMsg(
				String.format("Dataset conversion took "
						+ "%d ms", finishTime - startTime)));
		return transactionList;
	}
	
	 double processTransactions(List<NonValueTransaction> valueList, int divident) {
		double blackHole;
		double avgTransactionAmt = computeAverageTransactionAmount(valueList)/CommonUtils.ITERSIZE;
//		System.out.println(CommonUtils.generateLogMsg("Average Transaction Amount: "+avgTransactionAmt));
//		List<AbstractTransaction> workList = new ArrayList<>(valueList);
		double avgProcessingFee = computeAverageProcessingFee(valueList,CommonUtils.ITERSIZE);
//		avgProcessingFee += computeAverageProcessingFee(valueList,(divident*2)/GlobalStorage.ITERSIZE);
//		System.out.println(CommonUtils.generateLogMsg("Average processing fee: "+avgProcessingFee));
//		List<AbstractTransaction> workList1 = new ArrayList<>(workList);
		int numberOfCustomers = computeNumberOfCustomers(updateTransactions(valueList,CommonUtils.ITERSIZE));
//		numberOfCustomers += computeNumberOfCustomers(updateTransactions(valueList,(divident*2)/GlobalStorage.ITERSIZE));
//		System.out.println(CommonUtils.generateLogMsg("No. of transactions successfull are: "+numberOfCustomers));
		double accessVal = increasePrimitiveAccessOperation(valueList,CommonUtils.ITERSIZE);
		blackHole =  avgTransactionAmt + avgProcessingFee +numberOfCustomers+ accessVal;
//		blackHole = accessVal;
		return blackHole;
	}
	
	 double computeAverageTransactionAmount(List<NonValueTransaction> valueList) {
		long startTime;
		long finishTime;
		startTime = System.currentTimeMillis();
		if(null != valueList && !valueList.isEmpty()) {
			double sum = 0;
			for(NonValueTransaction valueTransaction : valueList) {
				double transactionAmt = valueTransaction.getTransactionAmount();
				sum += transactionAmt;
			}
			finishTime = System.currentTimeMillis();
			System.out.println(CommonUtils.generateLogMsg(
					String.format("Average Transaction Amount computation took "
							+ "%d ms", finishTime - startTime)));
			return sum/valueList.size();
		}
		
		return 0;
	}
	
	 double computeAverageProcessingFee(List<NonValueTransaction> valueList, float rate) {
		long startTime;
		long finishTime;
		startTime = System.currentTimeMillis();
		if(null != valueList && !valueList.isEmpty()) {
			double sum = 0;
			for(NonValueTransaction valueTransaction : valueList) {
				double transactionAmt = valueTransaction.getTransactionAmount();
				double processingFee = transactionAmt*rate;
				sum+=processingFee;
			}
			finishTime = System.currentTimeMillis();
			System.out.println(CommonUtils.generateLogMsg(
					String.format("Average Processing Fee computation took "
							+ "%d ms", finishTime - startTime)));
			return sum/valueList.size();
		}
		return 0;
	}
	
	 List<NonValueTransaction> updateTransactions(List<NonValueTransaction> workList, int rate) {
		long startTime;
		long finishTime;
		long i0 = 0;
		long i1 = 0;
		long i2 = 0;
		long i3 = 0;
		startTime = System.currentTimeMillis();
		if(null != workList && !workList.isEmpty()) {
			for(NonValueTransaction valueTransaction : workList) {
				i0 = System.currentTimeMillis();
				double transactionAmt = valueTransaction.getTransactionAmount();
				double processingFee = transactionAmt*rate;
				i1 = System.currentTimeMillis();
//				PaymentInfo pInfo = new PaymentInfo(valueTransaction.getPaymentInfo().getCustAccountBalance(),valueTransaction.getPaymentInfo().getTransactionDate(),valueTransaction.getPaymentInfo().getTransactionTime(),processingFee,valueTransaction.getPaymentInfo().getTransactionFeeRate(),false);
//				valueTransaction.setFeeInfo(pInfo);
				valueTransaction.resetFeeInfo(valueTransaction.createNewPaymentObject(processingFee));
				i2 = System.currentTimeMillis();
				if(valueTransaction.getCustAcctBalance() >= processingFee) {
//					PaymentInfo updatedPInfo = new PaymentInfo(valueTransaction.getPaymentInfo().getCustAccountBalance(),valueTransaction.getPaymentInfo().getTransactionDate(),valueTransaction.getPaymentInfo().getTransactionTime(),processingFee,valueTransaction.getPaymentInfo().getTransactionFeeRate(),true);
//					valueTransaction.setFeeInfo(updatedPInfo);
					valueTransaction.updateTransactionStatus(true);
				}
				i3 = System.currentTimeMillis();
			}
		}
		finishTime = System.currentTimeMillis();
		System.out.println(CommonUtils.generateLogMsg(
				String.format("I1 took "
						+ "%d ms", i1 - i0)));
		System.out.println(CommonUtils.generateLogMsg(
				String.format("I2 took "
						+ "%d ms", i2 - i1)));
		System.out.println(CommonUtils.generateLogMsg(
				String.format("I3 took "
						+ "%d ms", i3 - i2)));
		System.out.println(CommonUtils.generateLogMsg(
				String.format("Updating transactions took "
						+ "%d ms", finishTime - startTime)));
		return workList;
	}

	 int computeNumberOfCustomers(List<NonValueTransaction> updateTransactions) {
		long startTime;
		long finishTime;
		startTime = System.currentTimeMillis();
		if(null != updateTransactions && !updateTransactions.isEmpty()) {
			int noOfCustomers = 0;
			for(NonValueTransaction valueTransaction : updateTransactions) {
				if(valueTransaction.getTransactionStatus()) {
					noOfCustomers++;
				}
			}
			finishTime = System.currentTimeMillis();
			System.out.println(CommonUtils.generateLogMsg(
					String.format("Customer computation took "
							+ "%d ms", finishTime - startTime)));
			return noOfCustomers;
		}
		return 0;
	}
	
	 double increasePrimitiveAccessOperation(List<NonValueTransaction> valueList,int randomInt) {
		long startTime;
		long finishTime;
//		long i1 =0;
//		long i2 =0;
		startTime = System.currentTimeMillis();
		if(null != valueList && !valueList.isEmpty()) {
			double sum = 0;
			for(NonValueTransaction valueTransaction : valueList) {
//				i1 = System.currentTimeMillis();
				double transactionAmt = valueTransaction.computeFieldSum(randomInt);
				sum += transactionAmt;
//				i2 = System.currentTimeMillis();
			}
			finishTime = System.currentTimeMillis();
//			System.out.println(CommonUtils.generateLogMsg(
//					String.format("Field sum computation at parent took "
//							+ "%d ms", i2 - i1)));
			System.out.println(CommonUtils.generateLogMsg(
					String.format("Primitive access operation took "
							+ "%d ms", finishTime - startTime)));
			return sum/valueList.size();
		}
		return 0;
	}
	
}
