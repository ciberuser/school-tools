package editor.factory;

import editor.schema.Edge;

public class EdgeFactory {

	private static int counter = 0;
	private static EdgeFactory instance = new EdgeFactory();
	
	private EdgeFactory() {	
	}

    public static EdgeFactory getInstance() {
        return instance;
    }
	
    public void resetLinkCount() {
    	counter = 0;
    }
    
    public void setLinkCount(int count){
    	counter = count;
    }

    public Edge create(int from,int to, String linkName){
    	
    	Edge edge = new Edge(counter, from, to, linkName);
    	counter +=1;
    	return edge;
    	
    }
}
