package editor.gui;

import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.apache.commons.collections15.Transformer;

import editor.gui.Editor.ElementType;
import editor.listeners.GraphListener;
import editor.schema.Edge;
import editor.schema.Model;
import editor.schema.Vertex;
import edu.uci.ics.jung.algorithms.layout.AbstractLayout;
import edu.uci.ics.jung.algorithms.layout.StaticLayout;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.PickingGraphMousePlugin;
//import edu.uci.ics.jung.visualization.control.PluggableGraphMouse;
import edu.uci.ics.jung.visualization.decorators.EdgeShape;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Paint;
import java.awt.Stroke;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Collection;
import java.util.LinkedHashMap;
import edu.uci.ics.jung.visualization.picking.PickedState;
import edu.uci.ics.jung.visualization.renderers.DefaultEdgeLabelRenderer;
import edu.uci.ics.jung.visualization.renderers.Renderer;	// for dome reason it was not suggested by eclipse ide ????


public class GraphDisplay extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SparseMultigraph<Integer, Integer> graph = null;
	private AbstractLayout<Integer, Integer> layout = null;
	private VisualizationViewer<Integer, Integer> visual = null;
	
	private GraphListener graphListener = new GraphListener();
//	private PluggableGraphMouse graphMouse = new PluggableGraphMouse();
	private DefaultModalGraphMouse<Integer, Integer> graphMouse = new DefaultModalGraphMouse<Integer, Integer>();
	private PickingGraphMousePlugin<Integer, Integer> pickingPlugin = new PickingGraphMousePlugin<>();
	
	private LinkedHashMap<Integer, Paint> vertexColorMap = new LinkedHashMap<>();

	
	//	ScalingControl scaler = new CrossoverScalingControl();
	
	private boolean picking = false;
	
	private boolean foundState = false;
	
	public GraphDisplay() {
		super();
		setupPanel();
	}

	private void setupPanel() {
		setLayout(new BorderLayout(0, 0));
		setBackground(Color.LIGHT_GRAY);
	}
	
	/**
	 * initializes graph,layout and visualizer to display the graph and add the display to the panel
	 * if a user model is supplied initializes the graph from the user model , if no user model is supllied 
	 * initialize an empty graph
	 * @param um	a user model to initialize the graph with
	 * @return		true if the graph was initialized
	 */
	public boolean openGraph(Model um){
		if(graph == null){
			// open a new empty graph ( if no user model is chosen)
			graph = new SparseMultigraph<>();
			layout = new StaticLayout<>(graph);
			visual = new VisualizationViewer<>(layout);
			
			setupVisualization();
			
			this.add(visual);
			
			// if a user model is chosen then build the graph from the user model
			if(um != null){
				// insert the vertices of the user model into the graph
				for(Vertex vertex : um.getVertexMap().values() ){
					this.insertVertex(vertex);
				}
				// insert the edges of the user model into the graph
				for(Edge edge : um.getEdgeMap().values()){
					this.insertEdge(edge);
				}
			} 
			return true;		
		} else {
			// for now do nothing , as there is no current support for editing of multiple user models simultaneously 
			JOptionPane.showMessageDialog(null, "The Editor currently does not support multiple um/graph editing ");
		}
		
		return false;
	}
	
	/**
	 * remove the VisualizationViewer (the component that displays the graph) from the panel 
	 */
	public void closeGraph(){
		
		
//		if(graph!=null){
//			this.remove(visual);
//		}

		picking = false;
		foundState = false;	
		
		graphListener = null;
		pickingPlugin = null;
		graphMouse = null;
		visual = null;
		layout = null;
		graph = null;
		vertexColorMap = null;

	}
	
	/**
	 * set the look of the component which displays the graph - the VisualizationViewer 
	 */
	public void setupVisualization(){
		
		if(visual != null){
			visual.setBackground(Color.white);
			visual.setGraphMouse(graphMouse);
			setupLabels();	
			setupVertexDisplay();
			setupEdgeDisplay();
			
			visual.addMouseListener(graphListener);	
			
			visual.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent key) {
					if((key.getKeyCode() == KeyEvent.VK_ESCAPE)){
						Editor.getInstance().escapeKey();
					}
				}
			});	
			
			
			
			// Create a graph mouse and add it to the visualization component
