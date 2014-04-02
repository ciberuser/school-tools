package infobeadCollection;

import genericInfoBead.InfoBead;
import genericInfoBead.InfoItem;
import genericInfoBead.MetadataPart;
import genericInfoBead.Triplet;
import java.io.File;
import java.net.MalformedURLException;
import java.util.Date;


/**
 * 
 * @author Eyal Dim
 * @version 1.0
 * 
 * supplies the owner ID which is always HMPS
 * 
 */
public class CoreOwner extends InfoBead {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CoreOwner(){
		super();
	}
	
	MetadataPart metadata = new MetadataPart();
	InfoItem infoItemData = new InfoItem();
    String tripletID = this.getInfoBeadId();
    String modelId = this.getInfobeadModelId();
	Triplet data = new Triplet(tripletID);

	// This is the main purpose of this info-bead
	final String owner = "OIHM"; //Stand for Owner Id is the Hecht Museum

	/**
	 * Initializing the info-bead
	 */
	@Override
	public void initialize() {
		
		//--------------- set content ----------------------
		
		// -------------- set metadataPart ------------------------
		
		metadata.setInfoBeadName("OwnerID");
		metadata.getInfoUnits().add("Owner ID string");
		metadata.getOutputInterface().add("see metadata file");
		metadata.getInputInterfaces().add("N/A");
		metadata.setVersion("v1.0");
		metadata.getBackwardCompatibility().add("v1.0");
		metadata.setContact("N/A");
		try {
			File helpFile = new File( ".\\elements\\help\\HMPSOwner.txt" );
			metadata.setHelpFileURL(helpFile.toURI().toURL());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		metadata.getKeyWords().add("owner ID info-bead");
		metadata.getKeyWords().add("user model");
		metadata.getKeyWords().add("group model");
		try {
			File metadataFile = new File(".\\elements\\infobeadMetadata\\HMPSUserIdInfobead.metadata");
			metadata.setMetadataFileURL(metadataFile.toURI().toURL());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		metadata.setPartNumber("Eyal Dim 1009");
		metadata.setSupplementalData("N/A");
		metadata.setTrustworthiness("N/A");
		
		this.setMetadata(metadata);

		// ------------- Set default info ----------------------
		Date currentTime = new Date(System.currentTimeMillis());
		infoItemData.setExplainInfo("sets the owner ID to HMPS" );
		infoItemData.setInferenceTime(currentTime);
		infoItemData.setInfoValue((Object) owner);
		infoItemData.setInfoAccuracy("N/A");
		infoItemData.setInfoConfidence(1.0);
		infoItemData.setInfoValidityStartTime(currentTime);
		infoItemData.setInfoResolution("N/A");
		Date expirationDate = new Date(Long.MAX_VALUE); // forever
		infoItemData.setInfoValidityEndTime(expirationDate); 
		infoItemData.setInfoType("String");
		infoItemData.setInfoUnits("Owner ID");
		infoItemData.setReceptionTime(currentTime);
		infoItemData.setSampleRate(-1);
		infoItemData.setSupplementalData("N/A");
		infoItemData.setTimeAccuracy(1);
		infoItemData.setTimeUnits("milliseconds");
	
		this.getContent().add(infoItemData);  // keep the data within the info-bead
		this.setInfobeadOwnerId(owner);
		data.setAllIds(this.getInfobeadAllIds());
		data.setTime(currentTime);
		data.setInfoItem(infoItemData);
		this.pushData(data);

	}
	
	
	/**
	 * No need for handleData because the info-bead is a sensor info-bead
	 */
	@Override
	public void handleData(Triplet data) {
		// no need to do anything' as there is no input from other info-beads
		
	}
	
	
}
