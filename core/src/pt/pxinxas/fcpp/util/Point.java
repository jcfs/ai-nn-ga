package pt.pxinxas.fcpp.util;

import java.io.Serializable;

public class Point implements Serializable {
	private static final long serialVersionUID = 1L;
	public double x;
	public double y;

	public Point() {
		// TODO Auto-generated constructor stub
	}

	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double distance(Point p) {
		return distance(p.x, p.y);
	}

	public double distance(double x, double y) {
		return Math.hypot(this.x - x, this.y - y);
	}

	public void set(double x, double y) {
		this.x = x;
		this.y = y;
	}
	/**
	 * @param x
	 *            the x to set
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * @param y
	 *            the y to set
	 */
	public void setY(double y) {
		this.y = y;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "(" + x + "," + y + ")";
	}


}
