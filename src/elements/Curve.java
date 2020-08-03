package elements;

import java.util.ArrayList;

public class Curve extends PointGroup {
	
	ArrayList<Point> points;
	
	public Curve(Point... points) {
		super(points);
		this.points = getPoints();
	}
	
	public void addPoint(Point point) {
		getPoints().add(point);
	}

	public int getDivisions() {
		return getPoints().size();
	}
	
	public Curve shift(Vector shiftVector) {super.shift(shiftVector); return this;}
	public Curve rotate() {super.rotate(); return this;}
	public Curve scale(double scaleFactor) { super.scale(scaleFactor); return this;}
	public Curve duplicate() {return new Curve(getPointsAsArray());}
	
	public String toString() {
		return "[Curve : "+ points +"]";
	}
		
}
