package elements;

public class Circle extends Surface implements Divisible {

	
	private double radius;
	private Point center;
	private int divisions;
	
	public Circle(String name, double radius, Point center, int divisions) {
		super(name);
		this.divisions = divisions;
		this.radius = radius;
		this.center = center;
		form();
	}
	
	public void setDivisions(int n) {
		this.divisions = n;
		form();
	}
	
	public Point getCenterOfMass() {
		return this.center;
	}
	
	private void form() {
		
		Point[] vertices = new Point[divisions];
		Vector r = new Vector(new Point(radius,0,0));
		
	    double theta = 2*Math.PI/divisions;
	    Vector axisOfRotation = new Vector(new Point(0,0,1));
	   
	    
		for(int i = 0; i<divisions; i++) {
	    	vertices[i] = r.getHead();
	        r = r.rotate(theta, axisOfRotation);
		}
		
		for(int i = 0; i<vertices.length; i++) {
	    	vertices[i] =  vertices[i].shift(new Vector(center));
		}
		
		Face upper = new Face(vertices);
		getFaces().add(upper);
		getFaces().add(upper.rotate());
		
	}

	@Override
	public int getDivisions() {
		// TODO Auto-generated method stub
		return this.divisions;
	}
	
	
	
	
	
	
}
