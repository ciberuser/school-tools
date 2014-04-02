package infobeadCollection;

import java.io.File;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Date;
import genericInfoBead.*;

/**
 * AN example template that may be copied to create new info-beads
 * 
 * @author Eyal Dim
 * @version 1.0
 *
 */

public class CoreTemplateExample extends InfoBead {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public CoreTemplateExample(){
		super();
	}
 
	/* The following two parameters are model parameters 
	 set before the model was instantiated for a specific user or group).
	 They are set for each info-bead by the code that activates the user model or group model
	 They are copied here into local parameters*/
	String infobeadId = this.getInfoBeadId();
    String modelId = this.getInfobeadModelId();

    // setting up of the main components of the info-bead
	MetadataPart metadata = new MetadataPart();
	InfoItem infoItemData = new InfoItem();


	/**
	 * Initializing the info-bead
	 */
	@Override
	public void initialize() {
		
		
		
		// -------------- set metadataPart ------------------------
		
		// This data should be manually changed
		String infobeadName = "EnterHereTheInfobeadName";
		metadata.setInfoBeadName(infobeadName);
		metadata.getInfoUnits().add("The units used for the attributes");
		metadata.getOutputInterface().add("See metadata file");
		metadata.getInputInterfaces().add("See metadata file");
		metadata.setVersion("v1.0");
		metadata.getBackwardCompatibility().add("v1.0");
		metadata.setContact("N/A");
		// It is important to note that the help file is named by infobeadName.txt and the directory where it is located
		try {
			File helpFile = new File( ".\\elements\\help\\" + infobeadName + ".txt" );
			metadata.setHelpFileURL(helpFile.toURI().toURL());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		// Change, add or delete to get as many keywords as needed  and the directory where it is located
		metadata.getKeyWords().add("AAA"); 
		metadata.getKeyWords().add("BBB");
		metadata.getKeyWords().add("CCC");
		// It is important to note that the interfaces file is named by infobeadName.metadata
		try {
			File metadataFile = new File(".\\elements\\infobeadMetadata\\" + infobeadName + ".metadata");
			metadata.setMetadataFileURL(metadataFile.toURI().toURL());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		metadata.setPartNumber("Eyal Dim 1003"); // Any method of uniquely identifying the info-bead
		metadata.setSupplementalData("N/A");  // Any additional data (place holder for any fututre need
		metadata.setTrustworthiness("N/A");  
		
		// Enter the default metadata into the info-bead
		this.setMetadata(metadata);

		// ------Set default info,  it may be (partially or fully) changed when data is updated--------
		infoItemData.setExplainInfo("This is an example of an info-bead" );
		//Enter the attribute value of this info-bead here according to the info-bead value type/
		//It may be other type than a String
		infoItemData.setInfoValue("N/A");   // or set your own "no data" default value
		infoItemData.setInfoAccuracy ("N/A"); // or set your own accuracy of the value
		infoItemData.setInfoConfidence(-1); // -1 means no data. Otherwise, a value between 0 and 1.0 is expected
		infoItemData.setInfoResolution("N/A"); // or set your own resolution 
		infoItemData.setInfoType("N/A"); // change to the type of the attribute
		infoItemData.setInfoUnits("N/A"); // change to the units you use
		infoItemData.setSupplementalData("N/A"); // Place holder for additional data if needed
		
		//-----------------Set default timing for the info-------------------
		Date currentTime = new Date(System.currentTimeMillis());
		Long zero = (long) 0;
		Date zeroTime = new Date(zero);
		infoItemData.setInferenceTime(zeroTime); // set other time if needed
		infoItemData.setInfoValidityStartTime(zeroTime); // set other time if needed
		infoItemData.setInfoValidityEndTime(zeroTime);// set other time if needed
		infoItemData.setReceptionTime(currentTime);// should be changed to the receiving time of the evidence data
		infoItemData.setTimeUnits("milliseconds"); // If needed change the units of time stored here
		infoItemData.setTimeAccuracy(300); // The accuracy of the time tag
		infoItemData.setSampleRate(-1); // -1 means no data. Set here the number of milliseconds expected to elapse on the average between two consecutive reports of the info-bead  

		// Enter default content into the info-bead
		this.getContent().add(infoItemData);  // keep the data within the info-bead
		
	}
	

	/**
	 * Update based on input data from another info bead
	 * @param triplet the triplet containing the input attribute message
	 */
	
	protected void updateData(Triplet triplet){
		
		Date currentTime = new Date(System.currentTimeMillis());
		
				// Set the reception time now, to prevent the delay from the inference process
				infoItemData.setReceptionTime(currentTime);

		
				// Update the data that change based on the inference from the input data
				// There may be additional infoItem data that may change
				infoItemData.setExplainInfo("set explain info here: " + infobeadId); // Change manually
				
				// call the inference process
				String value = inference(triplet); // change this here to the real type of the inferred attribute value (call another method if needed
				infoItemData.setInfoValue((Object) value);
							
				// Update the timing in regard to the inferred attribute value
				infoItemData.setInferenceTime(triplet.getInfoItem().getInferenceTime()); // Set correctly
				infoItemData.setInfoValidityStartTime(triplet.getInfoItem().getInferenceTime()); // Set correctly
				Long deltaTimeInMilliseconds = new Long(3000);  //  3 second for example  - change as needed
				Date expirationDate = new Date((Long) (infoItemData.getInfoValidityStartTime().getTime() + deltaTimeInMilliseconds)); 
				infoItemData.setInfoValidityEndTime(expirationDate); 
				
				// Load the input data as evidence
				ArrayList<Triplet> inputDataList = new ArrayList<Triplet>();
				inputDataList.add(triplet); // There may be more than one triplet used for the inference
				infoItemData.setEvidence(inputDataList);
				infoItemData.setEvidenceStartTime(triplet.getTime()); // if the first triplet in the evidence array
				infoItemData.setEvidenceEndTime(triplet.getTime()); // if the last triplet in the evidence array
						
				this.getContent().add(infoItemData);  // keep the data within the info-bead
				
				////////////////  send the data to other info-beads /////////////////////////
				Triplet outData = new Triplet(infobeadId);  // send the data to the other info-beads
				outData.setAllIds(this.getInfobeadAllIds());
				outData.setInfoItem(infoItemData);
				currentTime = new Date(System.currentTimeMillis()); // get the time as close to the sending tile as possible
				outData.setTime(currentTime);
				this.pushData(outData);

	}


	/**
	 * handling the input data, for an input
	 */
	
	@Override
	public void handleData(Triplet inTriplet) {
		String inputSource = inTriplet.getId();
		String[] sourceName = inputSource.split("_");  // delete and ignore the info-bead unique ID after the "_"
		if (sourceName[0].equals("put here the info-bead name")) { // the name of the info-bead that sends the data
			updateData (inTriplet);
		} else {
			// add if statements as necessary for input from other info-beads
		}
	}
	
	
	// An example of an inference method that returns a string - this type should be changed  id needed
	public String inference(Triplet triplet){
		return triplet.getInfoItem().getInfoValue().toString(); // or change it to the the inferred value 
	}

}