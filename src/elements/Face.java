package elements;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import exceptions.NotEnoughVertexException;
import exceptions.VerticesColinearException;
import exceptions.VerticesNotCoplanarException;

public class Face extends PointGroup {


	public Face(Point... points) {
		super(points);
		checkIfPointsFormFace(points);
	}
	
	public boolean isFacingUp() { return getNormal().degreeBetween(new Vector(0,1,0)) <= Math.PI/2; }
	
	public Face shift(Vector shiftVector) {super.shift(shiftVector); return this;}
	public Face rotate() {super.rotate(); return this;}
	public Face scale(double scaleFactor) { super.scale(scaleFactor); return this;}	
	public Face duplicate() {return new Face(getPointsAsArray());}
	
	public String toString() {
		StringBuilder allInfo = new StringBuilder();
		allInfo.append("--Face with following elements : \n");
		for (Point p : getPoints())
			allInfo.append("--" + p + "\n");

		return allInfo.toString();
	}
	
	private void checkIfPointsFormFace(Point...points) {
		if(points.length<3) throw new NotEnoughVertexException();
		if(points.length== 3) {
			Line l = new Line(points[0], points[1], true);
			if(l.isPointOnLine(points[2])) throw new VerticesColinearException();
		}
		else if(points.length>3) {
			Line l = new Line(points[0], points[1], true);
			Boolean isColinear = true;
			PointGroup pg = new PointGroup(points[0], points[1], points[2]);
			Vector normal = pg.getNormal();
			for(int i = 3; i<points.length; i++) {
				if(isColinear && !l.isPointOnLine(points[i])) 
					isColinear = false;
			}
			if(isColinear) throw new VerticesColinearException();
		}
	}
}
