package editor.listeners;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;

import editor.gui.Editor;
import edu.uci.ics.jung.algorithms.layout.GraphElementAccessor;
import edu.uci.ics.jung.visualization.VisualizationViewer;

public class GraphListener extends MouseAdapter{
	
	
	// request focus
	@Override
	public void mouseEntered(MouseEvent event) {
		try{
			@SuppressWarnings("unchecked")
			VisualizationViewer<Integer, Integer> visual = (VisualizationViewer<Integer, Integer>) event.getSource();
			visual.requestFocus();
			visual.repaint(); // @try@ see if it refreshes faster ...
		} catch (ClassCastException e) {
			// this exception should be ignored - caused when listener closes and previous events are not cleared
			return;
		}
	}
	
	
	@Override
	public void mouseClicked(MouseEvent event) {
		try{
			@SuppressWarnings("unchecked")
			VisualizationViewer<Integer, Integer> visual = (VisualizationViewer<Integer, Integer>) event.getSource();
			GraphElementAccessor<Integer, Integer> pickSupport = visual.getPickSupport();
			Point point = event.getPoint();
			
			if(SwingUtilities.isLeftMouseButton(event)){
				if(Editor.getInstance().isEditingMode()){
					Editor.getInstance().addSwitch(point);
				} else {
					if(pickSupport != null){
						Integer eId = pickSupport.getEdge(visual.getGraphLayout(), point.getX(), point.getY());
						Integer vId = pickSupport.getVertex(visual.getGraphLayout(), point.getX(), point.getY());
						if(eId!=null && vId == null){
							Editor.getInstance().changeLink(eId);
						} 
						if(vId != null){
							Editor.getInstance().setLastPickedVertexID(vId);
							// update data on the status bar
							Editor.getInstance().showStatusBar(vId);
						} else {
							Editor.getInstance().showStatusBar(-1);
						}
					}
				}
			}
		} catch (ClassCastException e) {
			// this exception should be ignored - caused when listener closes and previous events are not cleared
			return;
		}
	}
	

}
