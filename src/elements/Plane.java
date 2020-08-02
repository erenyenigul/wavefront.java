package elements;

public class Plane extends Surface{

	
	private double sideLength;
	
	public Plane(String name, double sideLength) {
		super(name);
		this.sideLength = sideLength;
		form();
	}

	public void setSideLength(double sideLength) {
		this.sideLength = sideLength;
		form();
	}
	
	private void form() {
		Point[] vertices = new Point[4];
		vertices[0] = new Point(sideLength/2,0,sideLength/2);
		vertices[1] = new Point(sideLength/2,0,-sideLength/2);
		vertices[2] = new Point(-sideLength/2,0,-sideLength/2);
		vertices[3] = new Point(-sideLength/2,0,sideLength/2);
		Face f = new Face(vertices);
		
		addFace(f);

	}
	
}