//			DefaultModalGraphMouse gm = new DefaultModalGraphMouse();
//			gm.setMode(ModalGraphMouse.Mode.TRANSFORMING);
//			vv.setGraphMouse(gm);
		}

	}
	
	private void setupLabels() {
		
		// set Label/title display of vertices
		visual.getRenderer().getVertexLabelRenderer().setPosition(Renderer.VertexLabel.Position.N);
		
		Transformer<Integer, String> vertexLabller = new Transformer<Integer, String>() {
			@Override
			public String transform(Integer vId) {
				if (Editor.getInstance().isEditingUserModel()) {
					Model um = Editor.getInstance().getUserModel();
					return um.getVertexMap().get(vId).getLabel();
				} else if (Editor.getInstance().isEditingGroupModel()) {
					Model gm = Editor.getInstance().getGroupModel();
					return gm.getVertexMap().get(vId).getLabel();
				} else {
					return "";
				}
			}
		};
		visual.getRenderContext().setVertexLabelTransformer(vertexLabller);
		
		// set Label/title display of edges
			
		Transformer<Integer, String> edgeLabller = new Transformer<Integer, String>() {
			@Override
			public String transform(Integer eId) {
				if (architectureUtil.IBUMSetup.getAllowLinkLabels()){
					if (Editor.getInstance().isEditingUserModel()) {
						Model um = Editor.getInstance().getUserModel();
						return um.getEdgeMap().get(eId).getLabel();
					} else if (Editor.getInstance().isEditingGroupModel()) {
						Model gm = Editor.getInstance().getGroupModel();
						return gm.getEdgeMap().get(eId).getLabel();
					} else {
						return "";
					}
				} else {
					return "";
				}
			}
		};
		visual.getRenderContext().setEdgeLabelTransformer(edgeLabller);
		
	}
	
	
	
	

	
	public void setupVertexDisplay(){
		
		Transformer<Integer, Paint> vertexColorPainter = new Transformer<Integer, Paint>() {

			@Override
			public Paint transform(Integer vertexId) {
				Vertex vertex = new Vertex();
				if (Editor.getInstance().isEditingUserModel()) {
					vertex = Editor.getInstance().getUserModel().getVertexMap().get(vertexId);
				} else 	if (Editor.getInstance().isEditingGroupModel()) {
					vertex = Editor.getInstance().getGroupModel().getVertexMap().get(vertexId);
				}
				Paint color = null;
				ElementType type = vertex.getType();
				final PickedState<Integer> pickedState = visual.getPickedVertexState();
				
				if (pickedState.isPicked(vertexId)) {
					color = Color.yellow;
				} else if (type == ElementType.INFOBEAD){
					if (Editor.getInstance().isSensor(vertexId)){
						color = Color.green;
					} else if (Editor.getInstance().isService(vertexId)){
						color = Color.red;
					} else { // a regular info-bead
						color = Color.blue;
					}
				} else {
					color = Color.black;
				}
				vertexColorMap.put(vertexId, color);
			
				return color;
			}
		}; 
		visual.getRenderContext().setVertexFillPaintTransformer(vertexColorPainter);
	}
	
	
	
	public void setFoundColor (Integer vertexId, final Boolean foundIt){
		
		if (foundIt){
			vertexColorMap.put(vertexId, Color.orange);
		}
					
				
		Transformer<Integer, Paint> findSelectionColor = new Transformer<Integer, Paint>() {

			@Override
	        public Paint transform (Integer vertexId) {
				
				Paint color = vertexColorMap.get(vertexId);

				return color;
			}
    	};
		visual.getRenderContext().setVertexFillPaintTransformer(findSelectionColor);
    }

	    

