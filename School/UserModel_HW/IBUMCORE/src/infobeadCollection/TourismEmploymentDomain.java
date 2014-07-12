package infobeadCollection;

import java.io.File;
import java.net.MalformedURLException;
import java.util.Date;




import genericInfoBead.*;

/**
 * the info bead presents the employment domain of tourist
 * 
 * @author Mohammad && Attrash
 * @version 1.0
 *
 */
public class TourismEmploymentDomain extends InfoBead implements Runnable {
	
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	
	//String employe;
	
	
	public TourismEmploymentDomain(){
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
	
	//enum EmploymentDomain{Hightech,Lecturer,Independent,Employee,NoData};
	//EmploymentDomain employeeDomain;
	
	ReadDataFromExcel readData = new ReadDataFromExcel("InfoBeads.xlsx",8,3);
	String EmploymentDomain;
	
	
	/**
	 * Initializing the info-bead
	 */
	@Override
	public void initialize() {
		

		// -------------- set metadataPart ------------------------
		
		// This data should be manually changed
		String infobeadName = "TourismEmploymentDomain";
		metadata.setInfoBeadName(infobeadName);
		metadata.getInfoUnits().add("Enumerated: Hightech,Lecturer,Independent,Employee,NoData");
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
		metadata.getKeyWords().add("employee"); 
		metadata.getKeyWords().add("domain");
		metadata.getKeyWords().add("employment domain");
		
		
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
		infoItemData.setExplainInfo("Employment Domain" );
		//Enter the attribute value of this info-bead here according to the info-bead value type/
		//It may be other type than a String
		
		infoItemData.setInfoValue("NoData");   // or set your own "no data" default value
		infoItemData.setInfoAccuracy ("N/A"); // or set your own accuracy of the value
		infoItemData.setInfoConfidence(0.9); // -1 means no data. Otherwise, a value between 0 and 1.0 is expected
		infoItemData.setInfoResolution("N/A"); // or set your own resolution 
		infoItemData.setInfoType("String"); // change to the type of the attribute
		infoItemData.setInfoUnits("Enumerated"); // change to the units you use
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
		
		Thread DomainThread = new Thread(this,"Domain");
		DomainThread.start();
		
	}


	@Override
	public void handleData(Triplet inTriplet) {
		//No Need	
	}
	
	protected void updateData(){

		Date currentTime = new Date(System.currentTimeMillis());
		
		// Set the reception time now, to prevent the delay from the inference process
		infoItemData.setReceptionTime(currentTime);
		infoItemData.setExplainInfo(explain());
		
		// Update the timing in regard to the inferred attribute value
		
		Long deltaTimeInMilliseconds = new Long(5000);  //  3 second for example  - change as needed
		Date expirationDate = new Date((Long) (infoItemData.getInfoValidityStartTime().getTime() + deltaTimeInMilliseconds)); 
		infoItemData.setInfoValidityEndTime(expirationDate); 
		this.getContent().add(infoItemData);  // keep the data within the info-bead
		
		this.getContent().add(infoItemData);  // keep the data within the info-bead
		
////////////////send the data to other info-beads /////////////////////////
		Triplet outData = new Triplet(infobeadId);  // send the data to the other info-beads
		outData.setAllIds(this.getInfobeadAllIds());
		infoItemData.setInfoValue(this.EmploymentDomain);
		outData.setInfoItem(infoItemData);
		currentTime = new Date(System.currentTimeMillis()); // get the time as close to the sending tile as possible
		
		outData.setTime(currentTime);
		this.pushData(outData);
		
	}

	
	
	
	public String explain(){
		
		return "The employment domain may be: Hightech ,Lecturer ,Independent ,Employee or NoData.\n"
				+ "The employment domain value is: " + readData.ReadData();
	}


	@Override
	public void run() {
		this.EmploymentDomain=readData.ReadData().toString();
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
		updateData();
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

