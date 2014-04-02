package infobeadCollection;

import genericInfoBead.InfoBead;
import genericInfoBead.InfoItem;
import genericInfoBead.MetadataPart;
import genericInfoBead.Triplet;
import services.CoreUserIdFactoryService;

import java.io.File;
import java.net.MalformedURLException;
import java.util.Date;


/**
 * 
 * @author Eyal Dim
 * @version 1.0
 * 
 * supplies the user ID 
 * The user Id starts with the string "UI"
 * 
 */
public class CoreUserIdInfobead extends InfoBead {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CoreUserIdInfobead (){
		super();
	}
	
	MetadataPart metadata = new MetadataPart();
	InfoItem infoItemData = new InfoItem();
    String tripletID = this.getInfoBeadId();
    String modelId = getInfobeadModelId();
 	Triplet data = new Triplet(tripletID);

	// This is the main purpose of this info-bead
 	final String userId = CoreUserIdFactoryService.getInstance().nextUserIdCount().toString();

	/**
	 * Initializing the info-bead
	 */
	@Override
	public void initialize() {
		
		//--------------- set content ----------------------
		
		// -------------- set metadataPart ------------------------
		
		metadata.setInfoBeadName("UserId");
		metadata.getInfoUnits().add("User ID string");
		metadata.getOutputInterface().add("see metadata file");
		metadata.getInputInterfaces().add("N/A");
		metadata.setVersion("v1.0");
		metadata.getBackwardCompatibility().add("v1.0");
		metadata.setContact("N/A");
		try {
			File helpFile = new File( ".\\elements\\help\\HMPSUserIdInfobead.txt" );
			metadata.setHelpFileURL(helpFile.toURI().toURL());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		metadata.getKeyWords().add("user ID info-bead");
		metadata.getKeyWords().add("user model");
		try {
			File metadataFile = new File(".\\elements\\infobeadMetadata\\HMPSUserIdInfobead.metadata");
			metadata.setMetadataFileURL(metadataFile.toURI().toURL());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		metadata.setPartNumber("Eyal Dim 1008");
		metadata.setSupplementalData("N/A");
		metadata.setTrustworthiness("N/A");
		
		this.setMetadata(metadata);

		// ------------- Set default info ----------------------
		Date currentTime = new Date(System.currentTimeMillis());
		infoItemData.setExplainInfo("sets user ID from the service ID factory. The ID starts with UI" );
		infoItemData.setInferenceTime(currentTime);
		infoItemData.setInfoValue((Object) userId);
		infoItemData.setInfoAccuracy("N/A");
		infoItemData.setInfoConfidence(1.0);
		infoItemData.setInfoValidityStartTime(currentTime);
		infoItemData.setInfoResolution("N/A");
		Date expirationDate = new Date(Long.MAX_VALUE); // forever
		infoItemData.setInfoValidityEndTime(expirationDate); 
		infoItemData.setInfoType("String");
		infoItemData.setInfoUnits("User ID");
		infoItemData.setReceptionTime(currentTime);
		infoItemData.setSampleRate(-1);
		infoItemData.setSupplementalData("N/A");
		infoItemData.setTimeAccuracy(1);
		infoItemData.setTimeUnits("milliseconds");
	
		this.getContent().add(infoItemData);  // keep the data within the info-bead
		this.setInfobeadUserOrGroupId(userId);
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
