package elements;

import java.util.ArrayList;

import exceptions.NotEnoughVertexException;

public class PointGroup extends GeoElement {
	
	private ArrayList<Point> points;

	public PointGroup(Point...points) {

		this.points = new ArrayList<>(points.length);
		for(Point p : points) 
			this.points.add(p);
		
	}

	public ArrayList<Point> getPoints(){
		return points;
	}
	
	public Vector getNormal() {
		Vector v1 = new Vector(points.get(1)).subtract(new Vector(points.get(0)));
		Vector v2 = new Vector(points.get(2)).subtract(new Vector(points.get(1)));
		return v1.cross(v2).getUnitVector();
	}

	public Point getCenterOfMass() {
		double sumX = 0;
		double sumY = 0;
		double sumZ = 0;
		int size = points.size();
		for (Point p : points) {
			sumX += p.getX();
			sumY += p.getY();
			sumZ += p.getZ();
		}
		return new Point(sumX / size, sumY / size, sumZ / size);
	}

	public PointGroup scale(double scaleFactor) {
		Point centerOfMass = getCenterOfMass();
		Point[] scaledVertices = new Point[points.size()];
		for (int i = points.size()-1; -1 < i; i--) {
			Point p = points.remove(i);
			Vector shiftVector = new Vector(p, centerOfMass).scale(1-scaleFactor);
			Point scaledPoint = p.shift(shiftVector);
			scaledVertices[i] = scaledPoint;
		}
		updateAll(scaledVertices);
		return this;
	}

	public PointGroup shift(Vector shiftVector) {
		Point[] shiftedVertices = new Point[points.size()];
		for (int i = 0; i < points.size(); i++) {
			Point p = points.get(i);
			shiftedVertices[i] = p.shift(shiftVector);
		}
		updateAll(shiftedVertices);
		return this;
	}

	public PointGroup rotate() {
		Point[] reversed = new Point[this.points.size()];
		for (int i = 0; i < reversed.length; i++) {
			reversed[reversed.length - i - 1] = this.points.get(i);
		}
		updateAll(reversed);
		return this;
	}
	
	public Point[] getPointsAsArray() {
		Point[] duplicatePoints = new Point[points.size()];
		int i = 0;
		for(Point p: points) {
			duplicatePoints[i++] = p;
		}
		return duplicatePoints;
	}
	
	public PointGroup duplicate() {
		return new PointGroup((Point[]) this.points.toArray());
	}
	
	private void updateAll(Point[] points) {
		this.points.clear();
		for(Point p : points) 
			this.points.add(p);
	}
	

}
