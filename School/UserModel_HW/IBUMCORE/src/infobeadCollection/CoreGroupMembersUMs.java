package infobeadCollection;

import java.io.File;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Date;
import genericInfoBead.*;

/**
 * A group info-bead that keeps the IDs of the group-members' UMs in an ArrayList<String>
 * If there is no record of members model the ArrayList is empty
 * 
 * @author Eyal Dim
 * @version 1.0
 *
 */

public class CoreGroupMembersUMs extends InfoBead {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public CoreGroupMembersUMs(){
		
		super();
		
	}
	

	MetadataPart metadata = new MetadataPart();
	InfoItem infoItemData = new InfoItem();
    String tripletID = this.getInfoBeadId();
    String modelId = this.getInfobeadModelId();
    
    //This is the main purpose of this info-bead
	ArrayList<String> groupMembersIds = new ArrayList<String>();  // if no group members, the ArrayList is empty


	/**
	 * Initializing the info-bead
	 */
	@Override
	public void initialize() {
		
		
		// -------------- set metadataPart ------------------------
		
		metadata.setInfoBeadName("HMPSGroupMembersUMs");
		metadata.getInfoUnits().add("An ArrayList<String> of group members model IDs (stating all with UM");
		metadata.getOutputInterface().add("See metadata file");
		metadata.getInputInterfaces().add("See metadata file");
		metadata.setVersion("v1.0");
		metadata.getBackwardCompatibility().add("v1.0");
		metadata.setContact("N/A");
		try {
			File helpFile = new File( ".\\elements\\help\\HMPSGroupMembersUMs.txt" );
			metadata.setHelpFileURL(helpFile.toURI().toURL());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		metadata.getKeyWords().add("HMPS");
		metadata.getKeyWords().add("Hecht Museum Positioning System");
		metadata.getKeyWords().add("Group info-bead");
		metadata.getKeyWords().add("Group members");

		try {
			File metadataFile = new File(".\\elements\\infobeadMetadata\\HMPSGroupMembersUMs.metadata");
			metadata.setMetadataFileURL(metadataFile.toURI().toURL());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		metadata.setPartNumber("Eyal Dim 1012");
		metadata.setSupplementalData("N/A");
		metadata.setTrustworthiness("N/A");
		
		this.setMetadata(metadata);

		// ------------- Set default info ----------------------
		Date currentTime = new Date(System.currentTimeMillis());
		infoItemData.setExplainInfo("Initializing, group member UMs data are not available" );
		infoItemData.setInferenceTime(currentTime);
		infoItemData.setInfoValue(groupMembersIds);
		infoItemData.setInfoAccuracy("N/A");
		infoItemData.setInfoConfidence(1.0);
		infoItemData.setInfoValidityStartTime(currentTime);
		infoItemData.setInfoResolution("1 millisecind");
		Date expirationDate = new Date(currentTime.getTime() + (1000 * 60 * 60 * 6)); // 6 hours of visit in the museum
		infoItemData.setInfoValidityEndTime(expirationDate);
		infoItemData.setInfoType("ArrayList<String> of UM IDs");
		infoItemData.setInfoUnits("String starting with UM");
		infoItemData.setReceptionTime(currentTime);
		infoItemData.setSampleRate(1000); // once a second (1000 milliseconds)
		infoItemData.setSupplementalData("N/A");
		infoItemData.setTimeAccuracy(300);
		infoItemData.setTimeUnits("milliseconds");
		
		this.getContent().add(infoItemData);  // keep the data within the info-bead
		
	}
	

	/**
	 * Aggregate the user models of the group members
	 * @param triplet the triplet containing a HMPS DataAttribute message
	 */
	
	private void updateGroupMembersUmIdData(Triplet triplet){
		
		Boolean dataChanged = false;
		String inputModelID = triplet.getAllIds().getModelID();
		if (inputModelID != null  &&  !inputModelID.isEmpty()  &&  inputModelID.substring(0, 2).equals("UM")){
			if (groupMembersIds != null){
				if (groupMembersIds.isEmpty() ){
					groupMembersIds.add(inputModelID);
					dataChanged = true;
				} else if ( ! groupMembersIds.contains(inputModelID)) {
					groupMembersIds.add(inputModelID);
					dataChanged = true;
				}
			}
		}
		
		if (dataChanged){
			Date currentTime = new Date(System.currentTimeMillis());
			infoItemData.setExplainInfo("The user UM as identified in the info-bead id is: " + inputModelID);
			infoItemData.setInferenceTime(currentTime);
			String membersString = "";
			if ( ! groupMembersIds.isEmpty()){
				for (int i = 0; i < groupMembersIds.size(); i++ ){
					membersString += groupMembersIds.get(i);
					if (i < groupMembersIds.size()-1 ) {
						membersString += ",";
					}
				}
			}
			infoItemData.setInfoValue(membersString);
			infoItemData.setInfoValidityStartTime(currentTime);
			Date expirationDate = new Date(currentTime.getTime() + (1000 * 60 * 60 * 6)); // 6 hours of visit in the museum
			infoItemData.setInfoValidityEndTime(expirationDate);
			infoItemData.setReceptionTime(currentTime);
			ArrayList<Triplet> tripletArray = new ArrayList<Triplet>();
			tripletArray.add(triplet);
			infoItemData.setEvidence(tripletArray);
			infoItemData.setEvidenceStartTime(triplet.getInfoItem().getInferenceTime());
			infoItemData.setEvidenceEndTime(triplet.getInfoItem().getInferenceTime());
			infoItemData.setReceptionTime(currentTime);
			this.getContent().add(infoItemData); 
			
			Triplet outTriplet = new Triplet(tripletID);
			outTriplet.setAllIds(this.getInfobeadAllIds());
			outTriplet.setTime(currentTime);
			outTriplet.setInfoItem(infoItemData);
			
			pushData(outTriplet);
			
			dataChanged = false;
		}
	}
	
	
	/**
	 * handling the input data, in this case  the Blind ID of the user
	 */
	
	@Override
	public void handleData(Triplet data) {
	
		updateGroupMembersUmIdData(data);
	}

}