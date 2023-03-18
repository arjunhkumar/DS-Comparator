/**
 * 
 */
package in.ac.iitmandi.compl.transaction.processing.ds;

import java.io.Serializable;

/**
 * @author arjun
 *
 */
public class Dataset implements Serializable {

	private static final long serialVersionUID = 4244727932983628910L;

	public JSONResult results[];
	
	/**
	 * 
	 */
	public Dataset() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the results
	 */
	public JSONResult[] getResults() {
		return results;
	}

	/**
	 * @param results the results to set
	 */
	public void setResults(JSONResult[] results) {
		this.results = results;
	}

}
