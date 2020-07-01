package main;

import elements.*;
import operations.CreationOperations;

public class TestMesh {

	public static void main(String[] args) {
		// TODO Auto-generated method stub


		
		Circle c1 = new Circle("asdas",10, new Point(00,0,100), 10);
		Circle c2 = new Circle("dads",10, new Point(0,50,-100), 10);
		
		//CreationOperations.loft(c2, c1, 20, 0.1).saveAsObjFile();
	
        //CreationOperations.loft(c1, c2, 200, 5).saveAsObjFile();;
		
	     Mesh m = new Mesh("LOL");
	     m.add(c1);
	     m.saveAsObjFile();
		
		
	}

}
