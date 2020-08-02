package elements;

import java.util.ArrayList;

public class Face extends PointGroup {

	private ArrayList<Point> vertices;

	public Face(Point... points) {
		super(points);
		this.vertices = getPoints();
	}
	
	public boolean isFacingUp() { return getNormal().degreeBetween(new Vector(0,1,0)) <= Math.PI/2; }

	public ArrayList<Point> getVertices() {return vertices;}
	
	
	

	public Face shift(Vector shiftVector) {super.shift(shiftVector); return this;}
	public Face rotate() {super.rotate(); return this;}
	public Face scale(double scaleFactor) { super.scale(scaleFactor); return this;}	
	public Face duplicate() {return new Face((Point[]) this.vertices.toArray());}
	
	public String info() {
		StringBuilder allInfo = new StringBuilder();
		allInfo.append("--Face with following elements : \n");
		for (Point p : vertices)
			allInfo.append("--" + p.info() + "\n");

		return allInfo.toString();
	}
}
