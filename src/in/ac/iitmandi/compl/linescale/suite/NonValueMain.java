/**
 * 
 */
package in.ac.iitmandi.compl.linescale.suite;

import java.util.LinkedList;
import java.util.List;

import in.ac.iitmandi.compl.common.CommonUtils;
import in.ac.iitmandi.compl.linescale.ds.NonValueLine;
import in.ac.iitmandi.compl.linescale.ds.NonValuePoint;
import in.ac.iitmandi.compl.transaction.processing.ds.Dataset;
import in.ac.iitmandi.compl.transaction.processing.ds.JSONResult;

/**
 * @author arjun
 *
 */
public class NonValueMain {

	List<NonValueLine> vLineList;
	List<NonValueLine> scaledList;
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
			NonValueMain mainObj = new NonValueMain();
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
		for(NonValueLine line: vLineList) {
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
	
	private int scaleLine(int scale, NonValueLine line) {
		NonValueLine newLine = line.scaleLine(scale);
		addToScaledLines(newLine);
		return newLine.getS().getY();
	}
	
	private void addToScaledLines(NonValueLine newLine) {
		if(CommonUtils.isNotNull(scaledList)) {
			scaledList.add(newLine);
		}else {
			scaledList = new LinkedList<>();
			scaledList.add(newLine);
		}
	}

	private void addToLineList(NonValueLine newLine) {
		vLineList.add(newLine);
	}

	public NonValueLine createLine(JSONResult transactionData) {
		return new NonValueLine(createVPoint1(transactionData),createVPoint2(transactionData));
	}

	/**
	 * @param randomGenerator
	 * @return 
	 */
	/**
	 * @param transactionData
	 * @return 
	 */
	private NonValuePoint createVPoint1(JSONResult result) {
		
		int x = CommonUtils.formatDateString(result.getTransactionDate());
		int y = result.getTransactionTime();
		return new NonValuePoint(x, y);
	}
	
	private NonValuePoint createVPoint2(JSONResult result) {
		Double d = result.getTransactionAmount();
		int x = d.intValue();
		Double cAccBalance = 0d;
		if(result.getCustAccountBalance() != null && !result.getCustAccountBalance().isEmpty()) {
			cAccBalance =  Double.parseDouble(result.getCustAccountBalance());
		}
		int y = cAccBalance.intValue();
		return new NonValuePoint(x, y);
	}
	
	private double getFieldSum1(NonValueLine line) {
		double sum = 0;
		for(int i =0; i<CommonUtils.ITERSIZE;i++) {
			sum += line.getE().getX();
			sum += line.getE().getY();
		}
		return sum;
	}

	private double getFieldSum2(NonValueLine line) {
		double sum = 0;
		for(int i =0; i<CommonUtils.ITERSIZE;i++) {
			sum += line.getE().getX();
			sum += line.getE().getY();
		}
		return sum;
	}

}
