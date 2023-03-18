/**
 * 
 */
package in.ac.iitmandi.compl.linescale.ds;

/**
 * @author arjun
 *
 */
public class NonValueLine {

	private NonValuePoint s;
	private NonValuePoint e;
	
	/**
	 * @param s
	 * @param e
	 */
	public NonValueLine(NonValuePoint s, NonValuePoint e) {
		this.s = s;
		this.e = e;
	}
	/**
	 * @return the s
	 */
	public NonValuePoint getS() {
		return s;
	}
	/**
	 * @param s the s to set
	 */
	public void setS(NonValuePoint s) {
		this.s = s;
	}
	/**
	 * @return the e
	 */
	public NonValuePoint getE() {
		return e;
	}
	/**
	 * @param e the e to set
	 */
	public void setE(NonValuePoint e) {
		this.e = e;
	}
	public NonValueLine scaleLine(int scale) {
		NonValuePoint p1 = createPoint(this.getS().getX(),this.getS().getY(),scale);
		NonValuePoint p2 = createPoint(this.getE().getX(),this.getE().getY(),scale);
		return new NonValueLine(p1, p2);
	}

	private NonValuePoint createPoint(int x, int y, int scale) {
		return new NonValuePoint(x+scale,y+scale);
	}
	
}
