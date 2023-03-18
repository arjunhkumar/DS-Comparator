/**
 * 
 */
package in.ac.iitmandi.compl.linescale.suite;

import java.util.LinkedList;
import java.util.List;

import in.ac.iitmandi.compl.common.CommonUtils;
import in.ac.iitmandi.compl.common.GlobalStorage;
import in.ac.iitmandi.compl.linescale.ds.ValueLine;
import in.ac.iitmandi.compl.linescale.ds.ValuePoint;
import in.ac.iitmandi.compl.transaction.processing.ds.Dataset;
import in.ac.iitmandi.compl.transaction.processing.ds.JSONResult;

/**
 * @author arjun
 *
 */
public class ValueMain {

	List<ValueLine> vLineList;
	List<ValueLine> scaledList;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		long startTime;
		long finishTime;
		startTime = System.currentTimeMillis();
		Dataset ds = CommonUtils.loadDataSet();
		ValueMain mainObj = new ValueMain();
		mainObj.intializeDataPoints(ds);
		mainObj.runExperiments();
		finishTime = System.currentTimeMillis();
		System.out.println(CommonUtils.generateLogMsg(String.format("Total execution took %d ms", finishTime - startTime)));
	}

	

	private void intializeDataPoints(Dataset ds) {
		vLineList = new LinkedList<>();
		if(null != ds && null != ds.getResults() && ds.getResults().length > 0) {
			for (JSONResult transactionData : ds.getResults()) {
				addToLineList(this.createLine(transactionData));
			}
		}
	}

	private void runExperiments() {
		int scale = 1;
		int sum = 0;
		for(ValueLine line: vLineList) {
			scale = scaleLine(scale, line);
			sum += getFieldSum1(line);
			sum += getFieldSum2(line);
		}
		System.out.println(CommonUtils.generateLogMsg("Size: "+vLineList.size()));
		System.out.println(CommonUtils.generateLogMsg("Expt completed. Result: "+(scaledList.hashCode()+sum)));
	
	}
	
	private int scaleLine(int scale, ValueLine line) {
		ValueLine newLine = line.scaleLine(scale);
		addToScaledLines(newLine);
		return newLine.getS().getY();
	}
	
	private void addToScaledLines(ValueLine newLine) {
		if(CommonUtils.isNotNull(scaledList)) {
			scaledList.add(newLine);
		}else {
			scaledList = new LinkedList<>();
			scaledList.add(newLine);
		}
	}

	private void addToLineList(ValueLine newLine) {
		vLineList.add(newLine);
	}

	public ValueLine createLine(JSONResult transactionData) {
		return new ValueLine(createVPoint1(transactionData),createVPoint2(transactionData));
	}

	/**
	 * @param transactionData
	 * @return 
	 */
	private ValuePoint createVPoint1(JSONResult result) {
		
		int x = CommonUtils.formatDateString(result.getTransactionDate());
		int y = result.getTransactionTime();
		return new ValuePoint(x, y);
	}
	
	private ValuePoint createVPoint2(JSONResult result) {
		Double d = result.getTransactionAmount();
		int x = d.intValue();
		Double cAccBalance = 0d;
		if(result.getCustAccountBalance() != null && !result.getCustAccountBalance().isEmpty()) {
			cAccBalance =  Double.parseDouble(result.getCustAccountBalance());
		}
		int y = cAccBalance.intValue();
		return new ValuePoint(x, y);
	}
	
	private double getFieldSum1(ValueLine line) {
		long startTime;
		long finishTime;
		startTime = System.nanoTime();
		double sum = 0;
		for(int i =0; i<GlobalStorage.ITERSIZE;i++) {
//			i1 = System.nanoTime();
			sum += line.getE().getX();
//			i2 = System.nanoTime();
			sum += line.getE().getY();
////			i3 = System.nanoTime();
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
	
	private double getFieldSum2(ValueLine line) {
		long startTime;
		long finishTime;
		startTime = System.nanoTime();
		double sum = 0;
		for(int i =0; i<GlobalStorage.ITERSIZE;i++) {
//			i1 = System.nanoTime();
			sum += line.getS().getX();
//			i2 = System.nanoTime();
			sum += line.getS().getY();
////			i3 = System.nanoTime();
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


}
