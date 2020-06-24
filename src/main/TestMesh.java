package main;

import elements.*;
import operations.CreationOperations;

public class TestMesh {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Point p1 = new Point(0,0,0);
		Point p2 = new Point(1,1,1);
		Point p3 = new Point(0,1,0);
		
		
		Point p4 = new Point(0,0,10);
		
		Face f1 = new Face(p1,p2,p3);
		Vector v1 = new Vector(p4,p1);
		
		Circle c = new Circle(5, 100);
	    CreationOperations.extrude(c, v1).saveAsObjFile();;
		
		
		
	}

}
