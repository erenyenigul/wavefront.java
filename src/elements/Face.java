package elements;

public class Face extends GeoElement{

	private Point[] vertices;
	
    private int divisions;
	
	public void setDivisions(int n) {
		this.divisions = n;
	}
	
	public int getDivisions() {
		return this.divisions;
	}
	
	public Face(Point... vertices) {
		this.vertices = vertices;
		this.divisions = vertices.length;
	}
	
	public Face(int divisions) {
		this.divisions = divisions;
		vertices = new Point[divisions];
	}
	
	public Point[] getVertices() {
		return vertices;
		
	}
	
	public Vector getNormal() {
		Vector v1 = new Vector(vertices[0], vertices[1]);
		Vector v2 = new Vector(vertices[1], vertices[2]);
		
		return v1.cross(v2);
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
