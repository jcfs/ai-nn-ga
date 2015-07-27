package pt.pxinxas.fcpp.util;

/**
 * @author jcfs
 */
public class Triangle {
	public Point v1;
	public Point v2;
	public Point v3;
	
	public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
		this.v1 = new Point(x1, y1);
		this.v2 = new Point(x2, y2);
		this.v3 = new Point(x3, y3);
	}
	
	public Triangle(Point v1, Point v2, Point v3) {
		this.v1 = v1;
		this.v2 = v2;
		this.v3 = v3;
	}

	
}