public void setupEdgeColor(){
		
		Transformer<Integer, Paint> edgexColorPainter = new Transformer<Integer, Paint>() {

			@Override
			public Paint transform(Integer edgeId) {
				Edge edge = new Edge();
				if (Editor.getInstance().isEditingUserModel()) {
					edge = Editor.getInstance().getUserModel().getEdgeMap().get(edgeId);
				} else 	if (Editor.getInstance().isEditingGroupModel()) {
					edge = Editor.getInstance().getGroupModel().getEdgeMap().get(edgeId);
				}
				
				Paint color = null;
				EdgeType type = edge.getType();
				final PickedState<Integer> pickedState = visual.getPickedEdgeState();
				if (pickedState.isPicked(edgeId)) {
					color = Color.RED;
					visual.getRenderContext().setEdgeLabelRenderer(new MyDefaultEdgeLaberRenderer(Color.GRAY, Color.RED));
				} else if (type.equals(EdgeType.DIRECTED)){
						color = Color.BLACK;
						visual.getRenderContext().setEdgeLabelRenderer(new MyDefaultEdgeLaberRenderer(Color.BLACK, Color.RED));
				} else if (type.equals(EdgeType.UNDIRECTED)){
						color = Color.LIGHT_GRAY;
						visual.getRenderContext().setEdgeLabelRenderer(new MyDefaultEdgeLaberRenderer(Color.LIGHT_GRAY, Color.RED));
				}
				return color;
			}
		}; 
		visual.getRenderContext().setEdgeDrawPaintTransformer(edgexColorPainter);
		visual.getRenderContext().setArrowDrawPaintTransformer(edgexColorPainter);
		visual.getRenderContext().setArrowFillPaintTransformer(edgexColorPainter);


	}



	public class MyDefaultEdgeLaberRenderer extends DefaultEdgeLabelRenderer {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		protected Color unpickedEdgeLabelColor = Color.GREEN;
		protected Color pickedEdgeLabelColor = Color.RED;
		
		
	    public MyDefaultEdgeLaberRenderer(Color unpickedEdgeLabelColor, Color pickedEdgeLabelColor)
	    {
	        super(pickedEdgeLabelColor);
	        this.unpickedEdgeLabelColor = unpickedEdgeLabelColor;
	        this.pickedEdgeLabelColor = pickedEdgeLabelColor;
	    }
	
	    public <E> Component getEdgeLabelRendererComponent(JComponent vv, Object value, Font font, boolean isSelected, E edge)
	    {
	        super.setForeground(unpickedEdgeLabelColor);
	        if (isSelected) setForeground(pickedEdgeLabelColor);
	        super.setBackground(vv.getBackground());
	
	        if (font != null)
	        {
	            setFont(font);
	        }
	        else
	        {
	            setFont(vv.getFont());
	        }
	        setIcon(null);
	        setBorder(noFocusBorder);
	        setValue(value);
	        return this;
	    }
	}
		
	
	private void setupEdgeDisplay() {
		// display UNDIRECTED edges with STRIPED strokes (zebra)
				float dash[] = {10.0f,5.0f};
				final Stroke undirectedStroke = new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f);
				final Stroke directedStroke = new BasicStroke(1.0f);
				
				Transformer<Integer, Stroke> edgeStrokeTransformer =	new Transformer<Integer, Stroke>() {
					@Override
					public Stroke transform(Integer eId) {
					Edge edge = new Edge();
						if (Editor.getInstance().isEditingUserModel()) {
							edge = Editor.getInstance().getUserModel().getEdgeMap().get(eId);
						} else 	if (Editor.getInstance().isEditingGroupModel()) {
							edge = Editor.getInstance().getGroupModel().getEdgeMap().get(eId);
						}

						if(edge.isDirected()) {
							return directedStroke;	
						} else {
							return undirectedStroke;
						}
					} 
				};
				visual.getRenderContext().setEdgeStrokeTransformer(edgeStrokeTransformer);
				
		
				// make edges as lines
				visual.getRenderContext().setEdgeShapeTransformer(new EdgeShape.Line<Integer,Integer>());
				
				
				setupEdgeColor();
	}
	
	
	
	/**
	 * get a vertex and insert it to the graph
	 * @param vertex	chosen vertex to be added to the graph
	 * @return			true if the vertex was inserted / false if it was not inserted into the graph
	 */
	public void insertVertex(Vertex vertex){
		// check that the graph is actually instantiated
		if(graph!=null){
			// add the vertex to the graph
			graph.addVertex(vertex.getId());
			
			// set the vertex coordinates/point in the layout of the graph
			layout.setLocation(vertex.getId(), vertex.getX(), vertex.getY());
			
		}
		
	}

	

	
	/**
	 * get an edge and insert it to the graph
	 * @param edge		chosen edge to be added to the graph
	 * @return			true if the edge was inserted / false if it was not inserted into the graph
	 */
	public boolean insertEdge(Edge edge){
		// check that the graph is actually instantiated
		if(graph != null){
			graph.addEdge(edge.getId(), edge.getSourceId(), edge.getTargetId(), EdgeType.DIRECTED);
			return true;
		}
		return false;
	}
	
	
	/**
	 * get an edge and remove it from the graph
	 * @param edge		chosen edge to be removed from the graph
	 * @return			true if the edge was removed / false if it was not removed from the graph
	 */
	public boolean removeEdge(Integer edgeId){
		// check that the graph is actually instantiated
		if(graph != null){
			graph.removeEdge(edgeId);
			return true;
		}
		return false;
	}
	
	public boolean isInitialized(){
		if(graph == null){
			return false;
		} else 
			return true;
	}

	
	public void addPick(){
		if(!picking){
			graphMouse.add(pickingPlugin);
			visual.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			picking = true;
		}
	}
	public void removePick(){
		if(picking){
			graphMouse.remove(pickingPlugin);
			visual.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
			picking = false;
		}
	}
	
	

	

		
	/*
	 * Selects a vertex in the graph and returns its ID
	 * @return vertexID the vertex ID or -1 if it is not a vertex
	 */
	public Integer getPickedVertex(){
		Integer vertexID = Editor.getInstance().getLastPickedVertexID();
 		final PickedState<Integer> pickedState = visual.getPickedVertexState();
		if (graph!=null && pickedState!= null && pickedState.isPicked(vertexID)){
//			getVisual().repaint();
			return vertexID;
		} else  {
			return -1;
		}
	}
	
	
	
	/* 
	 * return all edges connected to vertex ID
	 */
	public Collection<Integer> getVertexEdges (Integer vertexId){
		return graph.getIncidentEdges(vertexId);
	}
	

	/*
	 *  Removes a vertex and all its edges from the graph
	 *  @param vertexID the id of the vertex to be deleted
	 *  @return vertexID the vertex ID or -1 if the graph does not exist
	 */
		public Integer removeVertexFromGraph(Integer vertexID){
		
			if (graph!=null)
			{
				Collection<Integer> vertexEdgesCollection = graph.getIncidentEdges(vertexID);
				if (vertexEdgesCollection != null){
					for (Integer linkID : vertexEdgesCollection){
						graph.removeEdge(linkID);
					}
				}
				graph.removeVertex(vertexID);
				getVisual().repaint();
				return vertexID;

			} else {
				return -1;
			}
			
			
		}
	
	
	
		
/*
 *  Removes a selected vertex and all its edges from the graph
 *  @return vertexID the vertex ID or -1 if it is not a vertex or operation could not be completed
 */
	public Integer removeVertexFromGraph(){
	
		Integer vertexID = Editor.getInstance().getLastPickedVertexID();
		final PickedState<Integer> pickedState = visual.getPickedVertexState();
		if (pickedState.isPicked(vertexID))
		{
			vertexID=removeVertexFromGraph(vertexID);
			
			return vertexID;

		} else {
			return -1;
		}
		
		
	}
		
		
	
	/* ~~~~~~~~~~~~~~~~ getters & setters ~~~~~~~~~~~~~~~~ */
			
	public VisualizationViewer<Integer, Integer> getVisual() {
		return visual;
	}

	public void updateCoordinates(Model um){
		if(um != null && um.getVertexMap()!=null && graph!= null && graph.getVertices() != null){
			
			for(Integer vertexId : graph.getVertices()){
				um.getVertexMap().get(vertexId).setLocation(layout.getX(vertexId),layout.getY(vertexId));
			}
			
		}
	}
	
	
	
		

	public boolean isFoundState() {
		return foundState;
	}

	public void setFoundState(boolean findState) {
		this.foundState = findState;
	}

}




