package main;

import java.util.HashMap;

import elements.*;
import operations.CreationOperations;

public class TestMesh {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	
		Face f1 = new Face(Point.ORIGIN, new Point(5,0,0), new Point(5,0,5),new Point(0,0,5));
        Face f2 = new Face(Point.ORIGIN, new Point(0,5,0), new Point(0,5,5), new Point(0,0,5));
		Mesh m = new Mesh("lol");
		Point p = new Point(-1,-1,-1);
		f1.getPoints().set(0, p);
		f2.getPoints().set(0, p);
        m.add(f1);
		m.add(f2.rotate());
        m.saveAsObjFile();
		
		//Circle c1 = new Circle("m", 5,Point.ORIGIN.shift(new Vector(0,0,10)), 4);
		//CreationOperations.loft(c1, s, 10, 0.3).saveAsObjFile();
	}

}
