package editor.factory;

import java.awt.Point;

import editor.gui.Editor.ElementType;
import editor.gui.ElementList;
import editor.metadata.CustomMetadata;
import editor.schema.Vertex;

public class VertexFactory {
	
	private static int counter = 0;
	private static VertexFactory instance = new VertexFactory();
	
	private VertexFactory(){
	}
	
	public static VertexFactory getInstance() {
		return instance;
	}
	
    public void resetVertexCount() {
    	counter = 0;
    }
    
    public void setVertexCount(int count) {
     	counter = count;
    }
    
    
    public Vertex create(String name, ElementList source, Point point){
    	CustomMetadata metadata = source.getMetadataMap().get(name);
    	Vertex vertex = new Vertex(counter, source.getType(), name, metadata.getInputInterfaces(), metadata.getOutputInterface());
    	
    	counter += 1;
    	vertex.setLocation(point);
    	    	
    	return vertex;
    }


	public Vertex copyAndCreate(String name, String[] metadataInput, String[] metadataOutput, ElementType type, Point point){
	
		Vertex vertex = new Vertex(counter, type, name, metadataInput, metadataOutput);
		
		counter += 1;
		
		vertex.setLocation(point);
		    	
		return vertex;
	}
}

