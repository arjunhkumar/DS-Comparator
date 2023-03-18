/**
 * 
 */
package in.ac.iitmandi.compl.transaction.processing;

import java.util.ArrayList;
import java.util.List;

import in.ac.iitmandi.compl.common.CommonUtils;
import in.ac.iitmandi.compl.common.GlobalStorage;
import in.ac.iitmandi.compl.transaction.processing.ds.Dataset;
import in.ac.iitmandi.compl.transaction.processing.ds.JSONResult;



/**
 * @author arjun
 *
 */
public class ValueMain{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		long startTime;
		long finishTime;
		startTime = System.currentTimeMillis();
		ValueMain mainObj = new ValueMain();
		if(mainObj.validateArgs(args)) {
			startTime = System.currentTimeMillis();
			Dataset ds = CommonUtils.loadDataSet();
			mainObj.executeAnalysis(ds);
			System.out.println(CommonUtils.generateLogMsg(
					String.format("Average time for field sum computation:"
							+ " %d ns", (CommonUtils.averageTime/GlobalStorage.ITERSIZE))));
			finishTime = System.currentTimeMillis();
			System.out.println(CommonUtils.generateLogMsg(String.format("Total execution took %d ms", finishTime - startTime)));
		}
	}
	
	public void executeAnalysis(Dataset ds) {
		long startTime;
		long finishTime;
		List<ValueTransaction> valueList = convertToTransaction(ds, new ValueTransaction());
		startTime = System.currentTimeMillis();
		double sum =0;
		for(int i = 1; i<=GlobalStorage.ITERSIZE; i++) {
			sum += processTransactions(valueList,i);
		}
		System.out.println("Final value: "+sum);
		finishTime = System.currentTimeMillis();
		System.out.println(CommonUtils.generateLogMsg(String.format("Analysis execution took %d ms", finishTime - startTime)));
	}

	
	 boolean validateArgs(String[] args) {
		if(! (args.length == 1)) {
			System.out.println(CommonUtils.generateErrorMsg("No. of arguments is incorrect."));
			System.out.println(CommonUtils.generateErrorMsg("Exiting without executing."));
			return false;
		}
		String iterSize = args[0];
		int iterVal = Integer.parseInt(iterSize);
		GlobalStorage.ITERSIZE = iterVal;
		return true;
	}
	
	List<ValueTransaction> convertToTransaction(Dataset ds,ValueTransaction transaction) {
		long startTime;
		long finishTime;
		startTime = System.currentTimeMillis();
		List<ValueTransaction> transactionList = null;
		if(null != ds && null != ds.getResults() && ds.getResults().length > 0) {
			transactionList = new ArrayList<>();
			for (JSONResult transactionData : ds.getResults()) {
				ValueTransaction nonValueTransaction = transaction.convertToTransactionObject(transactionData);
				transactionList.add(nonValueTransaction);
			}
		}
		finishTime = System.currentTimeMillis();
		System.out.println(CommonUtils.generateLogMsg(
				String.format("Dataset conversion took "
						+ "%d ms", finishTime - startTime)));
		return transactionList;
	}
	
	 double processTransactions(List<ValueTransaction> valueList, int divident) {
		double blackHole;
		double avgTransactionAmt = computeAverageTransactionAmount(valueList)/divident;
//		System.out.println(CommonUtils.generateLogMsg("Average Transaction Amount: "+avgTransactionAmt));
//		List<AbstractTransaction> workList = new ArrayList<>(valueList);
		double avgProcessingFee = computeAverageProcessingFee(valueList,divident/GlobalStorage.ITERSIZE);
//		avgProcessingFee += computeAverageProcessingFee(valueList,(divident*2)/GlobalStorage.ITERSIZE);
//		System.out.println(CommonUtils.generateLogMsg("Average processing fee: "+avgProcessingFee));
//		List<AbstractTransaction> workList1 = new ArrayList<>(workList);
		int numberOfCustomers = computeNumberOfCustomers(updateTransactions(valueList,divident/GlobalStorage.ITERSIZE));
//		numberOfCustomers += computeNumberOfCustomers(updateTransactions(valueList,(divident*2)/GlobalStorage.ITERSIZE));
//		System.out.println(CommonUtils.generateLogMsg("No. of transactions successfull are: "+numberOfCustomers));
		double accessVal = increasePrimitiveAccessOperation(valueList,10000);
		blackHole =  avgTransactionAmt + avgProcessingFee +numberOfCustomers+ accessVal;
//		blackHole = accessVal;
		return blackHole;
	}
	
	 double computeAverageTransactionAmount(List<ValueTransaction> valueList) {
		long startTime;
		long finishTime;
		startTime = System.currentTimeMillis();
		if(null != valueList && !valueList.isEmpty()) {
			double sum = 0;
			for(ValueTransaction valueTransaction : valueList) {
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
	
	 double computeAverageProcessingFee(List<ValueTransaction> valueList, float rate) {
		long startTime;
		long finishTime;
		startTime = System.currentTimeMillis();
		if(null != valueList && !valueList.isEmpty()) {
			double sum = 0;
			for(ValueTransaction valueTransaction : valueList) {
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
	
	 List<ValueTransaction> updateTransactions(List<ValueTransaction> workList, int rate) {
		long startTime;
		long finishTime;
		long i0 = 0;
		long i1 = 0;
		long i2 = 0;
		long i3 = 0;
		startTime = System.currentTimeMillis();
		if(null != workList && !workList.isEmpty()) {
			for(ValueTransaction valueTransaction : workList) {
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

	 int computeNumberOfCustomers(List<ValueTransaction> updateTransactions) {
		long startTime;
		long finishTime;
		startTime = System.currentTimeMillis();
		if(null != updateTransactions && !updateTransactions.isEmpty()) {
			int noOfCustomers = 0;
			for(ValueTransaction valueTransaction : updateTransactions) {
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
	
	 double increasePrimitiveAccessOperation(List<ValueTransaction> valueList,int randomInt) {
		long startTime;
		long finishTime;
//		long i1 =0;
//		long i2 =0;
		startTime = System.currentTimeMillis();
		if(null != valueList && !valueList.isEmpty()) {
			double sum = 0;
			for(ValueTransaction valueTransaction : valueList) {
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
