package elements;

import elements.Vector;

public class Point extends GeoElement{

	
    public static final Point ORIGIN = new Point(0,0,0);
	
	private double x;
	private double y;
	private double z;
	
	public Point(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public double getX() {
		return this.x;
	}
	
	public double getY() {
		return this.y;
	}
	
	public double getZ() {
		return this.z;
	}
	
	public double distanceToPoint(Point p) {
		return Math.sqrt( Math.pow(this.getX() - p.getX(), 2) +
		 		  Math.pow(this.getY() - p.getY(), 2) +
		          Math.pow(this.getZ() - p.getZ(), 2));
	}
	
	public Point shift(Vector v) {
		Point head= v.getHead();
	
		double shiftedX = head.x + this.x;
		double shiftedY = head.y+ this.y;
		double shiftedZ = head.z + this.z;
		
		return new Point(shiftedX, shiftedY, shiftedZ);
	}
	
	public boolean equals(Point p) {
		return p.getX() == this.x && p.getY() == this.y && p.getZ() == this.z;
	}
	
	public String info() {
		return 	"--Point on x:"+x+" y:"+y+" z:"+z; 
	}
	
}
