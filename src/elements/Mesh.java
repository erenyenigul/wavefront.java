package elements;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Mesh extends GeoElement{

	private ArrayList<Surface> surfaces;
	private String name;
	
	public Mesh(String name) {
		surfaces = new ArrayList<>();
		this.name = name;
	}
	
	public void add(Surface... surfaces ) {
		for(Surface s: surfaces) {
		   this.surfaces.add(s);
		}
	}
	
	public boolean remove(Surface surface) {
		return surfaces.remove(surface);
	}
	
	public void saveAsObjFile() {
		HashMap<Point,Integer> renderedVerticesMap = new HashMap<>();
		StringBuilder renderedVertices = new StringBuilder();
		StringBuilder renderedFaces = new StringBuilder();

		ArrayList<Face>  faces = getAllFaces();
		
		for(Face face : faces) {
			LinkedList<Integer> verticesOfFace = new LinkedList<Integer>();
			
			for(Point p : face.getVertices()) {
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

	
	private ArrayList<Face> getAllFaces(){
		ArrayList<Face> faces = new ArrayList<Face>();
		for(Surface s : surfaces) {
			faces.addAll(s.getFaces());
		}
		return faces;
	}
	
	public String info() {
		StringBuilder allInfo = new StringBuilder();
		
		allInfo.append("--Mesh named \""+name+"\" with following elements : \n");
		for(Face f : getAllFaces())
		  allInfo.append("--"+ f.info() + "\n");
	
		return allInfo.toString();
	}
	
}
