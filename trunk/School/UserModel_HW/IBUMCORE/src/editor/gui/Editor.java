package editor.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import modelExport.GenerateModel;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import architectureUtil.DirectoriesSetup;
import architectureUtil.IBUMSetup;

import editor.factory.EdgeFactory;
import editor.factory.ModelFactory;
import editor.factory.VertexFactory;
import editor.listeners.GraphListener;
import editor.listeners.MenuListener;
import editor.metadata.CustomMetadata;
import editor.schema.Edge;
import editor.schema.GroupModel;
import editor.schema.GroupModel.ModelType;
import editor.schema.InfoPendant;
import editor.schema.Model;
import editor.schema.UserModel;
import editor.schema.Vertex;
import editor.schema.ModelConversions;
import edu.uci.ics.jung.graph.util.EdgeType;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;

/**
 * the main frame/window for the editor , contains all the gui components .....
 * 
 * 
 */
public class Editor extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public enum ElementType {
		INFOBEAD, INFOPENDANT, USERMODEL, GROUPMODEL
	}

	public enum Mode {
		BLANK, PICKING, EDITING, TRANSFORMING
	}

	public enum EditedModelType {
		USERMODEL, GROUPMODEL
	}

	private static Editor instance = new Editor();

	private static Boolean closingState = false;

	// keeps the last picked vertex
	private static Integer lastPickedVertexID = -1;

	private static Integer infoBeadClipBoard = null;

	private static File workingUMFile = null;
	
	private static DirectoriesSetup dSetup = new  DirectoriesSetup();
	private static IBUMSetup ibumSetup = new IBUMSetup();

	// The state of the model - whether it is a group model that contains sub
	// models (user or group sub model)
	// or a user model

	private static EditedModelType editedModeType = EditedModelType.USERMODEL;
	
	public Boolean isEditingUserModel() {
		if (getEditedModeType().equals(EditedModelType.USERMODEL)) {
			return true;
		} else {
			return false;
		}
	}

	public Boolean isEditingGroupModel() {
		if (getEditedModeType().equals(EditedModelType.GROUPMODEL)) {
			return true;
		} else {
			return false;
		}
	}
	
	public class StatusBar extends JLabel {

		/**
		 * Status bar pane
		 */
		
		private static final long serialVersionUID = 1L;

		/** Creates a new instance of StatusBar */
	    public StatusBar() {
	        super();
	        super.setPreferredSize(new Dimension(500, 16));
	        setMessage("Info-bead Editor status bar:");
	    }

	    public void setMessage(String message) {
	        setText(" "+message);        
	    }        
	}

	// current gui .....
	private JPanel contentPane;
	private JSplitPane split1;
	private JTabbedPane tabs;
	private StatusBar statusBar;
	private InfoPanel infoPanel;


	// status
	private Mode mode = Mode.BLANK;
	private String selected = null;
	private String listItemSelected = null;
	private ElementList source = null;
	private UserModel um = null; // holds the user model and graph parts
	private GroupModel gm = null; // holds the group model parts
	private static ModelConversions convert = new ModelConversions();

	/* === lists === */

	private ElementList infoBeads;
	private ElementList infoPendants;
	private ElementList userModels;
	private ElementList groupModels;

	/*
	 * === graph display : a container for the graph visualizer & the graph and
	 * its layout ====
	 */

	private GraphDisplay graphDisplay;

	/* === menus === */

	private JMenuBar menuBar;

	// Menu bar content
	private JMenu menuFile;
	private JMenu menuEdit;
	private JMenu menuView;
	private JMenu menuRun;
	private JMenu menuHelp;

	// file Menu
	private JMenuItem fileMenuItemNewUserModel;
	private JMenuItem fileMenuItemNewGroupModel;
	private JMenuItem fileMenuItemOpenUserModel;
	private JMenuItem fileMenuItemOpenGroupModel;
	private JMenuItem fileMenuItemClose;
//	private JMenuItem fileMenuItemNewInfoBead;
	private JMenuItem fileMenuItemSave;
	private JMenuItem fileMenuItemSaveAs;
	private JMenuItem fileMenuItemSaveInfoPendantAs;
	private JMenuItem fileMenuItemDeleteModelFromList;
	private JMenuItem fileMenuItemExit;

	// Edit Menu
	private JMenuItem editMenuItemResetIBColors;
	private JMenuItem editMenuItemFindIB;
	private JMenuItem editMenuItemShowIP;
	private JMenuItem editMenuItemShowSubModel;
	private JMenuItem editMenuItemCopy;
	private JMenuItem editMenuItemPaste;
	private JMenuItem editMenuItemDelete;
	private JMenuItem editMenuDeleteSubModelFromGroupModel;

	// View Menu
	private JMenuItem viewMenuItemZoomIn;
	private JMenuItem viewMenuItemZoomOut;
	private JMenuItem viewMenuItemPan;
	private JMenuItem viewMenuItemRotate;
	private JMenuItem viewMenuItemMoveInfobead;

	// Run Menu
	private JMenuItem runMenuActivateModel;

	//  Menu
	private JMenuItem helpMenuIntro;
	private JMenuItem helpMenuInfoBeadHelp;
	private JMenuItem helpMenuAbout;

	public static Editor getInstance() {
		return instance;
	}

	/**
	 * Create the frame.
	 */
	private Editor() {

		// set the look and feel (laf) of the application/editor
		try {
			String className = Editor.getLookAndFeelClassName("Nimbus");
			UIManager.setLookAndFeel(className);
		} catch (Exception e) {
			e.printStackTrace();
		}

		setupFrame();
		setupLists();
		setupGraphDisplay();
		setupMenus();
		setTitle("Info-bead User Model Editor");
				
		

		// current gui

		tabs = new JTabbedPane(JTabbedPane.TOP);
		split1 = new JSplitPane();
		split1.setResizeWeight(0.1);

		JScrollPane scrollpaneInfoBeads = new JScrollPane(infoBeads,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		JScrollPane scrollpaneInfoPendants = new JScrollPane(infoPendants,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		JScrollPane scrollpaneUserModels = new JScrollPane(userModels,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		JScrollPane scrollpaneGroupModels = new JScrollPane(groupModels,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		tabs.addTab("Info-beads", null, scrollpaneInfoBeads, null);
		tabs.addTab("InfoPendants", null, scrollpaneInfoPendants, null);
		tabs.addTab("User Models", null, scrollpaneUserModels, null);
		tabs.addTab("Group Models", null, scrollpaneGroupModels, null);

		split1.setRightComponent(graphDisplay);
		split1.setLeftComponent(tabs);
		contentPane.add(split1, BorderLayout.CENTER);
		
	}

	/*
	 * === private .... setup methods
	 * ============================================
	 * ================================================
	 */

	private void setupFrame() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		// setup infoBead status bar
		statusBar = new StatusBar();
		contentPane.add(statusBar, BorderLayout.NORTH);
		
		// setup logging & information console panel
		infoPanel = new InfoPanel();
		contentPane.add(infoPanel, BorderLayout.SOUTH);
		
		


	}

	private void setupMenus() {

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		// ----------- File Menu ----------------

		menuFile = new JMenu("File");
		menuBar.add(menuFile);

		fileMenuItemNewUserModel = new JMenuItem("New user model");
		fileMenuItemNewUserModel.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_U, InputEvent.CTRL_MASK));
		menuFile.add(fileMenuItemNewUserModel);

		fileMenuItemNewGroupModel = new JMenuItem("New group model");
		fileMenuItemNewGroupModel.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_G, InputEvent.CTRL_MASK));
		menuFile.add(fileMenuItemNewGroupModel);

		fileMenuItemOpenUserModel = new JMenuItem("Open user model");
		menuFile.add(fileMenuItemOpenUserModel);

		fileMenuItemOpenGroupModel = new JMenuItem("Open group model");
		menuFile.add(fileMenuItemOpenGroupModel);

		menuFile.addSeparator();

		fileMenuItemClose = new JMenuItem("Close");
		menuFile.add(fileMenuItemClose);

		menuFile.addSeparator();

//		fileMenuItemNewInfoBead = new JMenuItem("Import info-bead");
//		menuFile.add(fileMenuItemNewInfoBead);
//
//		menuFile.addSeparator();

		fileMenuItemSave = new JMenuItem("Save");
		fileMenuItemSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				InputEvent.CTRL_MASK));
		menuFile.add(fileMenuItemSave);

		fileMenuItemSaveAs = new JMenuItem("Save model as");
		menuFile.add(fileMenuItemSaveAs);

		fileMenuItemSaveInfoPendantAs = new JMenuItem("Save info-pendant as");
		menuFile.add(fileMenuItemSaveInfoPendantAs);

		menuFile.addSeparator();

		fileMenuItemDeleteModelFromList = new JMenuItem("Delete model or info-pendant from list");
		menuFile.add(fileMenuItemDeleteModelFromList);

		menuFile.addSeparator();
		
		
		fileMenuItemExit = new JMenuItem("Exit");
		fileMenuItemExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,
				InputEvent.CTRL_MASK));
		menuFile.add(fileMenuItemExit);

		// ------------- Edit Menu --------------------

		menuEdit = new JMenu("Edit");
		menuBar.add(menuEdit);

		editMenuItemResetIBColors = new JMenuItem("Reset info-bead colors");
		editMenuItemResetIBColors.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_R, InputEvent.CTRL_MASK));
		menuEdit.add(editMenuItemResetIBColors);

		editMenuItemFindIB = new JMenuItem("Find info-bead");
		editMenuItemFindIB.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,
				InputEvent.CTRL_MASK));
		menuEdit.add(editMenuItemFindIB);

		menuEdit.addSeparator();

		editMenuItemShowIP = new JMenuItem("Show info-pendant");
		menuEdit.add(editMenuItemShowIP);

		editMenuItemShowSubModel = new JMenuItem("Show sub-model");
		menuEdit.add(editMenuItemShowSubModel);

		menuEdit.addSeparator();

		editMenuItemCopy = new JMenuItem("Copy info-bead");
		editMenuItemCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,
				InputEvent.CTRL_MASK));
		menuEdit.add(editMenuItemCopy);

		editMenuItemPaste = new JMenuItem("Paste info-bead");
		editMenuItemPaste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,
				InputEvent.CTRL_MASK));
		menuEdit.add(editMenuItemPaste);

		editMenuItemDelete = new JMenuItem("Delete info-bead");
		editMenuItemDelete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,
				InputEvent.CTRL_MASK));
		menuEdit.add(editMenuItemDelete);

		menuEdit.addSeparator();

		// editMenuAddSubModelToGroupModel = new
		// JMenuItem("Add sub-model to group model");
		// menuEdit.add(editMenuAddSubModelToGroupModel);
		//
		editMenuDeleteSubModelFromGroupModel = new JMenuItem(
				"Delete sub-model from group model");
		menuEdit.add(editMenuDeleteSubModelFromGroupModel);

		// -------------- View Menu ------------------------

		menuView = new JMenu("View");
		menuBar.add(menuView);

		viewMenuItemZoomIn = new JMenuItem("Zoom in");
		menuView.add(viewMenuItemZoomIn);

		viewMenuItemZoomOut = new JMenuItem("Zoom out");
		menuView.add(viewMenuItemZoomOut);

		viewMenuItemPan = new JMenuItem("Pan");
		menuView.add(viewMenuItemPan);

		viewMenuItemRotate = new JMenuItem("Rotate");
		menuView.add(viewMenuItemRotate);

		viewMenuItemMoveInfobead = new JMenuItem("Move info-bead");
		menuView.add(viewMenuItemMoveInfobead);

		// -------------- Run Menu ------------------------

		menuRun = new JMenu("Run");
		menuBar.add(menuRun);

		runMenuActivateModel = new JMenuItem("Activate model");
		menuRun.add(runMenuActivateModel);

		// -------------- Help Menu ------------------------

		menuHelp = new JMenu("Help");
		menuBar.add(menuHelp);

		helpMenuIntro = new JMenuItem("Introduction to info-bead User Modeling");
		menuHelp.add(helpMenuIntro);

		helpMenuInfoBeadHelp = new JMenuItem("Info-bead help");
		helpMenuInfoBeadHelp.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_F1, InputEvent.CTRL_MASK));
		menuHelp.add(helpMenuInfoBeadHelp);

		helpMenuAbout = new JMenuItem("About the Editor");
		menuHelp.add(helpMenuAbout);

		// --------------- handle menus -----------------

		// File menu

		fileMenuItemClose.setEnabled(false);
		fileMenuItemSave.setEnabled(false);
		fileMenuItemSaveAs.setEnabled(false);
		fileMenuItemSaveInfoPendantAs.setEnabled(false);
		fileMenuItemDeleteModelFromList.setEnabled(false);

		MenuListener fileMenuListener = new MenuListener();
		fileMenuItemNewUserModel.addActionListener(fileMenuListener);
		fileMenuItemNewGroupModel.addActionListener(fileMenuListener);
		fileMenuItemOpenUserModel.addActionListener(fileMenuListener);
		fileMenuItemOpenGroupModel.addActionListener(fileMenuListener);
		fileMenuItemClose.addActionListener(fileMenuListener);
