package operations;

import java.util.ArrayList;

import elements.*;
import exceptions.DivisionsNotMatchException;

public class CreationOperations {

	public static Mesh extrude(Face face, Vector directionVector) {

		Mesh extruded = new Mesh("Extruded Face");

		if (face.getNormal().degreeBetween(directionVector) < Math.PI / 2) {
			face = face.rotate();
		}
		
		extruded.add(face);

		Point[] verticesOfFace = face.getVertices();
		Point[] verticesOfUpperFace = new Point[verticesOfFace.length];

		verticesOfUpperFace[0] = verticesOfFace[0].shift(directionVector);

		for (int i = 1; i < verticesOfFace.length; i++) {
			Point nextVertex = verticesOfFace[i];
			Point lastVertex = verticesOfFace[i - 1];
			Point upperNextVertex = nextVertex.shift(directionVector);
			verticesOfUpperFace[i] = upperNextVertex;
			Point upperLastVertex = verticesOfUpperFace[i - 1];
			Face f = new Face(lastVertex, upperLastVertex, upperNextVertex, nextVertex);
			extruded.add(f);
		}
		Face lastSideOfMesh = new Face(verticesOfFace[verticesOfFace.length - 1],
				verticesOfUpperFace[verticesOfFace.length - 1], verticesOfUpperFace[0], verticesOfFace[0]);
		extruded.add(lastSideOfMesh);
		

		Face upperFace = new Face(verticesOfUpperFace);
		extruded.add( upperFace.rotate());

		return extruded;
	}

	public static Mesh loft(Surface surface1, Surface surface2, int segmentNum, double scaleFactor) throws DivisionsNotMatchException {

		
		Curve s1 = surface1.getOuterEdges();
		Curve s2 = surface2.getOuterEdges();
                   
		if(s1.getDivisions() != s2.getDivisions()) throw new DivisionsNotMatchException();
		
		Mesh lofted = new Mesh("Lofted mesh from surfaces :" +s1 + " " + s2);
    	
		lofted.addAll(surface1.getFrontFaces());
		lofted.addAll(surface2.getFrontFaces());

		Vector shiftVector = new Vector(s1.getCenterOfMass(), s2.getCenterOfMass()).scale(1.0 / (segmentNum+1));
		Curve lastFace = s1;

		for (int i = 0; i < segmentNum+1 ; i++) {
			Curve nextFace = null;
			if (i < segmentNum / 2)
				nextFace = lastFace.scale(Math.pow(scaleFactor, 2.0 / segmentNum)).shift(shiftVector);
			else if (i == segmentNum / 2)
				nextFace = s2.scale(scaleFactor).shift(shiftVector.scale(-1 * segmentNum / 2));
			else
				nextFace = lastFace.scale(Math.pow(1 / scaleFactor, 2.0 / segmentNum)).shift(shiftVector);

			lofted.addAll(formSideFaces(lastFace.getPoints().toArray(new Point[lastFace.getPoints().size()]), nextFace.getPoints().toArray(new Point[nextFace.getPoints().size()])));
			lastFace = nextFace;
		}

		
		return lofted;

	}

	private static ArrayList<Face> formSideFaces(Point[] verticesOfFirst, Point[] verticesOfSecond) {

		ArrayList<Face> faces = new ArrayList<Face>();
        
		
		if (face.getNormal().degreeBetween(directionVector) < Math.PI / 2) {
			face = face.rotate();
		}
		
		for (int i = 1; i < verticesOfFirst.length; i++) {
			Point nextVertex = verticesOfFirst[i];
			Point lastVertex = verticesOfFirst[i - 1];
			Point upperNextVertex = verticesOfSecond[i];
			Point upperLastVertex = verticesOfSecond[i - 1];
			faces.add(new Face(lastVertex, upperLastVertex, upperNextVertex, nextVertex));
		}
		Face lastSideOfMesh = new Face(verticesOfFirst[verticesOfFirst.length - 1],
									   verticesOfSecond[verticesOfFirst.length - 1], verticesOfSecond[0], verticesOfFirst[0]);
		faces.add(lastSideOfMesh);
        Mesh m = new Mesh(verticesOfFirst.toString());
        m.addAll(faces);
        m.saveAsObjFile();
		return faces;
	}

}
