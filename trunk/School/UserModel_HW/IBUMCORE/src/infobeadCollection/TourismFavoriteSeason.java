package infobeadCollection;

import java.io.File;
import java.net.MalformedURLException;
import java.util.Date;





import genericInfoBead.*;

/**
 * the Info bead season presents the favorite season that tourist prefer
 * 
 * @author Mohammad && Attrash
 * @version 1.0
 *
 */
public class TourismFavoriteSeason extends InfoBead implements Runnable   {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TourismFavoriteSeason(){
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
	//private String Value;
	
	
	private String  favoriteSeason;
	ReadDataFromExcel readData = new ReadDataFromExcel("InfoBeads.xlsx",1,3);
	
	/**
	 * Initializing the info-bead
	 */
	@Override
	public void initialize() {
		
		// -------------- set metadataPart ------------------------
		
		// This data should be manually changed
		String infobeadName = "TourismFavoriteSeason";
		metadata.setInfoBeadName(infobeadName);
		metadata.getInfoUnits().add("Enumerated: Spring ,Summer ,Autumn ,Winter, NoData ");
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
		metadata.getKeyWords().add("favorite season");
		metadata.getKeyWords().add("Tourist season");
		
		// It is important to note that the interfaces file is named by infobeadName.metadata
		try {
			File metadataFile = new File(".\\elements\\infobeadMetadata\\" + infobeadName + ".metadata");
			metadata.setMetadataFileURL(metadataFile.toURI().toURL());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		metadata.setPartNumber("Season "); // Any method of uniquely identifying the info-bead
		metadata.setSupplementalData("N/A");  // Any additional data (place holder for any fututre need
		metadata.setTrustworthiness("N/A");  
		
		// Enter the default metadata into the info-bead
		this.setMetadata(metadata);

		
		// ------Set default info,  it may be (partially or fully) changed when data is updated--------
		infoItemData.setExplainInfo("Info Bead that presnts the seson that tourist prefer to travel " );
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
		
		Thread SeasonThread = new Thread(this,"Season");
		SeasonThread.start();
	}


	@Override
	public void handleData(Triplet data) {
	//No Need
	}
	
	protected void updateData(){
		
		Date currentTime = new Date(System.currentTimeMillis());
		
		// Set the reception time now, to prevent the delay from the inference process
		infoItemData.setReceptionTime(currentTime);
		infoItemData.setExplainInfo(explain());
		
		// Update the timing in regard to the inferred attribute value
		//infoItemData.setInferenceTime(triplet.getInfoItem().getInferenceTime()); 
		//infoItemData.setInfoValidityStartTime(favoriteSeasonValidFrom); 
		//infoItemData.setInfoValidityEndTime(favoriteSeasonValidUntil); 
		
		Long deltaTimeInMilliseconds = new Long(5000);  //  3 second for example  - change as needed
		Date expirationDate = new Date((Long) (infoItemData.getInfoValidityStartTime().getTime() + deltaTimeInMilliseconds)); 
		infoItemData.setInfoValidityEndTime(expirationDate); 
		
		this.getContent().add(infoItemData);  // keep the data within the info-bead
		
////////////////send the data to other info-beads /////////////////////////
		Triplet OutData = new Triplet(infobeadId);  // send the data to the other info-beads
		OutData.setAllIds(this.getInfobeadAllIds());
		infoItemData.setInfoValue(this.favoriteSeason);
		OutData.setInfoItem(infoItemData);
		
		currentTime = new Date(System.currentTimeMillis()); // get the time as close to the sending tile as possible
		OutData.setTime(currentTime);
		this.pushData(OutData);
		
		
	}
	
	public String explain(){
		
		return "The favorite season may be: Summer, Winter, Spring, Autumn or Nodata.\n"
				+ "The favorite season value is: " + readData.ReadData();	
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		this.favoriteSeason=readData.ReadData().toString();
		updateData();
	}


	
	
}
