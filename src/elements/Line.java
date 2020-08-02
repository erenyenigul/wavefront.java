package elements;

import constants.GeoConstants;

public class Line extends GeoElement{

	
	private Point p1;
	private Point p2;
	private int divisions;
	private Point[] points;
	private boolean isInfinite;
	 
	public Line(Point p1, Point p2, boolean isInfinite) {
		this(p1,p2, GeoConstants.DEFAULT_LINE_DIVISION, isInfinite);
	}
	
	public Line(Point p1, Point p2, int divisions, boolean isInfinite) {
		this.p1 = p1;
		this.p2 = p2;
		this.divisions = divisions;
		this.isInfinite = isInfinite;
		this.points = new Point[divisions];
		form();
	}
	
	public void setDivisions(int n) {
		// TODO Auto-generated method stub
		this.divisions = n;
		form();
	}

	
	public int getDivisions() {
		// TODO Auto-generated method stub
		return this.divisions;
	}
	
	
	public Vector getDirectionVector() {
	    Vector v1 = new Vector(p1);
	    Vector v2 = new Vector(p2);
	    return v1.subtract(v2);
	}
	
	public boolean isPointOnLine(Point p) {
	    Vector directionVector = this.getDirectionVector();
	    Point headOfDirectionVector = directionVector.getHead();
	    
	    if(!isInfinite() && p1.distanceToPoint(p) + p2.distanceToPoint(p) != this.length() )
	    	return false;
	    return headOfDirectionVector.getX()/p.getX() == headOfDirectionVector.getY()/p.getY() &&  headOfDirectionVector.getY()/p.getY() == headOfDirectionVector.getZ()/p.getZ();
	}
	
	public double length() {
		if(!isInfinite())
		  return p1.distanceToPoint(p2);
		else
		  return Double.MAX_VALUE;
	}
	
	public boolean isInfinite() {
		return this.isInfinite;
	}
	private void form() {
		
		double shiftX = p2.getX()-p1.getX()/(divisions-1);
		double shiftY = p2.getY()-p1.getY()/(divisions-1);
		double shiftZ = p2.getZ()-p1.getZ()/(divisions-1);
		
		Vector shiftVector = new Vector(shiftX,shiftY, shiftZ);
		
		Point nextPoint = p1;
		points[0] = p1;
		for(int i = 1; i<this.divisions; i++) {
			nextPoint = nextPoint.shift(shiftVector);
			points[i] = nextPoint;
		}
	}

}
