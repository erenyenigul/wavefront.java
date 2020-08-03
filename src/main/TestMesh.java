package main;

import java.util.HashMap;

import elements.*;
import operations.CreationOperations;

public class TestMesh {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Face f1 = new Face(Point.ORIGIN, new Point(1,0,0), new Point(1,1,0), new Point(0,1,0));
		Surface s = new Surface("MySurface");
		s.addFace(f1);
		
		Face f2 = f1.duplicate().shift(new Vector(0,0,10));
		Surface s2 = new Surface("MySurface");
		s2.addFace(f2);
		CreationOperations.loft(s2, s, 1, 0.5).saveAsObjFile();;
		
	}

}
