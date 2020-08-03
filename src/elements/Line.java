package elements;

import constants.GeoConstants;

public class Line extends GeoElement{

	private Point p1;
	private Point p2;
	private int divisions;
	private Point[] points;
	private boolean isInfinite;
	private Vector directionVector;
	
	public Line(Point p1, Point p2, boolean isInfinite) {
		this(p1,p2, GeoConstants.DEFAULT_LINE_DIVISION, isInfinite);
	}
	
	public Line(Point p, Vector directionVector) {
		this.p1 = p;
		this.p2 = p.shift(directionVector);
		this.directionVector = directionVector;
		this.isInfinite = true;
	}
	
	public Line(Point p1, Point p2, int divisions, boolean isInfinite) {
		this.p1 = p1;
		this.p2 = p2;
		this.divisions = divisions;
		this.isInfinite = isInfinite;
		this.points = new Point[divisions];
		form();
		
		Vector v1 = new Vector(p1);
		Vector v2 = new Vector(p2);
		this.directionVector = v1.subtract(v2).getUnitVector();
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
	   return this.directionVector;
	}
	
	public boolean isPointOnLine(Point p) {
	    Vector directionVector = getDirectionVector();
	    Point headOfDirectionVector = directionVector.getHead();
	    Vector testVector = new Vector(p1, p);
	    return testVector.getUnitVector().equals(directionVector) || testVector.getUnitVector().scale(-1).equals(directionVector);
	}
	
	public double length() {
		if(!isInfinite())
		  return p1.distanceToPoint(p2);
		else
		  return Double.MAX_VALUE;
	}
	
	public double shortestDistanceToPoint(Point p) {
		Vector u = new Vector(p, p1);
		double uV = u.dot(directionVector);
		double distance = Math.sqrt(Math.pow(u.length(),2)-Math.pow(uV,2));
		return distance;
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
	
	public static int closestPoint(Line l, Point[] points) {
		int closestIndex = 0;
		double closestDistance = 0;
		for(int i=0;i<points.length;i++) {
			double distance = l.shortestDistanceToPoint(points[i]);
			if(distance<closestDistance) {
				closestDistance = distance;
				closestIndex = i;
			}
		}
		return closestIndex;
	}

}
