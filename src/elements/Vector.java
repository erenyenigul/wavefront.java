package elements;

import elements.GeoElement;

public class Vector extends GeoElement{

	private Point head;
	
	public Vector(Point head) {
		this.head = head;
	}
	
	public Vector(Point tail, Point head) {
		
		double x = head.getX()-tail.getX();
		double y = head.getY()-tail.getY();
		double z = head.getZ()-tail.getZ();
		this.head = new Point(x,y,z);
	}
	
	public Vector(double headX, double headY, double headZ) {
		this(new Point(headX, headY, headZ));
	}
	
	public Point getHead() {
		return head;
	}
	
	public double length() {
		return Point.ORIGIN.distanceToPoint(head);
	}
	
	public Vector getUnitVector() {
		
		double length = this.length();
		
		double headX = (head.getX())/length;
		double headY = (head.getY())/length;
		double headZ = (head.getZ())/length;
		
		Point unitVectorHead = new Point(headX, headY, headZ);
		
		return new Vector(unitVectorHead);
	}
	
	 
	
    public Vector cross(Vector v) {
    
    	Point headV1 = this.getHead();
    	Point headV2 = v.getHead();
    	
    	double crossX = headV1.getY()*headV2.getZ()-headV1.getZ()*headV2.getY();
    	double crossY = headV1.getZ()*headV2.getX()-headV1.getX()*headV2.getZ();
    	double crossZ = headV1.getX()*headV2.getY()-headV1.getY()*headV2.getX();
    	
        Point crossHead = new Point(crossX, crossY, crossZ);
        
    	return new Vector(crossHead);
    }
    
    public double dot(Vector v) {
    	
    	
    	Point headV1 = this.getHead();
    	Point headV2 = v.getHead();
    	
    	return headV1.getX()*headV2.getX() + headV1.getY()*headV2.getY() + headV1.getZ()*headV2.getZ();
    	
    }
	
    public double degreeBetween(Vector v) {
    	double dotProduct = this.dot(v);
    	return Math.acos(dotProduct/ (this.length() * v.length())* Math.PI / 180);
    }
    
    public Vector add(Vector v) {
    	
    	
    	Point head1 = this.getHead();
    	Point head2 = v.getHead();
    	
    	double sumX = head1.getX() + head2.getX();
    	double sumY = head1.getY() + head2.getY();
    	double sumZ = head1.getZ() + head2.getZ();

     	Point sumHead = new Point(sumX, sumY, sumZ);
        return new Vector(sumHead);	
    }
    
    public Vector subtract(Vector v) {
    	Point head1 = this.getHead();
    	Point head2 = v.getHead();
    	
    	double sumX = head1.getX() - head2.getX();
    	double sumY = head1.getY() - head2.getY();
    	double sumZ = head1.getZ() - head2.getZ();

     	Point sumHead = new Point(sumX, sumY, sumZ);
        return new Vector(sumHead);	
    }
    
    public Vector scale(double scalar) {
  
    	
    	 Point head = this.getHead();
    	 
    	 double scaledX = head.getX()* scalar;
    	 double scaledY = head.getY()* scalar;
    	 double scaledZ = head.getZ()* scalar;
         Point scaledHead = new Point(scaledX, scaledY, scaledZ);
         
         return new Vector(scaledHead);
    }
	
    public Vector rotate(double angle, Vector axisOfRotation) {
        return this.scale(Math.cos(angle)).add(axisOfRotation.cross(this).scale(Math.sin(angle))).add(axisOfRotation.scale(axisOfRotation.dot(this)*(1-Math.cos(angle)))); 
    }
   
    public boolean isZeroVector() {
    	return head.equals(Point.ORIGIN);
    }
	
    public boolean equals(Vector v) {
    	return head.equals(v.getHead());
    }
    
	public String info() {
		return "--Vector with head on: \n" + head.info();   
	}
	
}
