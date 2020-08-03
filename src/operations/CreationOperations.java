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
		
		extruded.addFace(face);

		Point[] verticesOfFace = face.getPointsAsArray();
		Point[] verticesOfUpperFace = new Point[verticesOfFace.length];

		verticesOfUpperFace[0] = verticesOfFace[0].shift(directionVector);

		for (int i = 1; i < verticesOfFace.length; i++) {
			Point nextVertex = verticesOfFace[i];
			Point lastVertex = verticesOfFace[i - 1];
			Point upperNextVertex = nextVertex.shift(directionVector);
			verticesOfUpperFace[i] = upperNextVertex;
			Point upperLastVertex = verticesOfUpperFace[i - 1];
			Face f = new Face(lastVertex, upperLastVertex, upperNextVertex, nextVertex);
			extruded.addFace(f);
		}
		Face lastSideOfMesh = new Face(verticesOfFace[verticesOfFace.length - 1],
				verticesOfUpperFace[verticesOfFace.length - 1], verticesOfUpperFace[0], verticesOfFace[0]);
		extruded.addFace(lastSideOfMesh);
		

		Face upperFace = new Face(verticesOfUpperFace);
		extruded.addFace( upperFace.rotate());

		return extruded;
	}

	public static Mesh loft(Surface surface1, Surface surface2, int segmentNum, double scaleFactor) throws DivisionsNotMatchException {
		
		Mesh lofted = new Mesh("Lofted mesh from surfaces :" +surface1 + " " + surface2);
		
		Curve s1 = surface1.getOuterEdges();
		Curve s2 = surface2.getOuterEdges();
                   
		if(s1.getDivisions() != s2.getDivisions()) throw new DivisionsNotMatchException();
		
		Vector directionVector = new Vector(s1.getCenterOfMass(), s2.getCenterOfMass());
		
		if(!(s1.getNormal().degreeBetween(directionVector) < Math.PI/2)) {
			s1.rotate();
			lofted.addAll(surface1.getFrontFaces());
		}
		else
			lofted.addAll(surface1.getBottomFaces());
		if(!(s2.getNormal().degreeBetween(directionVector.scale(-1)) < Math.PI/2)) {
			s2.rotate();
			lofted.addAll(surface1.getFrontFaces());
		}
		else
			lofted.addAll(surface2.getBottomFaces());
		
		Point firstCurvePivot = s1.getPoints().get(0);
		Line l = new Line(firstCurvePivot, directionVector);
		
		
		
		
		Vector shiftVector = directionVector.scale(1.0 / (2*segmentNum+2));
		Curve lastFace = s1;
		double segmentScaleFactor = Math.pow(scaleFactor, 1.0 / (segmentNum));
		for (int i = 0; i < 2*segmentNum+2 ; i++) {
			Curve nextFace = lastFace.duplicate();
			if (i < segmentNum+1) {
				nextFace.scale(segmentScaleFactor).shift(shiftVector);
				lofted.addAll(formSideFaces(lastFace.getPointsAsArray(), nextFace.getPointsAsArray()));
			}				
			else if(i == segmentNum+1) {
				nextFace = s2.duplicate();
				nextFace.shift(shiftVector.scale(-1*segmentNum));
				nextFace.scale(scaleFactor);
				lofted.addAll(formSideFaces(nextFace.getPointsAsArray(), lastFace.getPointsAsArray()));

			}
			else {
				nextFace.scale(1/segmentScaleFactor).shift(shiftVector);
				lofted.addAll(formSideFaces(nextFace.getPointsAsArray(), lastFace.getPointsAsArray()));
			}
				
			lastFace = nextFace.duplicate();
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
			faces.add(new Face(lastVertex, nextVertex, upperNextVertex, upperLastVertex));
		}
		Face lastSideOfMesh = new Face(verticesOfFirst[verticesOfFirst.length - 1],  verticesOfFirst[0], 
									   verticesOfSecond[0], verticesOfSecond[verticesOfFirst.length - 1]);
		faces.add(lastSideOfMesh);
		return faces;
	}

}
