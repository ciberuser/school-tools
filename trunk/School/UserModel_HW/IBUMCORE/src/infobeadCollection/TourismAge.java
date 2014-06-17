package infobeadCollection;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import genericInfoBead.*;

/**
 * The infobead is calculating the age of the user. 
 * 
 * @author Renana Gonen & ____
 * @version 1.0
 *
 */

public class TourismAge extends InfoBead  implements Runnable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public TourismAge(){
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

	Double Age = -1.0;
	Triplet newIn;
	boolean flag=false;
	/**
	 * Initializing the info-bead
	 */
	@Override
	public void initialize() {
		
		
		
		// -------------- set metadataPart ------------------------
		
		// This data should be manually changed
		String infobeadName = "TourismAge";
		metadata.setInfoBeadName(infobeadName);
		metadata.getInfoUnits().add("Double");
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
		metadata.getKeyWords().add("Age"); 
		metadata.getKeyWords().add("Age of customer");
		// It is important to note that the interfaces file is named by infobeadName.metadata
		try {
			File metadataFile = new File(".\\elements\\infobeadMetadata\\" + infobeadName + ".metadata");
			metadata.setMetadataFileURL(metadataFile.toURI().toURL());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		metadata.setPartNumber("Age-12345-12345"); // Any method of uniquely identifying the info-bead
		metadata.setSupplementalData("N/A");  // Any additional data (place holder for any fututre need
		metadata.setTrustworthiness("N/A");  
		
		// Enter the default metadata into the info-bead
		this.setMetadata(metadata);

		// ------Set default info,  it may be (partially or fully) changed when data is updated--------
		infoItemData.setExplainInfo("info bead that gets the BirthDate of a customer and calculates its age" );
		//Enter the attribute value of this info-bead here according to the info-bead value type/
		//It may be other type than a String
		infoItemData.setInfoValue(Age.toString());   // or set your own "no data" default value
		infoItemData.setInfoAccuracy ("N/A"); // or set your own accuracy of the value
		infoItemData.setInfoConfidence(0.9); // the confidence is 90% because can be some situation that the user gave the same grade to more then 1 region.
		infoItemData.setInfoResolution("N/A"); // or set your own resolution 
		infoItemData.setInfoType("Double"); // change to the type of the attribute
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
				infoItemData.setExplainInfo("The user's age is: " + this.Age.toString()); // Change manually
				
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
				infoItemData.setInfoValue(this.Age.toString());
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
		newIn=inTriplet;
		
	
		
		Thread AgeThread = new Thread(this,"Age");
		AgeThread.start();
	}


	@Override
	public void run() {
		

		try {
			Thread.sleep(3300);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
//		/	e.printStackTrace();
		}
		
		String inputSource = newIn.getId();
		String[] sourceName = inputSource.split("_");  // delete and ignore the info-bead unique ID after the "_"
		
		//System.out.println("The birthDate you got from "+ sourceName[0]+" is "+ newIn.getInfoItem().getInfoValue().toString());
		Date BirthDate = new Date();
		if (sourceName[0].equals("TourismDateOfBirth")){
			String temp = (String) newIn.getInfoItem().getInfoValue();
			
			try{
				BirthDate = new SimpleDateFormat("dd/mm/yy", Locale.ENGLISH).parse(temp);
			}catch(Exception e){
				e.printStackTrace();
			}
			DateFormat dateFormat = new SimpleDateFormat("dd/mm/yy");
			Date date = new Date();
			String TodayString = dateFormat.format(date);
			Date Today= new Date();
			try{
				Today = new SimpleDateFormat("dd/mm/yy", Locale.ENGLISH).parse(TodayString);
			}catch(Exception e){
				e.printStackTrace();
			}
			
			
			
			long diff = Today.getTime() - BirthDate.getTime();
			long diffDays = diff / (24 * 60 * 60 * 1000);
			long years = diffDays/365;
			Double value =Double.parseDouble(new DecimalFormat("##.#").format(years));
			this.Age = value;
		}
		
		if(!flag){	
			
			System.out.println("7.Your Birth Data as we recive is  :"+ BirthDate.toString() + "\n" );
			System.out.println("=========================================================");
			System.out.println("Then your Age is :"+ this.Age.toString()+ "\n");
	}
			flag = true;
			updateData (newIn);
	
	}

}