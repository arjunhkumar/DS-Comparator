/**
 * 
 */
package in.ac.iitmandi.compl.common;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import com.google.gson.Gson;

import in.ac.iitmandi.compl.transaction.processing.ds.Dataset;

/**
 * @author arjun
 *
 */
public class CommonUtils {

	static CommonUtils commonUtils = null;
	public static long averageTime = 0;
	public static final String JSON_PATH = "./dataset/transactions_formatted.json";
	private static final String PREPENDERRORVAL = "Error : ";
	private static final String PREPENDLOGVAL = "Log : ";
	public static int ITERSIZE;
	/**
	 * Private Constructor for singleton implementation
	 */
	private CommonUtils() {
	}
	
	public static CommonUtils getInstance() {
		if(null==commonUtils) {
			commonUtils = new CommonUtils();
		}
		return commonUtils;
	}

	
	public static boolean isNotNull(String str) {
		return !(null==str || "".equalsIgnoreCase(str));
	}
	
	public static boolean isNotNull(List<?> list) {
		return (null!=list && !list.isEmpty());
	}
	
	public static boolean isNotNull(Map<?, ?> map) {
		return (null!=map && !map.isEmpty());
	}
	
	public static boolean isNotNull(Set<?> set) {
		return (null!=set && !set.isEmpty());
	}
	
	public static boolean isNotNull(Vector<?> vector) {
		return (null!=vector && !vector.isEmpty());
	}

	public static String generateErrorMsg(String msg) {
		return PREPENDERRORVAL + msg;
	}
	
	public static String generateLogMsg(String msg) {
		return PREPENDLOGVAL + msg;
	}
	
	public static int formatDateString(String transactionDate) {
		if(null != transactionDate && "" != transactionDate) {
			String formattedString = transactionDate.replaceAll("/", "");
			return Integer.parseInt(formattedString);
		}
		return 0;
	}

	public static Dataset loadDataSet() {
		Dataset ds = null;
	    Gson gson = new Gson();
	    try (FileReader fReader = new FileReader(JSON_PATH)) {
	    	ds = gson.fromJson(fReader, Dataset.class);
	    } catch (IOException e) {
	    	generateLogMsg(e.getMessage());
			e.printStackTrace();
		} 
	    return ds;
	}

	public static void computeAverageTime(long iterTime) {
		averageTime+=iterTime;
	}

	public static boolean validateArgs(String[] args) {
		if(! (args.length == 1)) {
			System.out.println(generateErrorMsg("No. of arguments is incorrect."));
			System.out.println(generateErrorMsg("Exiting without executing."));
			return false;
		}
		String iterSize = args[0];
		int iterVal = Integer.parseInt(iterSize);
		CommonUtils.ITERSIZE = iterVal;
		return true;
	}

	/**
	 * @param args
	 */
	public static void setIterVal(String[] args) {
		if(args.length > 0) {
			String iterSize = args[0];
			int iterVal = Integer.parseInt(iterSize);
			ITERSIZE = iterVal;
		}
	}
}
