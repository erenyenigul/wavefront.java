package operations;

import java.util.ArrayList;

import elements.*;

public class CreationOperations {

	public static Mesh extrude(Face face, Vector directionVector) {

		Mesh extruded = new Mesh("Extruded Face");

		if (face.getNormal().degreeBetween(directionVector) < Math.PI / 2) {
			face = face.rotate();
		}
		extruded.add(new Surface("Side Surface", face));

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
			extruded.add(new Surface("Side Surface", f));
		}
		Face lastSideOfMesh = new Face(verticesOfFace[verticesOfFace.length - 1],
				verticesOfUpperFace[verticesOfFace.length - 1], verticesOfUpperFace[0], verticesOfFace[0]);
		extruded.add(new Surface("Side Surface", lastSideOfMesh));

		Face upperFace = new Face(verticesOfUpperFace);
		extruded.add(new Surface("Side Surface", upperFace.rotate()));

		return extruded;
	}

	public static Mesh loft(Surface s1, Surface s2, int segmentNum, double scaleFactor) {

			
		if (s1.getDivisions() > s2.getDivisions()) {
			int d = s1.getDivisions();
			s2.setDivisions(d);
		} else if (s1.getDivisions() < s2.getDivisions()) {
			int d = s2.getDivisions();
		    s1.setDivisions(d);
		}

		
		Mesh lofted = new Mesh("Lofted Surfaces");

		lofted.add(s1);
		lofted.add(s2);

		Vector shiftVector = new Vector(c1.getCenterOfMass(), c2.getCenterOfMass()).scale(1.0 / segmentNum);
		Face lastFace = c1;

		for (int i = 0; i < segmentNum+1 ; i++) {
			Face nextFace = null;
			if (i < segmentNum / 2)
				nextFace = lastFace.scale(Math.pow(scaleFactor, 2.0 / segmentNum)).shift(shiftVector);
			else if (i == segmentNum / 2)
				nextFace = c2.scale(scaleFactor).shift(shiftVector.scale(-1 * segmentNum / 2));
			else
				nextFace = lastFace.scale(Math.pow(1 / scaleFactor, 2.0 / segmentNum)).shift(shiftVector);

			lofted.add(new Surface("", formSideFaces(lastFace.getVertices(), nextFace.getVertices())) );
			lastFace = nextFace;
		}

		
		return lofted;

	}

	private static ArrayList<Face> formSideFaces(Point[] verticesOfFirst, Point[] verticesOfSecond) {

		ArrayList<Face> faces = new ArrayList<Face>();

		for (int i = 1; i < verticesOfFirst.length; i++) {
			Point nextVertex = verticesOfFirst[i];
			Point lastVertex = verticesOfFirst[i - 1];
			Point upperNextVertex = verticesOfSecond[i];
			Point upperLastVertex = verticesOfSecond[i - 1];
			faces.add( new Face(lastVertex, upperLastVertex, upperNextVertex, nextVertex));
		}
		Face lastSideOfMesh = new Face(verticesOfFirst[verticesOfFirst.length - 1],
				verticesOfSecond[verticesOfFirst.length - 1], verticesOfSecond[0], verticesOfFirst[0]);
		faces.add(lastSideOfMesh);

		return faces;
	}

}
