package operations;

import elements.*;

public class CreationOperations {

	public static Mesh extrude(Face face, Vector directionVector) {
		
		Mesh extruded = new Mesh("Extruded Face");
		
		if(face.getNormal().degreeBetween(directionVector)<Math.PI/2) {
		   face = face.rotate();
		}
		extruded.addFace(face);
		
		Point[] verticesOfFace = face.getVertices();
		Point[] verticesOfUpperFace = new Point[verticesOfFace.length];
		
		verticesOfUpperFace[0] = verticesOfFace[0].shift(directionVector);
		
		for(int i=1;i<verticesOfFace.length;i++) {
			Point nextVertex = verticesOfFace[i];
			Point lastVertex = verticesOfFace[i-1];
			Point upperNextVertex = nextVertex.shift(directionVector);
			verticesOfUpperFace[i] = upperNextVertex;
			Point upperLastVertex = verticesOfUpperFace[i-1];
			Face f = new Face(lastVertex, upperLastVertex, upperNextVertex, nextVertex);
			extruded.addFace(f);
		}
		Face lastSideOfMesh = new Face(verticesOfFace[verticesOfFace.length-1],verticesOfUpperFace[verticesOfFace.length-1], verticesOfUpperFace[0], verticesOfFace[0] );
		extruded.addFace(lastSideOfMesh);
		
		Face upperFace = new Face(verticesOfUpperFace);
		extruded.addFace(upperFace.rotate());
	
		return extruded;
	}
	
	
	
	
	
}
