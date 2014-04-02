package architectureUtil;

import genericInfoBead.InfoBead;
import genericInfoBead.InfoItem;
import genericInfoBead.MetadataPart;
import genericInfoBead.Triplet;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

//import java.sql.Time;

/**
 * 
 * @author Eyal Dim
 * @version 1.0
 * 
 * Gets manual input from a selection  list within the same frame
 * If the selection is missing it is set to "N/A" and the expiration date to null 
 * @param attribute   contains the info-bead attribute name as a String (usually the list header)
 * @param  fileName   a String containing the file name (without the suffix) of the metadata file, the help file and the selection list file
 * @param separator   A character delimiter that separates each list item from the next
 *
 */
public class SelectionListSensor extends InfoBead implements Runnable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String attributeName = "attribute name";
	String fileNamePrefix = "file name";
	char delimiter = ',';
	boolean trigger = false; // if true the selection list will be activated immediately
	boolean frameExists = false; // if true no need to open another frame. all buttons will be opened within the same frame.

	
	public SelectionListSensor (String attribute, String fileName, char separator, boolean triggered){
		super();
		attributeName = attribute;
		fileNamePrefix = fileName;
		delimiter = separator;
		trigger = triggered;
		
	}
	

	
	
	MetadataPart metadata = new MetadataPart();
	InfoItem info = new InfoItem();
	ListSelectionSensor sensor = new ListSelectionSensor();

	
	/**
	 * Initializing the info-bead
	 */
	@Override
	public void initialize() {
		
		//--------------- set content ----------------------
		
		// -------------- set metadataPart ------------------------
		
		metadata.setInfoBeadName(attributeName);
		metadata.getInfoUnits().add(attributeName + " string");
		metadata.getOutputInterface().add("see metadata file");
		metadata.getInputInterfaces().add("N/A");
		metadata.setVersion("v1.0");
		metadata.getBackwardCompatibility().add("v1.0");
		metadata.setContact("N/A");
		try {
			File helpFile = new File( ".\\elements\\help\\" + fileNamePrefix + ".txt" );
			metadata.setHelpFileURL(helpFile.toURI().toURL());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		metadata.getKeyWords().add(attributeName);
		metadata.getKeyWords().add("Assistive technology");
		try {
			File metadataFile = new File(".\\elements\\infobeadMetadata\\" + fileNamePrefix + ".metadata");
			metadata.setMetadataFileURL(metadataFile.toURI().toURL());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		metadata.setPartNumber("Eyal Dim 2002");
		metadata.setSupplementalData("N/A");
		metadata.setTrustworthiness("N/A");
		
		this.setMetadata(metadata);

		// ------------- Set default info ----------------------
		Date currentTime = new Date(System.currentTimeMillis());
		info.setExplainInfo("Initializing, " + fileNamePrefix + " data not available" );
		info.setInferenceTime(currentTime);
		info.setInfoValue((Object) "NoData");
		info.setInfoAccuracy("N/A");  
		info.setInfoConfidence(1.0);
		info.setInfoValidityStartTime(currentTime);
		info.setInfoResolution("N/A");
		info.setInfoValidityEndTime(currentTime);
		info.setInfoType("String");
		info.setInfoUnits("Enum");
		info.setReceptionTime(currentTime);
		info.setSampleRate(-1);
		info.setSupplementalData("N/A");
		info.setTimeAccuracy(1);
		info.setTimeUnits("milliseconds");
		
		this.getContent().add(info);  // keep the data within the info-bead
		
		
		if (trigger){
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					sensor.selectFromList(); 
				}
			});
		}
		
	}
	

	/**
	 * No need for handleData because the info-bead is a sensor info-bead
	 */
	@Override
	public void handleData(Triplet data) {
		// no need to do anything' as there is no input from other info-beads
		
	}
	
	
	/**
	 * Update the info-bead with the input from the list
	 * @param inputString the input string
	 */
	protected void updateData(String inputString){

		if (inputString != "NoData" && inputString != null) {
			Date currentTime = new Date(System.currentTimeMillis());
			info.setExplainInfo("The manually reported " + attributeName );
			info.setInferenceTime(currentTime);
			info.setInfoValue((Object) inputString);
			info.setInfoValidityStartTime(currentTime);
			Date expirationDate = new Date(((long) currentTime.getTime() ) + (long) ((long) 1000 * 60 * 60 * 24 * 365 * 120)); // 120 years
			info.setInfoValidityEndTime(expirationDate);
			info.setReceptionTime(currentTime);
			
			this.getContent().add(info);  // keep the data within the info-bead
			
			Triplet data = new Triplet(this.getInfoBeadId());  // send the data to the other info-beads
			data.setInfoItem(info);
			this.pushData(data);
		}	else {
			JOptionPane.showMessageDialog(null, "Error - " +attributeName + " data not available\n");
		}
	}
	
	
	protected void periodicUpdate() {
		Triplet data = new Triplet(this.getInfoBeadId());  // send the data to the other info-beads
		data.setInfoItem(info);
		this.pushData(data);
	}
			
		
	 
	/////////////////////////////////////////////////////////////////////////////
	///////// Handling HMI for manual input of data ////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	/**
	 * Opens a window for manual input from a list
	 * @author Eyal Dim
	 *
	 */
	public class ListSelectionSensor extends JFrame {

		private static final long serialVersionUID = 1L;

		/**
		 * IdSelectOn returns true if in id selection mode,
		 * otherwise returns false 
		 */
		private class StateIsOn{ 
			
			private Boolean on;
			
			protected Boolean getOn() {
				return on;
			}
			protected void setOn(Boolean state) {
				this.on = state;
			}
		}
		
		final StateIsOn selectionStatus = new StateIsOn();
		
		
		/**
		 * Presents selection list
		 */
		public void selectFromList(){	
		
		selectionStatus.setOn(false);  //Default 
		
		//////////////// Set window /////////////////////
		

		final JScrollPane scrollPane1;		
		scrollPane1 = SingleFrameForAllButtons.getInstance().getScrollPane();
		
		
		///////////////  Set Buttons  /////////////////////
		JButton selectButton = new JButton("Select " + attributeName);
        JPanel view = ((JPanel)scrollPane1.getViewport().getView());
		view.add(selectButton, BorderLayout.EAST);
//		selectButton.setBounds(60, 30, 900, 35);
        view.validate();

		selectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selectionStatus.setOn(true); 
				returnInputFromList();
			}
		});

	}		
