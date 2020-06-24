package elements;

public class Circle extends Face {

	
	private double radius;
	
	
	public Circle(double radius, int divisions) {
		super(divisions);
		this.radius = radius;
		
		Point[] vertices = getVertices();
		Vector r = new Vector(new Point(radius,0,0),Point.ORIGIN);
		
	    double theta = 2*Math.PI/divisions;
	    Vector axisOfRotation = new Vector(new Point(0,0,1), Point.ORIGIN);
	    
		for(int i = 0; i<divisions; i++) {
	    	vertices[i] = r.getHead();
	        r = r.rotate(theta, axisOfRotation);
		}
		
		
		
	}
	
	
	
	
	
	
}
