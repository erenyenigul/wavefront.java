package elements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class Surface extends Mesh {

	private ArrayList<Face> frontFaces;
	private ArrayList<Face> bottomFaces;
	private int divisions;

	public Surface(String name) {
		super(name);
		this.frontFaces = new ArrayList<Face>();
		this.bottomFaces = new ArrayList<Face>();
		this.divisions = 4;
	}
	
	public Surface(String name, ArrayList<Face> faces) {
		this(name);
		for(Face f : faces) {
		  addFace(f);
		}
	}
	
	public void addFace(Face f) {
		
		Face upperFace;
		Face bottomFace;
		
		if(f.isFacingUp()) { 
			upperFace = f;
			bottomFace = f.duplicate().rotate();
		}else {
			upperFace = f.duplicate().rotate();
			bottomFace = f;	
		}
		frontFaces.add(upperFace);
		bottomFaces.add(bottomFace);
	}
	
	public boolean removeFace(Face f) {
		return frontFaces.remove(f) ||  bottomFaces.remove(f.rotate());
	}
	
	public ArrayList<Face> getFrontFaces() {
		return this.frontFaces;
	}
	
	public ArrayList<Face> getBottomFaces() {
		return this.bottomFaces;
	}
	
	public void setDivisions(int n) {
		this.divisions = n;
	}
	
	public int getDivisions() {
		return this.divisions;
	}

	public Point getCenterOfMass() {
		double sumX = 0;
		double sumY = 0;
		double sumZ = 0;
		int size = frontFaces.size();
		for(Face f : frontFaces) {
			Point p = f.getCenterOfMass();
			sumX += p.getX();
			sumY += p.getY();
			sumZ += p.getZ();
		}
		return new Point(sumX/size, sumY/size, sumZ/size);
	}
	
	public void scale(double scaleFactor) {
		for(int i=0; i<frontFaces.size(); i++) {
			Face faceFront = frontFaces.get(i);
			Face faceBottom = bottomFaces.get(i);
			faceFront.scale(scaleFactor);
			faceBottom.scale(scaleFactor);
		}
	}
	
	public void shift(Vector shiftVector) {
		for(int i=0; i<frontFaces.size(); i++) {
			Face faceFront = frontFaces.get(i);
			Face faceBottom = bottomFaces.get(i);
			faceFront.shift(shiftVector);
			faceBottom.shift(shiftVector);
		}
	}
	
	public Curve getOuterEdges() {
		
		HashMap<PointDuple,Integer> edges = new HashMap<>();
		//Detecting edges which are only visited once
		for(Face face : frontFaces) {
			Point[] vertices = face.getPointsAsArray();
			for(int i = 1; i<vertices.length; i++) {
				PointDuple edge = new PointDuple(vertices[i-1], vertices[i]);
				Integer val = edges.remove(edge);
				if(val==null)
					edges.put(edge, 1);
				else
					edges.put(edge, val+1);
			}
			PointDuple edge = new PointDuple(vertices[vertices.length-1], vertices[0]);
			Integer val = edges.remove(edge);
			if(val==null)
				edges.put(edge, 1);
			else
				edges.put(edge, val+1);
		}
		Map<Point,Point> onceVisitedEdges = edges.entrySet().stream().filter(entry -> entry.getValue()==1)
		                                                             .collect(Collectors.toMap((entry)-> entry.getKey().first, 
		                                                            		 	(entry)-> entry.getKey().second));
		//Assembling edges
		Curve curve = new Curve();
		System.out.println(onceVisitedEdges);
		Point p0 = onceVisitedEdges.keySet().iterator().next();
		
		Point currentPoint = p0;
		do {
			curve.addPoint(currentPoint);
			currentPoint = onceVisitedEdges.get(currentPoint);
			}while(!currentPoint.equals(p0));
		return curve;
	}
	
	@Override
	public ArrayList<Face> getFaces(){
		ArrayList<Face> faces = new ArrayList<Face>();
		faces.addAll(frontFaces);
		faces.addAll(bottomFaces);
		return faces;
	}
	
	public Surface duplicate() {
		ArrayList<Face> copyFaces = new ArrayList<>();
		copyFaces.addAll(frontFaces);
		return new Surface("Duplicated Surface", copyFaces);
	}
	
	public String toString() {
		return "[Surface : " +getName() + " ]";
	}
	
}