//		fileMenuItemNewInfoBead.addActionListener(fileMenuListener);
		fileMenuItemSave.addActionListener(fileMenuListener);
		fileMenuItemSaveAs.addActionListener(fileMenuListener);
		fileMenuItemSaveInfoPendantAs.addActionListener(fileMenuListener);
		fileMenuItemDeleteModelFromList.addActionListener(fileMenuListener);
		fileMenuItemExit.addActionListener(fileMenuListener);

		// Edit menu
		editMenuItemResetIBColors.setEnabled(false);
		editMenuItemFindIB.setEnabled(false);
		editMenuItemShowIP.setEnabled(false);
		editMenuItemShowSubModel.setEnabled(false);
		editMenuItemCopy.setEnabled(false);
		editMenuItemPaste.setEnabled(false);
		editMenuItemDelete.setEnabled(false);
		editMenuDeleteSubModelFromGroupModel.setEnabled(false);

		MenuListener editMenuListener = new MenuListener();
		editMenuItemResetIBColors.addActionListener(editMenuListener);
		editMenuItemFindIB.addActionListener(editMenuListener);
		editMenuItemShowIP.addActionListener(editMenuListener);
		editMenuItemShowSubModel.addActionListener(editMenuListener);
		editMenuItemCopy.addActionListener(editMenuListener);
		editMenuItemPaste.addActionListener(editMenuListener);
		editMenuItemDelete.addActionListener(editMenuListener);
		editMenuDeleteSubModelFromGroupModel
				.addActionListener(editMenuListener);

		// View Menu
		viewMenuItemZoomIn.setEnabled(false);
		viewMenuItemZoomOut.setEnabled(false);
		viewMenuItemPan.setEnabled(false);
		viewMenuItemRotate.setEnabled(false);
		viewMenuItemMoveInfobead.setEnabled(false);

		MenuListener viewMenuListener = new MenuListener();
		viewMenuItemZoomIn.addActionListener(viewMenuListener);
		viewMenuItemZoomOut.addActionListener(viewMenuListener);
		viewMenuItemPan.addActionListener(viewMenuListener);
		viewMenuItemRotate.addActionListener(viewMenuListener);
		viewMenuItemMoveInfobead.addActionListener(viewMenuListener);

		// Run Menu
		runMenuActivateModel.setEnabled(false);

		MenuListener runMenuListener = new MenuListener();
		runMenuActivateModel.addActionListener(runMenuListener);

		// Help Menu
		helpMenuIntro.setEnabled(true);
		helpMenuInfoBeadHelp.setEnabled(false);
		helpMenuAbout.setEnabled(true);

		MenuListener helpMenuListener = new MenuListener();
		helpMenuIntro.addActionListener(helpMenuListener);
		helpMenuInfoBeadHelp.addActionListener(helpMenuListener);
		helpMenuAbout.addActionListener(helpMenuListener);

	}

	private void setupLists() {

		infoBeads = new ElementList();
		infoBeads.initialize(ElementType.INFOBEAD);

		infoPendants = new ElementList();
		infoPendants.initialize(ElementType.INFOPENDANT);

		userModels = new ElementList();
		userModels.initialize(ElementType.USERMODEL);

		groupModels = new ElementList();
		groupModels.initialize(ElementType.GROUPMODEL);

	}

	private void setupGraphDisplay() {

		graphDisplay = new GraphDisplay();

	}

	/*
	 * === methods
	 * ==============================================================
	 * ==================================================
	 */

	/**
	 * Handles the selection from the lists of info-beads of all types
	 * 
	 * @param stay
	 *            the viewed list which should not be cleared
	 */
	public void clearSelection(ElementList stay) {
		if (stay == null) {
			infoBeads.clearSelection();
			infoPendants.clearSelection();
			userModels.clearSelection();
			groupModels.clearSelection();

		} else if (stay.getType() == ElementType.INFOBEAD) {
			infoPendants.clearSelection();
			userModels.clearSelection();
			groupModels.clearSelection();

		} else if (stay.getType() == ElementType.INFOPENDANT) {
			infoBeads.clearSelection();
			userModels.clearSelection();
			groupModels.clearSelection();

		} else if (stay.getType() == ElementType.USERMODEL) {
			infoBeads.clearSelection();
			infoPendants.clearSelection();
			groupModels.clearSelection();

		} else if (stay.getType() == ElementType.GROUPMODEL) {
			infoBeads.clearSelection();
			infoPendants.clearSelection();
			userModels.clearSelection();

		}

	}

	/**
	 * clears the current selected item in the list
	 */
	public void escapeKey() {
		clearSelection(null);
		source = null;
		selected = null;
		listItemSelected = null;

		if (isEditingMode()) {
			graphDisplay.addPick();
			mode = Mode.PICKING;
		}
	}

	/**
	 * Handles blank mode (no graph), picking mode (selecting ) and editing mode
	 * 
	 * @param source
	 * @param selected
	 */
	public void elementSelected(ElementList source, String selected) {
		if (isBlankMode()) {
			this.source = source;
			this.selected = selected;
			this.listItemSelected = selected;
			log("blank : selection of : " + selected);
		} else if (isPickingMode()) {
			this.source = source;
			this.selected = selected;
			this.listItemSelected = selected;
			graphDisplay.removePick();
			mode = Mode.EDITING;
			log("picking : selection of : " + selected);
		} else if (isEditingMode()) {
			this.source = source;
			this.selected = selected;
			this.listItemSelected = selected;
			clearSelection(source);
			// source.setSelectedValue(selected, true);
			log("edit : selection of : " + selected);
		}
	}

	/**
	 * Selects what action to do in response to the picked list
	 * 
	 * @param point
	 *            The point in the graph where the items or item should be
	 *            located
	 */
	public void addSwitch(Point point) {

		if (source.equals(infoBeads)) {
			addVertex(point);
		} else if (source.equals(infoPendants)) {
			addSubModel(point);
		} else if (source.equals(userModels)) {
			addSubModel(point);
		} else if (source.equals(groupModels)) {
			// do not allow loading group model if editing a user model
			if (getEditedModeType() == EditedModelType.USERMODEL) {
				JOptionPane.showMessageDialog(null, "Cannot access a group model from a usedr model");
				escapeKey();
			} else {
				addSubModel(point);
			}
		}
	}

	/**
	 * Add a vertex to the graph and a model id in editing mode
	 * 
	 * @Param point the point on the screen (x,y)
	 */
	public void addVertex(Point point) {

		if (isPickingMode()) {
			// do nothing
		} else if (isEditingMode()) {
			Vertex vertex = VertexFactory.getInstance().create(selected,
					source, point);
			log("vertex << " + vertex.toString() + ">> was created");
			clearSelection(null);
			graphDisplay.addPick();
			mode = Mode.PICKING;
			ArrayList<Edge> edges = new ArrayList<Edge>();

			// add the vertex into the model
			if (isEditingUserModel()) {
				edges = um.addVertex(vertex);
			} else if (isEditingGroupModel()) {
				edges = gm.addVertex(vertex);
				gm.addVertexToGroup(vertex.getId(), gm.getModelId());
			}

			// insert the vertex into the graph
			graphDisplay.insertVertex(vertex);

			// insert the edges into the graph
			if (edges != null) {
				for (Edge edge : edges) {
					graphDisplay.insertEdge(edge);
				}
			}
			graphDisplay.getVisual().repaint();

		}
	}

	/**
	 * Return the vertices colors to their original setup
	 */
	public void resetInfoBeadColors() {
		// reset colors
		graphDisplay.setupVertexDisplay();
		graphDisplay.getVisual().repaint();

	}

	/**
	 * Find all info-beads that have the input string in their name
	 */
	public void findInfoBead() {
		String stringToFind = new String();
		Integer vertexId;
		String vertexName = null;
		Vertex vertex = null;

		// reset colors
		graphDisplay.setupVertexDisplay();
		graphDisplay.getVisual().repaint();

		// coloring find
		stringToFind = JOptionPane
				.showInputDialog("Please enter an info-bead name or part of it");
		if (stringToFind != null  &&  ! stringToFind.isEmpty()) {
			if (isEditingUserModel()) {
				for (Integer infoBeadKey : um.getVertexMap().keySet()) {
					vertex = um.getVertexMap().get(infoBeadKey);
					vertexName = vertex.getName();
					vertexId = infoBeadKey;
					if (vertexName.contains(stringToFind)) {
						graphDisplay.setFoundColor(vertexId, true);
					} else if (vertexId != null) {

						graphDisplay.setFoundColor(vertexId, false);

					}

				}
			} else if (isEditingGroupModel()) {
				for (Integer infoBeadKey : gm.getVertexMap().keySet()) {
					vertex = gm.getVertexMap().get(infoBeadKey);
					vertexName = vertex.getName();
					vertexId = infoBeadKey;
					if (vertexName.contains(stringToFind)) {
						graphDisplay.setFoundColor(vertexId, true);
					} else if (vertexId != null) {

						graphDisplay.setFoundColor(vertexId, false);

					}

				}
			}
			graphDisplay.getVisual().repaint();
		}

	}

	/*
	 * Gets the cluster of the selected vertices connected by a DIRECTED edge
	 * 
	 * @param vertexID the selected vertex ID
	 * 
	 * @param direction true if forward search in the graph, and false if
	 * backward search
	 */
	public Set<Integer> getDirecrtedClusterOfVertex(Integer vertexID,
			Boolean forward) {

		Set<Integer> graphClusterIDs = new HashSet<Integer>();
		Collection<Integer> tempEdgesIds = new HashSet<Integer>();
		Collection<Integer> tempVerticesIds = new HashSet<Integer>();
		Edge tempEdge = new Edge();
		final boolean FORWARD = true;
		final boolean BACKWARD = false;

		if (vertexID != null && vertexID != -1) {

			graphClusterIDs.add(vertexID);

			// Forward search
			if (forward) {
				tempEdgesIds = graphDisplay.getVertexEdges(vertexID);
				for (Integer eId : tempEdgesIds) {
					if (isEditingUserModel()) {
						tempEdge = um.getEdgeMap().get(eId);
					} else if (isEditingGroupModel()) {
						tempEdge = gm.getEdgeMap().get(eId);
					}
					if (tempEdge.isDirected()) {
						if (tempEdge.getSourceId() == vertexID) {
							tempVerticesIds = getDirecrtedClusterOfVertex(
									tempEdge.getTargetId(), FORWARD);
							graphClusterIDs.addAll(tempVerticesIds);
						}
					}
				}
			} else {
				// Backward search
				tempEdgesIds = graphDisplay.getVertexEdges(vertexID);
				for (Integer eId : tempEdgesIds) {
					if (isEditingUserModel()) {
						tempEdge = um.getEdgeMap().get(eId);
					} else if (isEditingGroupModel()) {
						tempEdge = gm.getEdgeMap().get(eId);
					}

					if (tempEdge.isDirected()) {
						if (tempEdge.getTargetId() == vertexID) {
							tempVerticesIds = getDirecrtedClusterOfVertex(
									tempEdge.getSourceId(), BACKWARD);
							graphClusterIDs.addAll(tempVerticesIds);
						}
					}
				}
			}

			return graphClusterIDs;
		} else {
			return null;
		}
	}

	/**
	 * Shows the info-pendant which is the cluster of the selected info bead
	 * 
	 * @return a set of all indexes of vertices in the info-pendant
	 */

	public Set<Integer> getCurrentInfoPendant() {

//		Integer tempTargetInfoBeadId = null;
//		Set<Integer> tempList = new HashSet<Integer>();
		Set<Integer> tempEdgesList = new HashSet<Integer>();
		Set<Integer> infoPendantIDs = new HashSet<Integer>();
		Set<Integer> attributeHolderIDs = new HashSet<Integer>();
		Edge tempEdge = new Edge();
		// Vertex tempVertex = new Vertex();
		final boolean FORWARD = true;
		final boolean BACKWARD = false;

		Integer infoBeadID = graphDisplay.getPickedVertex();

		if (infoBeadID != null && infoBeadID != -1) {
			infoPendantIDs.addAll(getDirecrtedClusterOfVertex(infoBeadID,
					FORWARD));
			infoPendantIDs.addAll(getDirecrtedClusterOfVertex(infoBeadID,
					BACKWARD));

			// make sure that there are at list two info-beads connected by an
			// active link
			if (infoPendantIDs.size() < 2) {
				infoPendantIDs = null;
			} else {
				// maKe sure that there is only a single 'attribute holder' in
				// the info pendant
				for (Integer vId : infoPendantIDs) {
					Boolean possibleAttributeHolder = true;
					tempEdgesList = (Set<Integer>) graphDisplay
							.getVertexEdges(vId);
					for (Integer eId : tempEdgesList) {
						if (possibleAttributeHolder) {
							if (isEditingUserModel()) {
								tempEdge = um.getEdgeMap().get(eId);
							} else if (isEditingGroupModel()) {
								tempEdge = gm.getEdgeMap().get(eId);
							}
							if (tempEdge.isDirected()) {
//								tempTargetInfoBeadId = tempEdge.getTargetId();
//								Vertex tempTargetVertex = new Vertex();
//								if (isEditingUserModel()) {
//									tempTargetVertex = um.getVertexMap().get(
//											tempTargetInfoBeadId);
//								} else if (isEditingGroupModel()) {
//									tempTargetVertex = gm.getVertexMap().get(
//											tempTargetInfoBeadId);
//								}
								if (tempEdge.getSourceId() == vId) { // cannot
																		// be an
																		// attribute
																		// holder
																		// because
																		// it
																		// forwards
																		// the
																		// data
									possibleAttributeHolder = false;
								}
							}
						}
					}
					if (possibleAttributeHolder) {
						attributeHolderIDs.add(vId);
					}
				}
				if (attributeHolderIDs.size() != 1) {
					infoPendantIDs = null;
				}
			}

		} else {
			JOptionPane.showMessageDialog(null,"To select an info-pendant please select first an info-bead within the pendant");
		}

		return infoPendantIDs;
	}

	/**
	 * Color the info-pendant's info-beads. The info-pendant should have a single attribute holder for the
	 * info-pendant (a single node at the end of the directed graph)
	 */
	public void showInfoPendant() {
		// reset colors
		graphDisplay.setupVertexDisplay();
		graphDisplay.getVisual().repaint();

		Set<Integer> infoPendantIDs = new HashSet<Integer>();
		infoPendantIDs = getCurrentInfoPendant();

		if ((infoPendantIDs != null)) {
			for (Integer id : infoPendantIDs) {
				graphDisplay.setFoundColor(id, true);
			}
			graphDisplay.getVisual().repaint();
		} else {
			JOptionPane.showMessageDialog(null,
					"Illegal info-pendant, might be due to the following reasons:\n"
							+ "         1) More than one 'info-pendant attribute holder'.\n"
							+ "         2) Less than two info-beads connected by an active link. \n"
							+ "         3) The user did not select an info-bead (a grapgh vertex). \n");
		}

	}

	/**
	 * Copies the current selected info-bead
	 */
	public void copyInfoBead() {
		Integer infoBeadID = graphDisplay.getPickedVertex();
		if (infoBeadID != -1) {
			setLocalClipBoard(infoBeadID);
			setLastPickedVertexID(infoBeadID);
		} else {
			setLocalClipBoard(null);
		}

	}

	/**
	 * Pastes the last copied info-bead
	 */
	public void pasteInfoBead() {
		Integer id = getLocalClipBoard();
		if (id != null) {
			mode = Mode.EDITING;
			graphDisplay.removePick();
			clearSelection(null);
			Editor.getInstance().log("Pasting << " + id);
			Vertex copiedVertex = new Vertex();
			Vertex pastedVertex = new Vertex();
			if (isEditingUserModel()) {
				copiedVertex = um.getVertexMap().get(id);
			} else if (isEditingGroupModel()) {
				copiedVertex = gm.getVertexMap().get(id);
			}
			Point point = new Point();
			point.x = (int) copiedVertex.getX() + 20;
			point.y = (int) copiedVertex.getY() + 20;
			pastedVertex = VertexFactory.getInstance().copyAndCreate(
					copiedVertex.getName(), copiedVertex.getInputs(),
					copiedVertex.getOutputs(), copiedVertex.getType(), point);
			log("vertex << " + pastedVertex.toString() + ">> was created");
			setLastPickedVertexID(pastedVertex.getId());
			graphDisplay.addPick();
			mode = Mode.PICKING;
			setLocalClipBoard(pastedVertex.getId());

			// add the vertex into the model
			ArrayList<Edge> edges = new ArrayList<Edge>();
			if (isEditingUserModel()) {
				edges = um.addVertex(pastedVertex);
			} else if (isEditingGroupModel()) {
				edges = gm.addVertex(pastedVertex);
				gm.addVertexToGroup(pastedVertex.getId(), gm.getModelId());
			}

			// insert the vertex into the graph
			graphDisplay.insertVertex(pastedVertex);

			// insert the edges into the graph
			if (edges != null) {
				for (Edge edge : edges) {
					graphDisplay.insertEdge(edge);
				}
			}
			graphDisplay.getVisual().repaint();

		}
	}

	/**
	 * Pastes the last copied info-bead
	 * 
	 * @param copiedVertex
	 *            copied vertex ID
	 * @return Integer inserted vertex ID
	 */
	public Integer insertInfoBead(Vertex copiedVertex, Integer modelId) {
		Integer copiedId = copiedVertex.getId();
		Integer insertedId = null;
		if (copiedId != null) {
			// Editor.getInstance().log("Inserting << " + id);
			Vertex insertedVertex = new Vertex();
			Point point = new Point();
			point.x = (int) copiedVertex.getX() + 20;
			point.y = (int) copiedVertex.getY() + 20;
			insertedVertex = VertexFactory.getInstance().copyAndCreate(
					copiedVertex.getName(), copiedVertex.getInputs(),
					copiedVertex.getOutputs(), copiedVertex.getType(), point);
			ArrayList<Edge> edges = null;
			// log("vertex << " + pastedVertex.toString() + ">> was created");

			// add the vertex into the model
			if (isEditingUserModel()) {
				edges = um.addVertex(insertedVertex);
			} else if (isEditingGroupModel()) {
				edges = gm.addVertex(insertedVertex);
				gm.addVertexToGroup(insertedVertex.getId(), modelId);
			}

			// insert the vertex into the graph
			graphDisplay.insertVertex(insertedVertex);

			// insert the edges into the graph
			if (edges != null) {
				for (Edge edge : edges) {
					if (isEditingUserModel()) {
						graphDisplay.insertEdge(edge);
					} else if (isEditingGroupModel()) {
						if (ibumSetup.getAllowUserModelToUserModelLink()){
							graphDisplay.insertEdge(edge);
						} else { //not allowing userModel to userModel edges
							Integer sourceVertexId = edge.getSourceId();
							Integer targetVertexId = edge.getTargetId();
							Integer sourceModelId = gm.getVertexIdToModelIdMap().get(sourceVertexId);
							Integer targetModelId = gm.getVertexIdToModelIdMap().get(targetVertexId);
							ModelType targetModelType = gm.getModelIdToModelTypeMap().get(targetModelId);
							if (sourceModelId == targetModelId || targetModelType.equals(ModelType.GROUPMODEL)){
								graphDisplay.insertEdge(edge);
							}
						}
					}
				}
			}
			
			graphDisplay.getVisual().repaint();

			insertedId = insertedVertex.getId();
		}

		return insertedId;
	}

	/**
	 * Deletes the selected info-bead from the graph
	 */
	public void deleteInfoBead() {

		Vertex infoBead = new Vertex();
		Integer infBeadID = graphDisplay.removeVertexFromGraph();
		if (infBeadID != -1) {	
			if (isEditingUserModel()) {
				infoBead = um.getVertexMap().get(infBeadID);
				um.removeVertex(infoBead);
			} else if (isEditingGroupModel()) {
				infoBead = gm.getVertexMap().get(infBeadID);
				gm.removeVertex(infoBead);
				gm.removeVertexFromGroup(infBeadID);
			}

			setLastPickedVertexID(null);
			// if deleted this info-bead can not be copied
			if (getLocalClipBoard() == infBeadID) {
				setLocalClipBoard(null);

			}
		}

	}


	
	/**
	 * Deletes an info-bead from the graph
	 * @param infoBeadID the ID of the the info-bead to be deleted
	 */
	public void deleteInfoBead(Integer infoBeadID) {

		Vertex infoBead = new Vertex();
		Integer id = graphDisplay.removeVertexFromGraph(infoBeadID);
		if (id != -1) {
			if (isEditingUserModel()) {
				infoBead = um.getVertexMap().get(infoBeadID);
				um.removeVertex(infoBead);
			} else if (isEditingGroupModel()) {
				infoBead = gm.getVertexMap().get(infoBeadID);
				gm.removeVertex(infoBead);
				gm.removeVertexFromGroup(infoBeadID);
			}

			setLastPickedVertexID(null);
			// if deleted this info-bead can not be copied
			if (getLocalClipBoard() == infoBeadID) {
				setLocalClipBoard(null);

			}
		}

	}

	
	/**
	 * Explains how to use zoom in
	 */
	public void viewZoomIn() {
	
		JOptionPane.showMessageDialog(this, "Please use the mouse scroll-down to zoom in");
	}

	/**
	 * Explains how to use zoom out
	 */
	public void viewZoomOut() {
		JOptionPane.showMessageDialog(this, "Please use the mouse scroll-up to zoom out");
	}

	
	/**
	 * Explains how to use pan
	 */
	public void viewPan() {
		JOptionPane.showMessageDialog(this, "Please toggle and hold the mouse left button\n" + 
					"to drag the model graph within the graph pane");
	}


	/**
	 * Explains how to use rotate
	 */
	public void viewRotate() {
		JOptionPane.showMessageDialog(this, "Please toggle and hold the mouse left button and the shift key and move the mouse to rotate the model graph");
	}
	
	/**
	 * Explains how to use move info-bead
	 */
	public void viewMoveInfobead() {
		JOptionPane.showMessageDialog(this, "Please select an info-bead,\n then toggle and hold the mouse left button,\n then toggle and hold the alt key,\n and then move the mouse to drag the selected info-bead within the model graph");
	}
	
	/**
	 * Toggles the edge between active (solid line) to inactive (dashed line)
	 * states
	 */
	public void changeLink(Integer edgeId) {
		if (isPickingMode()) {
			if (isEditingUserModel()) {
				um.changeLink(edgeId);
			} else if (isEditingGroupModel()) {
				gm.changeLink(edgeId);
			}

		} else if (isEditingMode()) {
			// do nothing as in editing mode you add edges -- you can't change
			// edges ...
		}
	}

	/**
	 * Activates these menu items when graph editing is available
	 */
	public void activateGraphMenus() {
		// Edit menu
		editMenuItemResetIBColors.setEnabled(true);
		editMenuItemFindIB.setEnabled(true);
		editMenuItemShowIP.setEnabled(true);
		editMenuItemShowSubModel.setEnabled(true);
		editMenuItemCopy.setEnabled(true);
		editMenuItemPaste.setEnabled(true);
		editMenuItemDelete.setEnabled(true);
		editMenuDeleteSubModelFromGroupModel.setEnabled(true);

		// View Menu
		viewMenuItemZoomIn.setEnabled(true);
		viewMenuItemZoomOut.setEnabled(true);
		viewMenuItemPan.setEnabled(true);
		viewMenuItemRotate.setEnabled(true);
		viewMenuItemMoveInfobead.setEnabled(true);

		// Run Menu
		runMenuActivateModel.setEnabled(true);
		
		//Help menu
		helpMenuIntro.setEnabled(true);
		helpMenuInfoBeadHelp.setEnabled(true);
		helpMenuAbout.setEnabled(true);
	}

	/**
	 * Deactivates these menu items when graph editing is not available
	 */
	public void deActivateGraphMenus() {
		// Edit menu
		editMenuItemFindIB.setEnabled(false);
		editMenuItemShowIP.setEnabled(false);
		editMenuItemShowSubModel.setEnabled(false);
		editMenuItemCopy.setEnabled(false);
		editMenuItemPaste.setEnabled(false);
		editMenuItemDelete.setEnabled(false);
		editMenuDeleteSubModelFromGroupModel.setEnabled(false);

		// View Menu
		viewMenuItemZoomIn.setEnabled(false);
		viewMenuItemZoomOut.setEnabled(false);
		viewMenuItemPan.setEnabled(false);
		viewMenuItemRotate.setEnabled(false);
		viewMenuItemMoveInfobead.setEnabled(false);

		// Run Menu
		runMenuActivateModel.setEnabled(false);
		
		// Help Menu
		helpMenuIntro.setEnabled(true);
		helpMenuInfoBeadHelp.setEnabled(false);
		helpMenuAbout.setEnabled(true);
		
	}

	/**
	 * Initiates a new model and a new graph
	 */
	public void newUserModel() {

		setEditedModeType(EditedModelType.USERMODEL);

		um = ModelFactory.getInstance().createUserModel();

		graphDisplay = null;
		setupGraphDisplay();
		split1.setRightComponent(graphDisplay);

		graphDisplay.openGraph(null);
		graphDisplay.addMouseListener(new GraphListener());
		graphDisplay.addPick();

		mode = Mode.PICKING;

		clearSelection(null);
		source = null;
		selected = null;
		listItemSelected = null;


		graphDisplay.addPick();

		this.setVisible(true);

		fileMenuItemClose.setEnabled(true);
		fileMenuItemSave.setEnabled(true);
		fileMenuItemSaveAs.setEnabled(true);
		fileMenuItemSaveInfoPendantAs.setEnabled(true);
		fileMenuItemDeleteModelFromList.setEnabled(true);
		fileMenuItemNewUserModel.setEnabled(false);
		fileMenuItemNewGroupModel.setEnabled(false);
		fileMenuItemOpenUserModel.setEnabled(false);
		fileMenuItemOpenGroupModel.setEnabled(false);

		activateGraphMenus();
		editMenuDeleteSubModelFromGroupModel.setEnabled(false); // This one is
																// only relevant
																// for sub
																// models of a
																// group model


	}

	/**
	 * Initiates a new model and a new graph
	 */
	public void newGroupModel() {

		setEditedModeType(EditedModelType.GROUPMODEL);

		gm = ModelFactory.getInstance().createGroupModel();

		graphDisplay = null;
		setupGraphDisplay();
		split1.setRightComponent(graphDisplay);

		graphDisplay.openGraph(null);
		graphDisplay.addMouseListener(new GraphListener());
		graphDisplay.addPick();

		mode = Mode.PICKING;

		clearSelection(null);
		source = null;
		selected = null;
		listItemSelected = null;


		graphDisplay.addPick();

		this.setVisible(true);

		fileMenuItemClose.setEnabled(true);
		fileMenuItemSave.setEnabled(true);
		fileMenuItemSaveAs.setEnabled(true);
		fileMenuItemSaveInfoPendantAs.setEnabled(true);
		fileMenuItemDeleteModelFromList.setEnabled(true);
		fileMenuItemNewUserModel.setEnabled(false);
		fileMenuItemNewGroupModel.setEnabled(false);
		fileMenuItemOpenUserModel.setEnabled(false);
		fileMenuItemOpenGroupModel.setEnabled(false);

		activateGraphMenus();

	}

	/**
	 * Opens a previously saved user model
	 */
	public void openUserModel() {
		
		
		setEditedModeType(EditedModelType.USERMODEL);
		
		um = ModelFactory.getInstance().createUserModel();

		File file = fChooserOpen(ElementType.USERMODEL, "open user model");
		
		if (file == null) {
			return;
		} else if (file.isFile()) {

			setWorkingUMFile(file);
			um = loadUModel(file);
			
			if(um!=null){
				int vertexCount = 0;
				for (Integer key : um.getVertexMap().keySet()){
					vertexCount = Math.max(vertexCount, um.getVertexMap().get(key).getId());
				}
				VertexFactory.getInstance().setVertexCount(vertexCount + 1);
				
				int linkCount = 0;
				for (Integer key : um.getEdgeMap().keySet()){
					linkCount = Math.max(linkCount, um.getEdgeMap().get(key).getId());
				}
				EdgeFactory.getInstance().setLinkCount(linkCount + 1);

				graphDisplay = null;
				setupGraphDisplay();
				split1.setRightComponent(graphDisplay);

				this.setVisible(true);

				graphDisplay.openGraph(um);
				graphDisplay.addMouseListener(new GraphListener());
				graphDisplay.addPick();

				mode = Mode.PICKING;

				clearSelection(null);
				source = null;
				selected = null;
				listItemSelected = null;


				fileMenuItemClose.setEnabled(true);
				fileMenuItemSave.setEnabled(true);
				fileMenuItemSaveAs.setEnabled(true);
				fileMenuItemSaveInfoPendantAs.setEnabled(true);
				fileMenuItemDeleteModelFromList.setEnabled(true);
				fileMenuItemNewUserModel.setEnabled(false);
				fileMenuItemNewGroupModel.setEnabled(false);
				fileMenuItemOpenUserModel.setEnabled(false);
				fileMenuItemOpenGroupModel.setEnabled(false);

				activateGraphMenus();
				
				

				this.setVisible(true);
			} else {
				// already logged , so do nothing
			}

		} else {
			JOptionPane.showMessageDialog(null,"Tthere was a problem with the chosen file ");
			return;
		}
	}

	/**
	 * Opens a previously saved group model
	 */
	public void openGroupModel() {
		

		
		this.setEditedModeType(EditedModelType.GROUPMODEL);
		
		gm = ModelFactory.getInstance().createGroupModel();

		File file = fChooserOpen(ElementType.GROUPMODEL, "open group model");
		
		if (file == null){
			return;
		} else if (file.isFile()) {

			setWorkingUMFile(file);
			gm = loadGModel(file);

			if (gm != null) {

				int vertexCount = 0;
				for (Integer key : gm.getVertexMap().keySet()){
					vertexCount = Math.max(vertexCount, gm.getVertexMap().get(key).getId());
				}
				VertexFactory.getInstance().setVertexCount(vertexCount + 1);
				
				int linkCount = 0;
				for (Integer key : gm.getEdgeMap().keySet()){
					linkCount = Math.max(linkCount, gm.getEdgeMap().get(key).getId());
				}
				EdgeFactory.getInstance().setLinkCount(linkCount + 1);
				
				int modelCount = 0;
				for (Integer key : gm.getModelIdToModelTypeMap().keySet()){
					modelCount = Math.max(modelCount,(int) key);
				}
				ModelFactory.getInstance().setUMCount(modelCount + 1);

				graphDisplay = null;
				setupGraphDisplay();
				split1.setRightComponent(graphDisplay);

				this.setVisible(true);

				graphDisplay.openGraph(gm);
				graphDisplay.addMouseListener(new GraphListener());
				graphDisplay.addPick();

				mode = Mode.PICKING;

				clearSelection(null);
				source = null;
				selected = null;
				listItemSelected = null;


				fileMenuItemClose.setEnabled(true);
				fileMenuItemSave.setEnabled(true);
				fileMenuItemSaveAs.setEnabled(true);
				fileMenuItemSaveInfoPendantAs.setEnabled(true);
				fileMenuItemDeleteModelFromList.setEnabled(true);
				fileMenuItemNewUserModel.setEnabled(false);
				fileMenuItemNewGroupModel.setEnabled(false);
				fileMenuItemOpenUserModel.setEnabled(false);
				fileMenuItemOpenGroupModel.setEnabled(false);

				activateGraphMenus();
				
				
				// prevent links from different UMs to be displayed if this is not allowed by the setup
				if ( ! ibumSetup.getAllowUserModelToUserModelLink()){
					for (Integer key : gm.getEdgeMap().keySet()){
						Edge edge = gm.getEdgeMap().get(key);
						Integer sourceVertexId = edge.getSourceId();
						Integer targetVertexId = edge.getTargetId();
						Integer sourceModelId = gm.getVertexIdToModelIdMap().get(sourceVertexId);
						Integer targetModelId = gm.getVertexIdToModelIdMap().get(targetVertexId);
						ModelType targetModelType = gm.getModelIdToModelTypeMap().get(targetModelId);
						ModelType sourceModelType = gm.getModelIdToModelTypeMap().get(sourceModelId);
						if ((sourceModelId != targetModelId && targetModelType.equals(ModelType.USERMODEL)  
								&&  ! sourceModelType.equals(ModelType.GROUPMODEL))){
							graphDisplay.removeEdge(key);
						}
					}
				}				

				this.setVisible(true);
			} else {
				// already logged , so do nothing
			}

		} else {
			JOptionPane.showMessageDialog(null,"Ththere was a problem with the chosen file ");
			return;
		}
	}

	/**
	 * Adds a previously saved group model to an existing model if all
	 * info-beads are available
	 * 
	 * @param point
	 *            the top left corner of the model
	 */
	public void addGroupModel(Point point) {

	}

	/**
	 * Adds a previously saved model to an existing model if all info-beads are
	 * available
	 * 
	 * @param point
	 *            the top left corner of the model
	 */
	/**
	 * @param point
	 */
	public void addSubModel(Point point) {

		if (isPickingMode()) {
			// do nothing
		} else if (isEditingMode()) {
			String fileName = "";
			String messageText = "";
			ElementList loadedSubModelSource = new ElementList();
			loadedSubModelSource = source;

			if (source.equals(infoPendants) || source.equals(userModels)
					|| source.equals(groupModels)) {
				if (source.equals(infoPendants)) {
					fileName = dSetup.getInfopendantspath() + selected + ".eip";
					messageText = "info-pendant";
				}
				if (source.equals(userModels)) {
					fileName = dSetup.getUserModelsPath() + selected + ".eum";
					messageText = "user-model";
				}
				if (source.equals(groupModels)) {
					fileName = dSetup.getGroupModelsPath() + selected + ".egm";
					messageText = "group-model";
				}
				File file = new File(fileName);
				log("Creating " + messageText + " << " + fileName + ">>");
				if (isEditingUserModel()) {
					log("\t vertices included in the user model");
				} else if (isEditingGroupModel()) {
					log("\t created as a sub-model of the group model");

				}

				clearSelection(null);
				graphDisplay.addPick();
				mode = Mode.PICKING;

				UserModel tempUserModel = null;
				InfoPendant tempInfoPendant = null;
				GroupModel tempGroupModel = null;
				GroupModel workingModel = null; 

				if (file.equals("")){
					return;
				}
				if (file.exists()) {
					if (source == infoPendants) {
						tempInfoPendant = loadIPModel(file);
						workingModel = (convert.model2GroupModel((Model) tempInfoPendant));
					} else if (source == userModels) {
						tempUserModel = loadUModel(file);
						workingModel = convert.userModel2GroupModel(tempUserModel);
					} else if (source == groupModels) {
						tempGroupModel = loadGModel(file);
						workingModel = tempGroupModel;
					}

					if (workingModel != null) {
						Vertex tempVertex = new Vertex();
						Edge tempEdge = new Edge();
						Integer fromVertexId = null;
						Integer toVertexId = null;
						HashMap<Integer, Integer> relatedVertices = new HashMap<Integer, Integer>();
						Collection<Integer> edges = null;
						Point topLeftPoint = new Point();

						// first pass to make sure all info-beads are available

						File checkFile = null;
						Boolean infoBeadsAvailable = true;
						String infoBeadsPath = dSetup.getInfobeadsPath();

						for (Integer keyLoaded : workingModel.getVertexMap().keySet()) {
							tempVertex = workingModel.getVertexMap().get(keyLoaded);
							checkFile = new File(infoBeadsPath
									+ tempVertex.getName() + ".java");

							if (!checkFile.exists()) {
								infoBeadsAvailable = false;
								JOptionPane.showMessageDialog(null, "Error - the "
												+ checkFile.getName()
												+ " info-bead required by the model is not available");
							}

						}


						// Handle addition of a sub model (either a user model
						// or a group model) into the group model as a
						// separated entity identified by its group model id
						
						Integer newModelid = null;
						if (isEditingGroupModel()) {
							if (loadedSubModelSource.equals(userModels)) {
								newModelid = gm.addNewSubUserModel(tempUserModel);
							} else if (loadedSubModelSource.equals(groupModels)) {
								newModelid = gm.addNewSubGroupModel(tempGroupModel);
							} else if (loadedSubModelSource.equals(infoPendants)) {
								gm.addNewSubInfoPendant(tempInfoPendant);
							}
						}  

						// Set vertices positions so that the selected point is
						// the top-left point of the added vertices
						// Second pass to paste all vertices, and keep track of
						// the new local ID and its connection with the loaded
						// id
						// This is required to make sure that the right edges
						// are kept DIRECTED

						topLeftPoint.setLocation(
								(double) graphDisplay.getHeight(),
								(double) graphDisplay.getWidth());
						if (infoBeadsAvailable) {
							for (Integer keyOfLoadedVertex : workingModel.getVertexMap().keySet()) {
								tempVertex = workingModel.getVertexMap().get(keyOfLoadedVertex);
								topLeftPoint.setLocation(
										Math.min(topLeftPoint.getX(),
												tempVertex.getX()), Math.min(
												topLeftPoint.getY(),
												tempVertex.getY()));
							}
							Double deltaX = point.getX() - topLeftPoint.getX();
							Double deltaY = point.getY() - topLeftPoint.getY();
							Integer keyOfUpdatedVertex = null;
							for (Integer keyOfLoadedVertex : workingModel.getVertexMap().keySet()) {
								tempVertex = workingModel.getVertexMap().get(keyOfLoadedVertex);
								tempVertex.setLocation(tempVertex.getX() + deltaX, tempVertex.getY() + deltaY);
								if (isEditingGroupModel()) {
									if (loadedSubModelSource.equals(userModels)  ||   loadedSubModelSource.equals(groupModels)) {
										keyOfUpdatedVertex = insertInfoBead(tempVertex, newModelid);
									} else if (loadedSubModelSource.equals(infoPendants)) { 
										keyOfUpdatedVertex = insertInfoBead(tempVertex, gm.getModelId());
									}
								} else {  // editing user model and merging other models into it
								keyOfUpdatedVertex = insertInfoBead(tempVertex, um.getModelId() );
								}
								
								relatedVertices.put(keyOfLoadedVertex, keyOfUpdatedVertex);
							}

							// Set the right local Edges to be DIRECTED if the
							// loaded edges are Directed
							for (Integer edgeLoadedKey : workingModel
									.getEdgeMap().keySet()) {
								tempEdge = workingModel.getEdgeMap().get(edgeLoadedKey);
								if (tempEdge.isDirected()) {
									fromVertexId = relatedVertices.get(tempEdge.getSourceId());
									toVertexId = relatedVertices.get(tempEdge.getTargetId());
									edges = graphDisplay.getVertexEdges(fromVertexId);
									for (Integer e : edges) {
										if (isEditingUserModel()) {
											tempEdge = um.getEdgeMap().get(e);
										} else if (isEditingGroupModel()) {
											tempEdge = gm.getEdgeMap().get(e);
										}

										if ((tempEdge.getSourceId() == fromVertexId) && (tempEdge.getTargetId() == toVertexId)) {
											tempEdge.setType(EdgeType.DIRECTED);
										}
									}

								}
							}
						}
					} 
					
					graphDisplay.getVisual().repaint();

				} else {
					JOptionPane.showMessageDialog(null, " Cannot find the model ");
				}

			} else {
				JOptionPane.showMessageDialog(null, " There was a problem with the chosen file ");
				return;
			}
			log(messageText + " << " + fileName + ">> was created");
		}

	}


	/**
	 * Offers the user to save a user model before exiting the editor or closing
	 * the model
	 */
	public void saveBeforeQuit() {
		Boolean empty = true;
		if (isEditingUserModel()) {
			if (um != null && !um.getVertexMap().isEmpty()) {
				empty = false;
			}
		} else if (isEditingGroupModel()) {
			if (gm != null && !gm.getVertexMap().isEmpty()) {
				empty = false;
			}
		}

		if (!empty) {
			String message = "Do you want to save the current Model?";
			String title = "Warning";
			int reply = JOptionPane.showConfirmDialog(null, message, title,
					JOptionPane.YES_NO_OPTION);
			if (reply == JOptionPane.YES_OPTION) {
				save();
			}
		}
	}

	/**
	 * Closing a model
	 */
	public void Close() {

		this.setVisible(false);

		graphDisplay.removeAll();
		graphDisplay.closeGraph();
		graphDisplay = null;

		mode = Mode.BLANK;

		fileMenuItemClose.setEnabled(false);
		fileMenuItemSave.setEnabled(false);
		fileMenuItemSaveAs.setEnabled(false);
		fileMenuItemSaveInfoPendantAs.setEnabled(false);
		fileMenuItemDeleteModelFromList.setEnabled(true);
		fileMenuItemNewUserModel.setEnabled(true);
		fileMenuItemNewGroupModel.setEnabled(true);
		fileMenuItemOpenUserModel.setEnabled(true);
		fileMenuItemOpenGroupModel.setEnabled(true);

		deActivateGraphMenus();

		if (um != null) {
			um.close();
			um = null;
		}

		if (gm != null) {
			gm.close();
			gm = null;
		}

		setWorkingUMFile(null);
		
		showStatusBar(-1);

		this.setVisible(true);

	}

	/**
	 * Loads a new Info-bead
	 */
	public void importInfoBead() {
		newElement();
	}

	
	


	/**
	 * Loads a new info-bead
	 * 
	 */
	public void newElement() {
		
		File sourceFile = fChooserImport(ElementType.INFOBEAD, "Select info-bead");
		
		String infobeadName = sourceFile.getName();
		File infobeadFileCheck = new File(dSetup.getInfobeadsPath() + infobeadName);
		if (infobeadFileCheck.isFile() && infobeadFileCheck.exists() ){
			JOptionPane.showMessageDialog(null, "An info-bead by that name already exists.\n** Import aborted.");
			return;
		}
		
			// copy java source, help file and metadata to the editors compatible element folder
		if (infobeadName == null || infobeadName == ""){
			return;
		} else if (sourceFile != null && sourceFile.isFile()) {

			String sourceMetadataPath = FilenameUtils
					.removeExtension(sourceFile.getAbsolutePath())
					+ ".metadata";
			File metadataFile = new File(sourceMetadataPath);

			String helpPath = FilenameUtils
					.removeExtension(sourceFile.getAbsolutePath())
					+ ".txt";
			File helpFile = new File(helpPath);

			CustomMetadata metadata = null;
			

			if (metadataFile != null && metadataFile.exists() && metadataFile.isFile()) {
//				File targetPath = infoBeads.getElementPath();
				File iBeadsCollection = new File(dSetup.getInfobeadsPath());
				File ihelpCollection = new File(dSetup.getHelpPath());
				File iMetadataPath = new File(dSetup.getMetadataPath());

				// copy the metadata
				try {
					FileUtils.copyFileToDirectory(metadataFile, iMetadataPath);

				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "Failed : could not copy the info-bead  metadata to the metadata folder");
					return;
				}

				// load the metadata from file to CustomMetadata object
				metadata = loadMetadata(metadataFile);

				if (metadata != null) {
					infoBeads.addElement(
							FilenameUtils.removeExtension(sourceFile.getName()),
							metadata);
				} else {
					// delete copies metadata file 
					// warning message is assumed to be presented by add element method above
					String metadataToBeDeletedName = iMetadataPath + FilenameUtils.removeExtension(sourceFile.getName());
					File metadataToBeDeleted = new File(metadataToBeDeletedName);
					if (metadataToBeDeleted != null && metadataToBeDeleted.exists() && metadataToBeDeleted.isFile()) {
						metadataToBeDeleted.delete();
					}
					return;					
				}	
				
				
				// copy the java source file
				try {
					FileUtils.copyFileToDirectory(sourceFile, iBeadsCollection);
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "Failed : could not copy the info-bead source file to the info-bead folder");
					// delete the metadata file
					String metadataToBeDeletedName = iMetadataPath + FilenameUtils.removeExtension(sourceFile.getName());
					File metadataToBeDeleted = new File(metadataToBeDeletedName);
					if (metadataToBeDeleted != null && metadataToBeDeleted.exists() && metadataToBeDeleted.isFile()) {
						metadataToBeDeleted.delete();
					}
					return;
				}



				// Load help file
				if (helpFile != null && helpFile.exists() && helpFile.isFile()) {
					try {
						FileUtils.copyFileToDirectory(helpFile, ihelpCollection);
					} catch (IOException e) {
						e.printStackTrace();
					}

				} else {
					JOptionPane.showMessageDialog(null, "Info-bead help (.txt) file not found. Loading the info-bead without a help file");
				}
				
				
			} else {
				JOptionPane.showMessageDialog(null, "Failed to load info-bead. Every ?.java source file requires a matching .metadata file");
			}

		} else {
			JOptionPane.showMessageDialog(null, "There was a problem with the chosen file ");
		}
	}

	/**
	 * If file exists saves the model else calls save as
	 */

	public void save() {

		Boolean firstSave = true;
		Boolean saveSucceded = false;
		File file = getWorkingUMFile();
		

		if (file != null) {
			
			// delete the file and rewrite a new one
			if (!file.delete()){
				JOptionPane.showMessageDialog(null, "File cannot be rewritten, could be in use");
				return;
			}
			
			if (isEditingUserModel()) {
				graphDisplay.updateCoordinates(um);
			} else if (isEditingGroupModel()) {
				graphDisplay.updateCoordinates(gm);
			}

			
			if (isEditingUserModel()) {
				saveSucceded = saveUserModelToXML(file, um);
			} else if (isEditingGroupModel()) {
				saveSucceded = saveGroupModelToXML(file, gm);
			}
			firstSave = false;
			if (!saveSucceded) {
				JOptionPane.showMessageDialog(null, "**** Error - save failed");
			}
		}
		if (firstSave) {
			saveModelAs();

		} else {
			log("Saved the model to : " + file.getName());
		}

	}

	/**
	 * Saves the user model into a file selected by the user
	 */
	public void saveModelAs() {

		Boolean saveSucceeded = false;
		Boolean nullModel = true;

		if (isEditingUserModel()) {
			if (um != null) {
				nullModel = false;
			}
		} else if (isEditingGroupModel()) {
			if (gm != null) {
				nullModel = false;
			}
		}

		if (!nullModel) {

			File file = null;

			if (isEditingUserModel()) {
				graphDisplay.updateCoordinates(um);
				file = fChooserSave(ElementType.USERMODEL);
			} else if (isEditingGroupModel()) {
				graphDisplay.updateCoordinates(gm);
				file = fChooserSave(ElementType.GROUPMODEL);
			}

			if (file == null){ 
				return;
			} else if (file.exists()) {
					// delete and write a new one
					if (!file.delete()){
						JOptionPane.showMessageDialog(null, "File cannot be rewritten, could be in use"); 
						return;
					}
				} 

				if (isEditingUserModel()) {
					saveSucceeded = saveUserModelToXML(file, um);
					if (saveSucceeded) {
						userModels.removeAll();
						userModels.initialize(ElementType.USERMODEL);
						log("Saved the user model as : " + file.getName());
					}
				} else if (isEditingGroupModel()) {
					saveSucceeded = saveGroupModelToXML(file, gm);
					if (saveSucceeded) {
						groupModels.removeAll();
						groupModels.initialize(ElementType.GROUPMODEL);
						log("Saved the group model as : " + file.getName());
					}
				}

				if (saveSucceeded) {
					repaint();
					setWorkingUMFile(file);
				} else {
				JOptionPane.showMessageDialog(null, "There is problem with the selected file name");
			}

		} else {
			JOptionPane.showMessageDialog(null, "Cannot save when no user model/graph is open");
		}

	}

	/*
	 * Saves an infoPendant into a file selected by the user
	 */
	public void saveInfoPendantAs() {

		Set<Integer> infoPendantIDs = new HashSet<Integer>();
		infoPendantIDs = getCurrentInfoPendant();

		if ((infoPendantIDs != null) && (infoPendantIDs.size() > 0)) {

			InfoPendant infoPendant = new InfoPendant();

			if (isEditingUserModel()) {
				graphDisplay.updateCoordinates(um);
			} else if (isEditingGroupModel()) {
				graphDisplay.updateCoordinates(gm);
			}

			for (Integer id : infoPendantIDs) {
				if (isEditingUserModel()) {
					infoPendant.addVertex(um.getVertexMap().get(id));
				} else if (isEditingGroupModel()) {
					infoPendant.addVertex(gm.getVertexMap().get(id));
					gm.addVertexToGroup(id, gm.getModelId());
				}
			}

			Edge edge = new Edge();

			if (isEditingUserModel()) {
				for (Integer key1 : um.getEdgeMap().keySet()) {
					edge = um.getEdgeMap().get(key1);
					if ((infoPendantIDs.contains(edge.getSourceId()))
							&& (infoPendantIDs.contains(edge.getTargetId()))
							&& edge.isDirected()) {
						infoPendant.getEdgeMap().put(key1, edge);
					}
				}
			} else if (isEditingGroupModel()) {
				for (Integer key1 : gm.getEdgeMap().keySet()) {
					edge = gm.getEdgeMap().get(key1);
					if ((infoPendantIDs.contains(edge.getSourceId()))
							&& (infoPendantIDs.contains(edge.getTargetId()))
							&& edge.isDirected()) {
						infoPendant.getEdgeMap().put(key1, edge);
					}
				}

			}

			File file = fChooserSave(ElementType.INFOPENDANT);

			if (file == null) {
				return;
			} else if (saveInfoPendantToXML(file, infoPendant)) {
					infoPendants.removeAll();
					infoPendants.initialize(ElementType.INFOPENDANT);
					log("Saved the info-pendant as : " + file.getName());
					repaint();
				} else {
				JOptionPane.showMessageDialog(null, "There is a problem with the selected file name");
			}

		} else {
			JOptionPane.showMessageDialog(null, "Cannot save for one of the following reasons:\n"
					+ "	  1. There isn't any open user model graph\n"
					+ "	  2. Could not select an info-pendant");
		}

	}

	
	/*
	 * === utility methods
	 * ======================================================
	 * ==================================================
	 */

	/**
	 * log information at the message panel at the bottom of the screen
	 * 
	 * @Param log The message string to be displayed
	 */
	public void log(String log) {
		infoPanel.log(log);
	}

	/**
	 * displays a fileChooser and return the user's file selection
	 * 
	 * @param type
	 *            the type of the file that should be loaded
	 * @param approveButtonText
	 *            the text that is displayed on the accept button
	 * @return the file that was chosen by the user
	 */
	public File fChooserOpen(final ElementType type, String approveButtonText) {

//		String acceptButton = "";
		String currentWorkingDirectory = "";

		// set file chooser starting/current folder as root of the editor
		if (type.equals(ElementType.USERMODEL)) {
			currentWorkingDirectory = dSetup.getUserModelsPath();
		} else if (type.equals(ElementType.GROUPMODEL)) {
			currentWorkingDirectory = dSetup.getGroupModelsPath();
		}

		File returnFile = null;

		JFileChooser fileChooser = new JFileChooser(currentWorkingDirectory);

		// filter to only display java source files
		FileFilter javaFilter = new FileFilter() {

			@Override
			public String getDescription() {
				return "Java source files";
			}

			@Override
			public boolean accept(File file) {
				return (file.getName().endsWith(".java") || file.isDirectory());
			}
		};

		// filter to only display user models
		FileFilter umFilter = new FileFilter() {

			@Override
			public String getDescription() {
				return "Editor User Model save files";
			}

			@Override
			public boolean accept(File file) {
				return (file.getName().endsWith(".eum") || file.isDirectory());
			}
		};

		// filter to only display group models
		FileFilter gmFilter = new FileFilter() {

			@Override
			public String getDescription() {
				return "Editor User Model save files";
			}

			@Override
			public boolean accept(File file) {
				return (file.getName().endsWith(".egm") || file.isDirectory());
			}
		};

		// set filters & accept button text for the file chooser 
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.setMultiSelectionEnabled(false);

		switch (type) {
		case INFOBEAD:
			fileChooser.addChoosableFileFilter(javaFilter);
			break;
		case USERMODEL:
			fileChooser.addChoosableFileFilter(umFilter);
			break;
		case GROUPMODEL:
			fileChooser.addChoosableFileFilter(gmFilter);
			break;

		default:
			break;
		}

		// display the file chooser
		int action;
		action = fileChooser.showDialog(this, approveButtonText);

		File sFile = null;
		if (action == JFileChooser.APPROVE_OPTION) {
			try {
				File cFile = fileChooser.getSelectedFile();
				sFile = new File(currentWorkingDirectory + cFile.getName());

				String fileName = sFile.getName();

				if (sFile.exists()) {
					returnFile = sFile;
				} else {
					String message = "File < " + fileName + " > does not exist";
					JOptionPane.showMessageDialog(this, message);
				}

			} catch (Exception e) {
				System.out.println("Open Error");
				e.printStackTrace();
			}

		}

		return returnFile;

	}

	/**
	 * diplays a fileChooser , with and return the users file selection
	 * 
	 * @param type
	 *            the type of the file that should be loaded
	 * @param approveButtonText
	 *            the text that is displayed on the accept button
	 * @return the file that was chosen by the user
	 */
	public File fChooserImport(final ElementType type, String approveButtonText) {

//		String acceptButton = "";

		// set file chooser starting/current folder as root of the editor
		String currentWorkingDirectory = dSetup.getEclipseworkingdirpath();

		File returnFile = null;

		JFileChooser fileChooser = new JFileChooser(currentWorkingDirectory);

		// filter to only display java class files
		FileFilter javaFilter = new FileFilter() {

			@Override
			public String getDescription() {
				return "Java source files";
			}

			@Override
			public boolean accept(File file) {
				return (file.getName().endsWith(".java") || file.isDirectory());
			}
		};

		// set filters & accept button text for the file chooser (.class for
		// elements)
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.setMultiSelectionEnabled(false);

		if (type.equals(ElementType.INFOBEAD)){
			fileChooser.addChoosableFileFilter(javaFilter);
		}

		// display the file chooser
		int action;
		action = fileChooser.showDialog(this, approveButtonText);

		File iFile = null;
		if (action == JFileChooser.APPROVE_OPTION) {
			try {
				iFile = fileChooser.getSelectedFile();

				String fileName = iFile.getName();

				if (iFile.exists()) {
					returnFile = iFile;
				} else {
					String message = "File < " + fileName + " > does not exist";
					JOptionPane.showMessageDialog(this, message);
				}

			} catch (Exception e) {
				System.out.println("Element Import Error");
				e.printStackTrace();
			}

		}

		return returnFile;

	}

	/**
	 * A file chooser to select a file for saving models
	 * 
	 * @param type
	 *            the type of the file that should be saved
	 * @return the file that was chosen by the user
	 */
	public File fChooserSave(final ElementType type) {

//		String acceptButton = "";

		// set file chooser starting/current folder as root of the editor
		String currentWorkingDirectory = null;
		String approveButtonText = null;
		String fileSuffix = null;
		File returnFile = null;

		switch (type) {

		case USERMODEL:
			currentWorkingDirectory = dSetup.getUserModelsPath();
			approveButtonText = "Save user model";
			fileSuffix = ".eum";
			break;

		case GROUPMODEL:
			currentWorkingDirectory = dSetup.getGroupModelsPath();
			approveButtonText = "Save group model";
			fileSuffix = ".egm";
			break;

		case INFOPENDANT:
			currentWorkingDirectory = dSetup.getInfopendantspath();
			approveButtonText = "Save info-pendant";
			fileSuffix = ".eip";
			break;

		default:
			return returnFile;
		}

		JFileChooser fileChooser = new JFileChooser(currentWorkingDirectory);

		// filter to only display user models
		FileFilter umFilter = new FileFilter() {

			@Override
			public String getDescription() {
				return "Editor User Model save files";
			}

			@Override
			public boolean accept(File file) {
				return (file.getName().endsWith(".eum") || file.isDirectory());
			}
		};

		// filter to only display group models
		FileFilter gmFilter = new FileFilter() {

			@Override
			public String getDescription() {
				return "Editor Group Model save files";
			}

			@Override
			public boolean accept(File file) {
				return (file.getName().endsWith(".egm") || file.isDirectory());
			}
		};

		// filter to only display infoPendants
		FileFilter ipFilter = new FileFilter() {

			@Override
			public String getDescription() {
				return "Editor Info-Pendant save files";
			}

			@Override
			public boolean accept(File file) {
				return (file.getName().endsWith(".eip") || file.isDirectory());
			}
		};

		// set filters & accept button text for the file chooser (.class for
		// elements or .eum for user models)
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.setMultiSelectionEnabled(false);

		switch (type) {

		case USERMODEL:
			fileChooser.addChoosableFileFilter(umFilter);
			break;

		case GROUPMODEL:
			fileChooser.addChoosableFileFilter(gmFilter);
			break;

		case INFOPENDANT:
			fileChooser.addChoosableFileFilter(ipFilter);
			break;

		default:
			break;
		}

		// display the file chooser
		int action;
		action = fileChooser.showDialog(this, approveButtonText);

		File sFile = null;
		if (action == JFileChooser.APPROVE_OPTION) {
			try {
				File cFile = fileChooser.getSelectedFile();
				if (cFile.getName().endsWith(fileSuffix)) {
					sFile = new File(currentWorkingDirectory + cFile.getName());
				} else {
					sFile = new File(currentWorkingDirectory + cFile.getName()
							+ fileSuffix);
				}

				String fileName = sFile.getName();

				if (!sFile.exists()) {
					returnFile = sFile;
				} else {
					String message = "File < " + fileName
							+ " > already exist: \n"
							+ "Do you want to overwrite it?";
					String title = "Warning";
					int reply = JOptionPane.showConfirmDialog(null, message,
							title, JOptionPane.YES_NO_OPTION);
					if (reply == JOptionPane.YES_OPTION) {
						sFile.delete();
						returnFile = sFile;
					}
				}

			} catch (Exception e) {
				System.out.println("Save Error");
				e.printStackTrace();
			}

		}
		return returnFile;

	}

	/**
	 * Saves an infoPendant into a file using XML format
	 * 
	 * @param file
	 *            The file used to save the infoPendant data
	 * @param savedElement
	 *            the infoPendant to be saved
	 * @return Boolean true if succeeded else false
	 */

	public boolean saveInfoPendantToXML(File file, InfoPendant savedElement) {
		try {
			JAXBContext context = JAXBContext.newInstance(InfoPendant.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
					Boolean.TRUE);

			// write to xml (form the metadata object that was created and
			// defined)
			marshaller.marshal(savedElement, file);
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Failed to save the info-pendant");

		}
		return false;
	}

	/**
	 * Saves a user-model into a file using XML format
	 * 
	 * @param file
	 *            The file used to save the model data
	 * @param savedElement
	 *            the user model to be saved
	 * @return Boolean true if succeeded else false
	 */

	public boolean saveUserModelToXML(File file, UserModel savedElement) {


		try {
			JAXBContext context = JAXBContext.newInstance(UserModel.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
					Boolean.TRUE);

			// write to xml (form the object that was created and defined)
			marshaller.marshal(savedElement, file);
			return true;

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Failed to save the user model");
			e.printStackTrace();

		}
		return false;
	}

	/**
	 * Saves a group-model into a file using XML format
	 * 
	 * @param file
	 *            The file used to save the model data
	 * @param savedElement
	 *            the group model to be saved
	 * @return Boolean true if succeeded else false
	 */

	public boolean saveGroupModelToXML(File file, GroupModel savedElement) {
		try {
			JAXBContext context = JAXBContext.newInstance(GroupModel.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
					Boolean.TRUE);

			// write to xml (form the object that was created and defined)
			marshaller.marshal(savedElement, file);
			return true;

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Failed to save the group model");
			e.printStackTrace();

		}
		return false;
	}

	
	
	/**
	 * Deletes a saved model (a group model, a user model or an info-pendant)
	 */
	public void deleteSavedModelItemFromList(){
				
		String savedModelFileName = "";
		if (this.source != null){
			if (this.source.equals(userModels)){
				if (this.listItemSelected != null){
					 int choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to detele this saved user model from the Editor?" 
				                ,"Warning", JOptionPane.WARNING_MESSAGE
				                , JOptionPane.OK_CANCEL_OPTION);
					 if (choice == 0){
						savedModelFileName = dSetup.getUserModelsPath() + this.listItemSelected.toString() + ".eum";
						userModels.deleteElement(listItemSelected.toString());
						File removedFile = new File(savedModelFileName);
						if (removedFile != null  &&  removedFile.exists()  &&  removedFile.isFile()){
							removedFile.delete();
						}
					}
				}
			} else if (this.source.equals(groupModels)){
				if (this.listItemSelected != null){
					 int choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to detele this saved group model from the Editor?" 
				                ,"Warning", JOptionPane.WARNING_MESSAGE
				                , JOptionPane.OK_CANCEL_OPTION);
					if (choice == 0){
						savedModelFileName = dSetup.getGroupModelsPath() + this.listItemSelected.toString() + ".egm";
						groupModels.deleteElement(listItemSelected.toString());
						File removedFile = new File(savedModelFileName);
						if (removedFile != null  &&  removedFile.exists()  &&  removedFile.isFile()){
							removedFile.delete();
						}
					}
				}
			} else if (this.source.equals(infoPendants)){
				if (this.listItemSelected != null){
					 int choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to detele this saved info-pendant from the Editor?" 
				                ,"Warning", JOptionPane.WARNING_MESSAGE
				                , JOptionPane.OK_CANCEL_OPTION);
					if (choice == 0){
						savedModelFileName = dSetup.getInfopendantspath() + this.listItemSelected.toString() + ".eip";
						infoPendants.deleteElement(listItemSelected.toString());
						File removedFile = new File(savedModelFileName);
						if (removedFile != null  &&  removedFile.exists()  &&  removedFile.isFile()){
							removedFile.delete();
						}
					}
				}
				}
		}
	}
	
	
	
	/**
	 * load model from a file
	 * 
	 * @param file
	 *            a user model XML file
	 * @return CustomeMetadata object loaded from the XML file
	 * @throws JAXBException
	 */
	public static InfoPendant loadIPModel(File file) {
		if (file != null && file.isFile()) {
			InfoPendant infoPendant = (InfoPendant) loadXML(file,
					InfoPendant.class);
			return infoPendant;
		}
		return null;
	}

	/**
	 * load user model from a file
	 * 
	 * @param file
	 *            a user model XML file
	 * @return CustomeMetadata object loaded from the XML file
	 * @throws JAXBException
	 */
	public static UserModel loadUModel(File file) {

		UserModel userModel = (UserModel) loadXML(file, UserModel.class);
		if (userModel != null) {
			return userModel;
		} else {
			return null;
		}
	}

	/**
	 * load group model from a file
	 * 
	 * @param file
	 *            a user model XML file
	 * @return CustomeMetadata object loaded from the XML file
	 * @throws JAXBException
	 */
	public static GroupModel loadGModel(File file) {
		if (file != null && file.isFile()) {
			GroupModel gModel = (GroupModel) loadXML(file, GroupModel.class);
			if (gModel != null) {
				return gModel;
			}
		}
		return null;
	}

	/**
	 * Loads a model from a file containing a model in an XML format or an
	 * info-bead in a .class format
	 * 
	 * @param file
	 *            The file to load the data from
	 * @param name
	 *            The type of the file to be loaded
	 * @return The loaded object
	 */
	public static Object loadXML(File file, Class<?> name) {
		Object loaded = null;

		try {
			JAXBContext context = JAXBContext.newInstance(name);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			if (file.isFile()) {
				loaded = unmarshaller.unmarshal(file);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return loaded;

	}

	/**
	 * load metadata from a file
	 * 
	 * @param file
	 *            a metadata file
	 * @return CustomeMetadata object loaded from the metadata file
	 * @throws JAXBException
	 */
	public static CustomMetadata loadMetadata(File file) {
		if (file != null && file.isFile()) {
			CustomMetadata metadata = (CustomMetadata) loadXML(file,
					CustomMetadata.class);
			return metadata;
		}
		return null;
	}

	/**
	 * Returns the class name of the installed LookAndFeel with a name
	 * containing the name snippet or null if none found.
	 * 
	 * @param nameSnippet
	 *            a snippet contained in the Laf's name
	 * @return the class name if installed, or null
	 */
	public static String getLookAndFeelClassName(String nameSnippet) {
		LookAndFeelInfo[] plafs = UIManager.getInstalledLookAndFeels();
		for (LookAndFeelInfo info : plafs) {
			if (info.getName().contains(nameSnippet)) {
				return info.getClassName();
			}
		}
		return null;
	}

/**
 * 
 * @return a set of the info-beads' IDs that belong to the
 * same user model of a selected info-bead, within a group model
 */
	public Set<Integer> getModelInGroupModel() {

		Set<Integer> userModelIDs = new HashSet<Integer>();
		Integer userModelId;

		Integer infoBeadID = graphDisplay.getPickedVertex();
		
		if (this.isEditingGroupModel()){

			if (infoBeadID != null && infoBeadID != -1) {
				
				userModelId = gm.getVertexIdToModelIdMap().get(infoBeadID);
				userModelIDs = gm.string2IDs(gm.getModelIdToVertexIdMap().get(userModelId));
				
			} else {
				JOptionPane.showMessageDialog(null, " Please select first an info-bead first");
			} 
		} else {
			JOptionPane.showMessageDialog(null, "A sub model may be shown only within a group model");
		}

		return userModelIDs;
	}
	
	
	
	/**
	 * 
	 * @return the model ID of the
	 *  user model of a selected info-bead, within a group model
	 */
		public Integer getModelIdInGroupModel() {

			Integer subModelId = null;

			Integer infoBeadID = graphDisplay.getPickedVertex();
			
			if (this.isEditingGroupModel()){

				if (infoBeadID != null && infoBeadID != -1) {
					
					subModelId = gm.getVertexIdToModelIdMap().get(infoBeadID);
					
				} else {
					JOptionPane.showMessageDialog(null, " Please select an info-bead first");
				} 
			} else {
				JOptionPane.showMessageDialog(null, "A sub model may be shown only within a group model");
			}

			return subModelId;
		}
		
	
	
	/**
	 * Color the sub model of a selected info-bead within a group model
	 */
	public void showSubModel(){
		graphDisplay.setupVertexDisplay();
		graphDisplay.getVisual().repaint();

		Set<Integer> modelVerticesIDs = new HashSet<Integer>();
		modelVerticesIDs = getModelInGroupModel();

		if ((modelVerticesIDs != null) && (!modelVerticesIDs.isEmpty())) {
			for (Integer id : modelVerticesIDs) {
				graphDisplay.setFoundColor(id, true);
			}
			graphDisplay.getVisual().repaint();
		} 

	}

	
	
	/**
	 * Removes a sub-model (user model or group model) from a group model
	 */

	public void deleteSubModelFromGroupModel() {
		
		Integer vertexModelId = getModelIdInGroupModel();
		
		 int choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to detele this sub-model from the group model?" 
	                ,"Warning", JOptionPane.WARNING_MESSAGE
	                , JOptionPane.OK_CANCEL_OPTION);

		 
		if (choice == 0){
			if ((vertexModelId != null) &&  (vertexModelId != -1)) {
				if (this.isEditingGroupModel()){
					if (gm.getModelIdToModelTypeMap().get(vertexModelId) == ModelType.GROUPMODEL) {
						gm.removeSubGroupModel(vertexModelId);
					} else if (gm.getModelIdToModelTypeMap().get(vertexModelId) == ModelType.USERMODEL) {
						gm.removeSubUserModel(vertexModelId);
					}

				} else  {
					JOptionPane.showMessageDialog(null, "*** Error - this is not a group model");
				}
			}
		}
	}


	public void launchModel(){
		GenerateModel modelMain = new GenerateModel();
		if (this.isEditingUserModel()){
			modelMain.saveModelMainSrc(um);
		} else if (this.isEditingGroupModel()){
			modelMain.saveModelMainSrc(gm);
		}
	}
	
	
	/**
	 * Checks if an info-bead is a sensor info-bead (does not have inputs)
	 * @param infoBeadId the id of the info-bead
	 * @return true if the info-bead is a sensor info-bead, else returns false
	 */
	public boolean isService(Integer infoBeadId){
		Vertex v = new Vertex();
		boolean isService = false;
		if (this.isEditingUserModel()){
			v = um.getVertexMap().get(infoBeadId);
		} else if (this.isEditingGroupModel()){
			v = gm.getVertexMap().get(infoBeadId);
		}
		if (v != null){
			if (v.getOutputs() != null){
				if (v.getOutputs().length == 0){
					isService = true;
				} else {
					isService = false;
				}
			} else {
				isService = true;
			}			
		} else {
			isService = false;
		}
		
		return isService;
	}
	

	/**
	 * Checks if an info-bead is a service info-bead (does not have outputs)
	 * @param infoBeadId the id of the info-bead
	 * @return true if the info-bead is a service info-bead, else returns false
	 */
	public boolean isSensor(Integer infoBeadId){
		Vertex v = new Vertex();
		boolean isSensor = false;
		if (this.isEditingUserModel()){
			v = um.getVertexMap().get(infoBeadId);
		} else if (this.isEditingGroupModel()){
			v = gm.getVertexMap().get(infoBeadId);
		}
		if (v != null){
			if (v.getInputs() != null){
				if (v.getInputs().length == 0){
					isSensor = true;
				} else {
					isSensor = false;
				}
			} else {
				isSensor = true;
			}			
		} else {
			isSensor = false;
		}
		
		return isSensor;
	}
	
	
	
	/**
	 * Opens a window frame and presents the help data
	 * @param text the help text
	 * @param title the help topic to be presented at the window title
	 */
	public void  openHelpFrame(String text, String title){
		
		
		InfoPanel helpPanel = new InfoPanel();
		JFrame f = new JFrame();
		f.setContentPane(helpPanel);
     	f.setSize(400, 300);
     	f.setBackground(Color.lightGray);
     	f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
     	f.setTitle(title);
     	f.setVisible(true); 
     	helpPanel.write(text);


	}
	
	
	/**
	 * Reading help data from a file that has the UTF-16 and Unicode characters format 
	 * @param fileName the name of the help file (expected to be a .txt file
	 * @param helpWindowTitle the title to be presented as the help topic
	 */
	public void helpBufferedReader(String fileName, String helpWindowTitle) {
		
		File file = new File(fileName);
		String helpText = "";
		
		if (file.exists() && !file.isDirectory()){
			try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF-16")))
			{
	 
				String sCurrentLine;
	 
				while ((sCurrentLine = br.readLine()) != null) {
					helpText += "\n" + sCurrentLine;
				}
	 
			} catch (IOException e) {
				e.printStackTrace();
			} 
		openHelpFrame(helpText, helpWindowTitle);
		} else {
			JOptionPane.showMessageDialog(null, "Help file is not available");
		}
	 
	}
	
	
	/** 
	 * Presets the "about" help item
	 */
	public void helpAbout(){
		
		String aboutFileName = dSetup.getHelpPath() + "about.txt";
		helpBufferedReader(aboutFileName, "About the Info-bead User Model Editor");
		
	
	}
	
	
	/**
	 * Presents the help data about an info-bead which is selected either from the graph or from the info-beads list
	 */
	public void helpInfobead(){
		
		Integer infoBeadID = graphDisplay.getPickedVertex();
		String vertexName = "";
		if (infoBeadID != null && infoBeadID != -1){
			Vertex vertex = new Vertex();
			if (this.isEditingUserModel()){
				vertex = um.getVertexMap().get(infoBeadID);
			} else if (this.isEditingGroupModel()){
				vertex = gm.getVertexMap().get(infoBeadID);
			}
			vertexName = vertex.getName();
		} else if (this.source.equals(infoBeads)){
			if (this.listItemSelected != null){
				vertexName = this.listItemSelected;
			}
		} else {
			JOptionPane.showMessageDialog(null, "To use the info-bead help please select an info-bead from the graph or from the list on the left");
		} 
		
		String IbFileName = dSetup.getHelpPath() + vertexName + ".txt";
		helpBufferedReader(IbFileName, "Info-bead help");

	}
	
	
	
	/**
	 * Presents the help of the introduction to the info-bead user model editor
	 */
	public void helpIntroduction(){

		String introductionFileName = dSetup.getHelpPath() + "Introduction.txt";
		helpBufferedReader(introductionFileName, "Introduction to Info-bead User Model Editor");

	}
	
	

	
	
	
	/**
	 * Presents vertex data in the status bar
	 * @param vertexId the Id of the vertex whose data is the content of the status bar
	 */
	public void showStatusBar(Integer vertexId){
		Vertex vertex;
		String message = "Info-bead Editor status bar:";
		if (vertexId != -1){
			if (Editor.getInstance().isEditingUserModel()) {
				vertex = Editor.getInstance().getUserModel().getVertexMap().get(vertexId);
				message  += " now editing: USERMODEL, vertex: " + vertex.getName() + ", vertex ID: " + vertexId;
				Editor.getInstance().getStatusBar().setMessage(message);
			} else 	if (Editor.getInstance().isEditingGroupModel()) {
				vertex = Editor.getInstance().getGroupModel().getVertexMap().get(vertexId);
				Integer modelId = Editor.getInstance().getGroupModel().getVertexIdToModelIdMap().get(vertexId);
				if (modelId != null){
					String vertexModelId = modelId.toString();
					String vertexModelType = Editor.getInstance().getGroupModel().getModelIdToModelTypeMap().get(modelId).toString();
					String parentModelId = "";;
					if( Editor.getInstance().getGroupModel().getModelIdToParentModelIdMap() != null){
						parentModelId  = "" + Editor.getInstance().getGroupModel().getModelIdToParentModelIdMap().get(modelId);
					}
					message += " now editing : GROUPMODEL, vertex: " + vertex.getName() + ", vertex ID: " + vertexId + 
							", vertex type: " +  vertexModelType + " vertex, model Id: " +  vertexModelId + ",  parent-model ID: " + parentModelId;
					Editor.getInstance().getStatusBar().setMessage(message);
				} else {
					message += " Now editing: USERMODEL, vertex: " + vertex.getName() + ", vertex ID: " + vertexId;
					Editor.getInstance().getStatusBar().setMessage(message);
				}
			}
		}
		
		Editor.getInstance().getStatusBar().setMessage(message);


	}
	
	
	/* ~~~~~~~~~~~~~~~~~ getters & setters ~~~~~~~~~~~~~~~~~ */

	public boolean isBlankMode() {
		if (mode == Mode.BLANK) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isPickingMode() {
		if (mode == Mode.PICKING) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isEditingMode() {
		if (mode == Mode.EDITING) {
			return true;
		} else {
			return false;
		}
	}

	public Model getUserModel() {
		return um;
	}

	public GroupModel getGroupModel() {
		return gm;
	}

	public Integer getLastPickedVertexID() {
		return lastPickedVertexID;
	}

	public void setLastPickedVertexID(Integer vID) {
		lastPickedVertexID = vID;
	}

	public static Integer getLocalClipBoard() {
		return infoBeadClipBoard;
	}

	public static void setLocalClipBoard(Integer localClipBoard) {
		Editor.infoBeadClipBoard = localClipBoard;
	}

	public static Boolean getClosingState() {
		return closingState;
	}

	public static void setClosingState(Boolean closingState) {
		Editor.closingState = closingState;
	}

	public static File getWorkingUMFile() {
		return workingUMFile;
	}

	public static void setWorkingUMFile(File workingUMFile) {
		Editor.workingUMFile = workingUMFile;
	}

	public static EditedModelType getEditedModeType() {
		return editedModeType;
	}

	public void setEditedModeType(EditedModelType editedModeType) {
		Editor.editedModeType = editedModeType;
	}

	public GraphDisplay getGraphDisplay() {
		return graphDisplay;
	}

	public DirectoriesSetup getDSetup() {
		return dSetup;
	}

	public StatusBar getStatusBar() {
		return statusBar;
	}

}
