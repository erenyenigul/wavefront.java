package elements;

import elements.GeoElement;

public class Face extends GeoElement{

	private Point[] vertices;
   
	
	public Face(Point... vertices) {
		this.vertices = vertices;
	}
	
	public Point[] getVertices() {
		return vertices;	
	}
	
	  public Vector getNormal() { 
	  Vector v1 = new Vector(vertices[1]).subtract(new Vector(vertices[0]));
	  Vector v2 = new Vector(vertices[1]).subtract(new Vector(vertices[2]));
	  
	  return v1.cross(v2).getUnitVector(); 
	  }
	 
	public Point getCenterOfMass() {
		double sumX = 0;
		double sumY = 0;
		double sumZ = 0;
		int size = vertices.length;
		for(Point p : vertices) {
			sumX += p.getX();
			sumY += p.getY();
			sumZ += p.getZ();
		}
		return new Point(sumX/size, sumY/size, sumZ/size);
		
	}
	
	public Face scale(double scaleFactor) {
		Point centerOfMass = getCenterOfMass();
		Point[] verticesOfScaledFace = new Point[vertices.length];
		for(int i = 0; i<vertices.length; i++ ) {
			Point p = vertices[i];
			Vector shiftVector = new Vector(p, centerOfMass).scale(1-scaleFactor);
			
			verticesOfScaledFace[i] = p.shift(shiftVector);
		}
		return new Face(verticesOfScaledFace);
	}
	
	public Face shift(Vector shiftVector) {
		Point[] shiftedVertices = new Point[vertices.length];
		for(int i = 0; i<vertices.length; i++ ) {
			Point p = vertices[i];
			shiftedVertices[i] = p.shift(shiftVector);
		}
		return new Face(shiftedVertices);
	}
	
	public String info() {
		
		StringBuilder allInfo = new StringBuilder();
		
		allInfo.append("--Face with following elements : \n");
		for(Point p : vertices)
		  allInfo.append("--" + p.info() + "\n");
		
	    return allInfo.toString();
		
	}
	
	public Face rotate() {
		Point[] reversed = new Point[this.vertices.length];
		for(int i = 0; i<reversed.length; i++) {
			reversed[reversed.length-i-1] = this.vertices[i];
		}
		return new Face(reversed);
	}
	
}
