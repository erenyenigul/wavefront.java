package elements;

public class Vector extends GeoElement{

	private Point head;
	private Point tail;
	
	public Vector(Point head, Point tail) {
		this.head = head;
		this.tail = tail;
	}
	
	public Point getHead() {
		return head;
	}
	public Point getTail() {
		return tail;
	}
	
	public double length() {
		return tail.distanceToPoint(head);
	}
	
	public Vector getUnitVector() {
		
		double length = this.length();
		
		double headX = (head.getX()-tail.getX())/length + tail.getX();
		double headY = (head.getY()-tail.getY())/length + tail.getY();
		double headZ = (head.getZ()-tail.getZ())/length + tail.getZ();
		
		Point unitVectorHead = new Point(headX, headY, headZ);
		
		return new Vector(unitVectorHead, tail);
	}
	
	public Vector shiftTailTo(Point p) {
		
		double headX = (head.getX()-tail.getX());
		double headY = (head.getY()-tail.getY());
		double headZ = (head.getZ()-tail.getZ());
		
		Point shiftedVectorHead = new Point(headX, headY, headZ);
		
		return new Vector(shiftedVectorHead, tail);
		
	}
	
    public Vector cross(Vector v) {
    
    	
    	Vector v1 = this.shiftTailTo(Point.ORIGIN);
    	Vector v2 = v.shiftTailTo(Point.ORIGIN);
    	
    	Point headV1 = v1.getHead();
    	Point headV2 = v2.getHead();
    	
    	double crossX = headV1.getY()*headV2.getZ()-headV1.getZ()*headV2.getY();
    	double crossY = headV1.getZ()*headV2.getX()-headV1.getX()*headV2.getZ();
    	double crossZ = headV1.getX()*headV2.getY()-headV1.getY()*headV2.getX();
    	
        Point crossHead = new Point(crossX, crossY, crossZ);
        
    	return new Vector(crossHead, Point.ORIGIN);
    }
    
    public double dot(Vector v) {
    	
    	Vector v1 = this.shiftTailTo(Point.ORIGIN);
    	Vector v2 = v.shiftTailTo(Point.ORIGIN);
    	
    	Point headV1 = v1.getHead();
    	Point headV2 = v2.getHead();
    	
    	return headV1.getX()*headV2.getX() + headV1.getY()*headV2.getY() + headV1.getZ()*headV2.getZ();
    	
    }
	
    public double degreeBetween(Vector v) {
    	double dotProduct = this.dot(v);
    	return Math.acos(dotProduct/ (this.length() * v.length())* Math.PI / 180);
    }
    
    public Vector add(Vector v) {
    	Vector v1 = this.shiftTailTo(Point.ORIGIN);
    	Vector v2 = v.shiftTailTo(Point.ORIGIN);
    	
    	Point head1 = v1.getHead();
    	Point head2 = v2.getHead();
    	
    	double sumX = head1.getX() + head2.getX();
    	double sumY = head1.getY() + head2.getY();
    	double sumZ = head1.getZ() + head2.getZ();

     	Point sumHead = new Point(sumX, sumY, sumZ);
        return new Vector(sumHead, Point.ORIGIN);	
    }
    
    public Vector scale(double scalar) {
  
    	 Vector v = this.shiftTailTo(Point.ORIGIN); 
    	 Point head = v.getHead();
    	 
    	 double scaledX = head.getX()* scalar;
    	 double scaledY = head.getY()* scalar;
    	 double scaledZ = head.getZ()* scalar;
         Point scaledHead = new Point(scaledX, scaledY, scaledZ);
         
         return new Vector(scaledHead, Point.ORIGIN);
    }
	
    public Vector rotate(double angle, Vector axisOfRotation) {
        return this.scale(Math.cos(angle)).add(axisOfRotation.cross(this).scale(Math.sin(angle))).add(axisOfRotation.scale(axisOfRotation.dot(this)*(1-Math.cos(angle)))); 
    }
    
    
	
	public String info() {
		return "--Vector with head on: \n" + head.info() + "--and tail: " + tail.info();   
	}
	
}
