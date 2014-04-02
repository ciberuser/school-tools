package editor.schema;

import edu.uci.ics.jung.graph.util.EdgeType;

/**
 * used to hold links (possible & established ) between vertices/elements(infoBeads/sensors/services) 
 */
public class Edge {
	private int id;
	private String label;
	private EdgeType type = EdgeType.UNDIRECTED;
	private String linkName;
	private int sourceId;
	private int targetId;
	
	/** default empty constructor */
	public Edge() {
	}
	
	/**
	 * constructor
	 * @param id - edge id
	 * @param sourceId - id of the source vertex
	 * @param targetId - id of the target vertex
	 * @param linkName - implementing link-interface_name
	 */
	public Edge(int id, int sourceId, int targetId, String linkName) {
		this.id = id;
		this.sourceId = sourceId;
		this.targetId = targetId;
		this.linkName = linkName;
		this.label = linkName + " (" + sourceId +"," + targetId + ")" ;
	}
	
	public boolean isDirected(){
		if(type.equals(EdgeType.DIRECTED))
			return true;
		else
			return false;
	}
	
	/** DIRECTED <==> UNDIRECTED*/
	public void changeType() {
		if(type.equals(EdgeType.UNDIRECTED)) {
				type = EdgeType.DIRECTED;
		}
		else {
				type = EdgeType.UNDIRECTED;
		}
	}
	
	/* ~~~~~~~~~~~~~~~~ getters & setters ~~~~~~~~~~~~~~~~ */

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public EdgeType getType() {
		return type;
	}

	public void setType(EdgeType type) {
		this.type = type;
	}

	public String getLinkName() {
		return linkName;
	}

	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}

	public int getSourceId() {
		return sourceId;
	}

	public void setSourceId(int sourceId) {
		this.sourceId = sourceId;
	}

	public int getTargetId() {
		return targetId;
	}

	public void setTargetId(int targetId) {
		this.targetId = targetId;
	}
	
	
}
