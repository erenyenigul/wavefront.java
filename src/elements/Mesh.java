package elements;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Mesh extends GeoElement{

	private ArrayList<Face> faces;
	private String name;
	
	public Mesh(String name) {
		faces = new ArrayList<>();
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void addAll(ArrayList<Face> faces) {
			this.faces.addAll(faces);
	}
	
	public void addFace(Face f) {
		this.faces.add(f);
	}
	
	public boolean removeFace(Face face) {
		return faces.remove(face);
	}
	
	//This method is implemented for rendering surfaces. This method below is implemented differently in Surface class
	//as it has two different containers for faces (One for front one for bottom faces).
	public ArrayList<Face> getFaces(){
		return this.faces;
	}
	
	public void saveAsObjFile() {
		HashMap<Point,Integer> renderedVerticesMap = new HashMap<>();
		StringBuilder renderedVertices = new StringBuilder();
		StringBuilder renderedFaces = new StringBuilder();

		for(Face face : getFaces()) {
			LinkedList<Integer> verticesOfFace = new LinkedList<Integer>();
			
			for(Point p : face.getPoints()) {
				if(renderedVerticesMap.containsKey(p)) {
					verticesOfFace.add(renderedVerticesMap.get(p));
				
				}else {
					int index = renderedVerticesMap.size()+1;
					renderedVerticesMap.put(p, index);
					renderedVertices.append("v "+p.getX() + " " + p.getY() +" " + p.getZ() + "\n");
					
					verticesOfFace.add(index);
				}
			}
			
			renderedFaces.append("f ");
			for(int v : verticesOfFace) {
				renderedFaces.append(v+" ");
			}
			renderedFaces.append("\n");
			
			
			File objFile = new File(name+".obj");
			
			try(FileWriter fileWriter = new FileWriter(objFile)) {
				fileWriter.write(renderedVertices.toString() + renderedFaces.toString());
				fileWriter.close();
			} catch (IOException e) {
			    // Cxception handling
			}
		}
		
	}

	public String toString() {
		StringBuilder allInfo = new StringBuilder();
		
		allInfo.append("--Mesh named \""+name+"\" with following elements : \n");
		for(Face f : getFaces())
		  allInfo.append("--"+ f + "\n");
	
		return allInfo.toString();
	}
	
}
