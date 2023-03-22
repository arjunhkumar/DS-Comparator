/**
 * 
 */
package in.ac.iitmandi.compl.linescale.suite;

import java.util.LinkedList;
import java.util.List;

import in.ac.iitmandi.compl.common.CommonUtils;
import in.ac.iitmandi.compl.linescale.ds.NonValueLine;
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
	long result;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		long startTime;
		long finishTime;
		if(CommonUtils.validateArgs(args)) {
			startTime = System.currentTimeMillis();
			Dataset ds = CommonUtils.loadDataSet();
			ValueMain mainObj = new ValueMain();
			mainObj.intializeDataPoints(ds);
			mainObj.runExperiments();
			finishTime = System.currentTimeMillis();
			System.out.println(CommonUtils.generateLogMsg("Result: "+mainObj.result));
			System.out.println(CommonUtils.generateLogMsg(
					String.format("Average time for field sum computation:"
							+ " %d ns", (CommonUtils.averageTime/(2*mainObj.vLineList.size())))));
			System.out.println(CommonUtils.generateLogMsg(String.format("Total execution took %d ms", finishTime - startTime)));
		}
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
			long startTime;
			long finishTime;
			startTime = System.nanoTime();
			scale = scaleLine(scale, line);
			sum += getFieldSum1(line);
			sum += getFieldSum2(line);
			finishTime = System.nanoTime();
			CommonUtils.computeAverageTime(finishTime - startTime);
		}
		this.result = scaledList.hashCode()+sum;
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
		double sum = 0;
		int x = line.e.x;
		int y = line.e.y;
		for(int i =0; i<CommonUtils.ITERSIZE;i++) {
			sum += x;
			sum += y;
		}
		return sum;
	}
	
	private double getFieldSum2(ValueLine line) {
		double sum = 0;
		int x = line.getS().getX();
		int y = line.getS().getY();
		for(int i =0; i<CommonUtils.ITERSIZE;i++) {
			sum += x;
			sum += y;
		}
		return sum;
	}


}
