/**
 * 
 */
package in.ac.iitmandi.compl.common;


/**
 * @author arjun
 *
 */
public class GlobalStorage {
	
	
	public static int ITERSIZE;
	
	static GlobalStorage instance;
	
	private GlobalStorage() {
	}
	
	public static GlobalStorage getInstance() {
		if(instance == null) {
			instance = new GlobalStorage();
		}
		return instance;
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