//
//		
		
		/**
		 * Assigning a selection from the list
		 * @return the selection string
		 */
		public String sensorInit() {
			
		
			Object[] options = loadListFile();
			
			String s;
			if (options[0] != "NoData"){
			
				final Icon EmptyIcon = null;		//needed by JFrame window
				Icon icon=EmptyIcon;
				 s = (String)JOptionPane.showInputDialog(
									null,
				                    "Please enter " + attributeName + ":\n",
				                    attributeName + " input Dialog",
				                    JOptionPane.PLAIN_MESSAGE,
				                    icon,
				                    options,
				                    "");
			} else {
				s= "NoData";
			}
			return (s);
		}
		
		/**
		 * Handles the actions requested by the window
		 */
		public void returnInputFromList() {
			
			String response = "NoData";
			
			if (selectionStatus.getOn()){
				response = sensorInit();
				if (response != null){
					updateData(response);
				}
			}
		}
		
			/**
			 * Loads Data from a file, that contains a comma separated list 
			 * @return an Object array of input data, or "NoData" in the first object if there is no selection item in the file or the file was not found
			 */
			private Object[] loadListFile(){

				ArrayList<String> idsNames = new ArrayList<String>();
				
				final String filePath = architectureUtil.DirectoriesSetup.getOtherFilesPath();
				File file = new File(filePath + fileNamePrefix + ".txt");
				String inputIdData = "";
				if (file != null && file.exists()){
					try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8")))
					{
						String sCurrentLine;
			 
						while ((sCurrentLine = br.readLine()) != null) {
							inputIdData += sCurrentLine;
						}
			 
					} catch (IOException e) {
						JOptionPane.showMessageDialog(null, "Error - " + attributeName + " file does not exist or cannot be used\n");
						idsNames.add("NoData");
					} 
				}
				
				if (inputIdData == null  ||  inputIdData == ""){
					JOptionPane.showMessageDialog(null, "Error - " + attributeName + " list is empty\n");
					idsNames.add("NoData");
				}
				
				int inputIdDataIndex = 0;
				String idString = "";
			
				while (inputIdData != null && inputIdData != ""){
					inputIdDataIndex = inputIdData.indexOf(delimiter);
					if (inputIdDataIndex > 0) {
						idString = inputIdData.substring(0,inputIdDataIndex );
						inputIdData = inputIdData.substring(inputIdDataIndex + 1 );
					} else if (inputIdData != "") {
						idString = inputIdData;
						inputIdData = "";
					}
					idsNames.add(idString);
				}
				
				Object[] idsArray = new Object[idsNames.size()];
				int i=0;
				for (String s : idsNames) {
					idsArray[i] =  s;
					i++;
				}

				return (idsArray);
			
			}

	}
	
	
	@Override
	public void run() {
	 // empty
		
	}


	public ListSelectionSensor getSensor() {
		return sensor;
	}


}
