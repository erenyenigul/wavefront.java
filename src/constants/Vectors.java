package constants;

import elements.Point;
import elements.Vector;

public class Vectors {

	public static double determinant(Vector v1, Vector v2, Vector v3) {
		Point p1 = v1.getHead();
		Point p2 = v2.getHead();
		Point p3 = v3.getHead();
		double i = p1.getX()*(p2.getY()*p3.getZ()-p2.getZ()*p3.getY());
		double j = p2.getX()*(p1.getY()*p3.getZ()-p1.getZ()*p3.getY());
		double k = p3.getX()*(p1.getY()*p2.getZ()-p1.getZ()*p2.getY());
		
		return i-j+k;
	}
	
	public static double dot(Vector v1, Vector v2) {
		return v1.dot(v2);
	}
}
