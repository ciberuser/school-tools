package editor.gui;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import org.apache.commons.io.FilenameUtils;

import architectureUtil.DirectoriesSetup;

import editor.gui.Editor.ElementType;
import editor.metadata.CustomMetadata;
import editor.schema.Model;

/**
 * also need to call the initialize(Element type) to set the Element list to one of : INFOBEADS/SENSORINFOBEADS/SERVICEINFOBEADS
 * and also populate the list with existing elements from the predefined folders ......
 * @version 11Jan2013, 2032
 */
public class ElementList extends JList<String>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ElementType type;
	private DefaultListModel<String> listModel;
	private LinkedHashMap<String, CustomMetadata> metadataMap = new LinkedHashMap<>();
	private LinkedHashMap<String, Model> modelsMap = new LinkedHashMap<>();
	private File elementPath = null;
	private DirectoriesSetup dSetup = new DirectoriesSetup();
	private final String metadataExtension = ".metadata";
	private final String eumExtension = ".eum";
	private final String egmExtension = ".egm";
	private final String eipExtension = ".eip";
//	private ModelConversions convert = new ModelConversions();
	
	public ElementList() {
		super();
	}
	
	/**	define the default folder for the elements */
	private void defineFolder() {
		switch (type) {
			case INFOBEAD:
				elementPath = new File(dSetup.getMetadataPath());
				break;
			case INFOPENDANT:
				elementPath = new File(dSetup.getInfopendantspath());
				break;
			case USERMODEL:
				elementPath = new File(dSetup.getUserModelsPath());
				break;
			case GROUPMODEL:
				elementPath = new File(dSetup.getGroupModelsPath());
				break;
			default:
				JOptionPane.showMessageDialog(null, "<editor.ElementList> -- Error -- unknown list type \n");

			}
		}
	
	/** add elements from default folder to the list  */
	private void populate(){
		ArrayList<File> metadataFiles = new ArrayList<>();
		ArrayList<File> eumFiles = new ArrayList<>();
		ArrayList<File> egmFiles = new ArrayList<>();
		ArrayList<File> eipFiles = new ArrayList<>();
		CustomMetadata metadata = null;
		Model model = null;

		// list all the metadata files in the default folder 
		if (elementPath.isDirectory()){
			if  (elementPath.list().length>0){
				File[] listOfFiles = elementPath.listFiles();
				for(File file:listOfFiles){
					if(file.isFile()){
						if(file.getName().endsWith(metadataExtension)){
							metadataFiles.add(file);
						}
						if(file.getName().endsWith(eumExtension)){
							eumFiles.add(file);
						} else if (file.getName().endsWith(egmExtension)){
							egmFiles.add(file);
						} else if (file.getName().endsWith(eipExtension)){
							eipFiles.add(file);
						}
					}
				}
				
				for(File file:metadataFiles){
						metadata = Editor.loadMetadata(file);
						if(metadata!=null){
							addElement(FilenameUtils.removeExtension(file.getName()), metadata);
						}
				}
				
				for(File file:eipFiles){
					model = Editor.loadIPModel(file);
					if(model!=null){
						addElement(FilenameUtils.removeExtension(file.getName()), model);
					}
				}
				
				for(File file:eumFiles){
					model = Editor.loadUModel(file);
					if(model!=null){
						addElement(FilenameUtils.removeExtension(file.getName()), model);
					}
				}
				
				for(File file:egmFiles){
					model = Editor.loadGModel(file);
					if(model!=null){
						addElement(FilenameUtils.removeExtension(file.getName()), model);
					}
				}

			} 
		} 

			
	}
	
	/**
	 * adds an element to the ElementList
	 * @param name		name of the class/element
	 * @param metadata	the metadata
	 */
	public void addElement(String name, CustomMetadata metadata){
		metadataMap.put(name, metadata);
		listModel.add(listModel.getSize(), name);
	}
	
	
	/**
	 * adds an model element (either a user model or a group model or an info-pendant) to the ElementList
	 * @param name		name of the class/element
	 * @param mode		the model (either a user model or a group model or an info-pendant)
	 */
	public void addElement(String name, Model submodel){
		modelsMap.put(name, submodel);
		listModel.add(listModel.getSize(), name);
	}
	
	
	/**
	 * Deletes a model element ((either a user model or a group model or an info-pendant) from the ElementList
	 * @param name the name of the element to be deleted
	 */
	public void deleteElement(String name){
		modelsMap.remove(name);
		int index = listModel.indexOf(name);
		listModel.remove(index);
	}
	
	
	
		
	/**  
	 * set the element type for the list, populate the list with elements already present in the default folder
	 * and also load all the metadata of the elements and add it to the metadataMap
	 * @param type the type of elements that the list will hold
	 */
	public void initialize(ElementType type){
		this.type = type;
		listModel = new DefaultListModel<>();
		this.setModel(listModel);
		
		defineFolder();
		populate();		
			
		// request focus 
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent event) {
				super.mouseEntered(event);
				ElementList list = (ElementList) event.getSource();
				list.requestFocus();	
			}
			
			// element selection
			@Override
			public void mouseClicked(MouseEvent event) {
				ElementList list = (ElementList) event.getSource();
				//if(event.getClickCount() ==1){// for double click and ....
				//}
					int index = list.locationToIndex(event.getPoint());
					if(index>=0 && index<=list.getModel().getSize()){
						String selected = list.getModel().getElementAt(index);
						Editor.getInstance().elementSelected(list, selected);
					}
				
			}
		});


		// clear selection when escape key is pressed
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent key) {
				if((key.getKeyCode() == KeyEvent.VK_ESCAPE)){
					Editor.getInstance().escapeKey();
				}
			}
		});		
	}

	/* ~~~~~~~~~~~~~~~~~ getters & setters ~~~~~~~~~~~~~~~~~ */
	

	public ElementType getType() {
		return type;
	}

	public void setType(ElementType type) {
		this.type = type;
	}

	public LinkedHashMap<String, CustomMetadata> getMetadataMap() {
		return metadataMap;
	}

	public void setMetadataMap(LinkedHashMap<String, CustomMetadata> metadataMap) {
		this.metadataMap = metadataMap;
	}
	
	public File getElementPath(){
		return elementPath;
	}
	
	
	


	
	
}
