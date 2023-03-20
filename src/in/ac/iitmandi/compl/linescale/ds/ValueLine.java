/**
 * 
 */
package in.ac.iitmandi.compl.linescale.ds;

/**
 * @author arjun
 *
 */
public class ValueLine {

	public ValuePoint s;
	public ValuePoint e;
	
	/**
	 * @param s
	 * @param e
	 */
	public ValueLine(ValuePoint s, ValuePoint e) {
		this.s = s;
		this.e = e;
	}
	/**
	 * @return the s
	 */
	public ValuePoint getS() {
		return s;
	}
	/**
	 * @param s the s to set
	 */
	public void setS(ValuePoint s) {
		this.s = s;
	}
	/**
	 * @return the e
	 */
	public ValuePoint getE() {
		return e;
	}
	/**
	 * @param e the e to set
	 */
	public void setE(ValuePoint e) {
		this.e = e;
	}
	
	public ValueLine scaleLine(int scale) {
		ValuePoint p1 = createPoint(this.getS().getX(),this.getS().getY(),scale);
		ValuePoint p2 = createPoint(this.getE().getX(),this.getE().getY(),scale);
		return new ValueLine(p1, p2);
	}
	
	private ValuePoint createPoint(int x, int y, int scale) {
		return new ValuePoint(x+scale,y+scale);
	}
	
	
}
