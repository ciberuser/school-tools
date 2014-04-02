package genericInfoBead;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;



/**
 * @author Eyal Dim	
 * @version 1.0
 * 
 *  Components of this Class should be able to interface with other components with observer pattern (as an observer and an observable)
 * 
 *	important methods :
 *		void pushData(Triplet data) : used to send data (as a Triplet) from an Observable component to all its Observer components
 *	
 *	abstract methods that need implementation :
 *		void handleData(Triplet data) : this method is how the component will process the received data (the input)
 *		public void initialize(String id)
 *
 *	
 *  interfaces methods that need implementation :
 *		Zero or more input interfaces methods: 
 *			public Triplet getEvidence(String senderID, Time sentTime, Triplet inputData)
 *		Output interface:
 *			public void sendToConsumer(String infoBeadID, Time sentTime, Triplet outputData)
 * 
 */

public abstract class InfoBead extends Observable implements Observer, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private TripletId infobeadAllIds = new TripletId();
	private MetadataPart metadata = new MetadataPart();	// the metadata description of the info-bead
	private ArrayList<InfoItem> content = new ArrayList<InfoItem>(); // the information kept for each info-bead value
	
	private boolean active = true;  // defines when the info-bead is active (sending and receiving data)
									// and when it is not active (not receiving and not sending data)
	
	/**
	 * this method is how the component will process the received data (the input)
	 * @param data	is the input for the component
	 */
	
	public abstract void handleData(Triplet data);
	
	/**
	 * if needed this function will be used at the first activation of the component
	 */
	public abstract void initialize();
	

	
		
	
	/* -------------------------------------------------------------------------
	   ------------------- Implementation of Observable, the output interface -----
	   ------------------------------------------------------------------------- */
	
//	/**
//	 * used to send data (as a Triplet) from an Observable infoBead to all its Observer infoBeads
//	 * @param data - this is the output of the component 
//	 */
	public void pushData(Triplet data ){
		if (this.isActive()){
			if(this.countObservers()!=0){
				setChanged();
				notifyObservers(data);
			}	
		}
	}
	

	
		
	
	/* -------------------------------------------------------------------------
	   -------------------- Implementation of Observer the input interface ------
	   ------------------------------------------------------------------------- */
	
	@Override
	public void update(Observable o, Object arg) {
		if (this.isActive()){
			if(arg instanceof Triplet) {
				handleData((Triplet) arg);
			} 
		}
	}
	
	


	
	/* -------------------------------------------------------------------------
	   ----------- Implementation connectivity of info-beads to each other ------
	   ------------------------------------------------------------------------- */
	
	/**
	 * A front for addObserver (with an added check for Observer compatibility), that implement a link
	 * @parm infoBead an infoBead in the model 
	 */
	public void connect(InfoBead infoBead) {
		if(infoBead instanceof Observer) {
			addObserver((Observer)infoBead);
		}
	}

	
	/* -------------------------------------------------------------------------
	   ----------- Activation / deactivation of an info-bead -------------------
	   ------------------------------------------------------------------------- */
	
	public void activate(String elementId) {
		this.setActive(true);
	}


	public void deactivate(String elementId) {
		this.setActive(false);
	}

	
	/* -------------------------------------------------------------------------
	  TODO-------------------------- security checks ------------------------------
	   ------------------------------------------------------------------------- */
	
	
	
	/* -------------------------------------------------------------------------
	   --------------------- get ID availability info -------------------------
	   ------------------------------------------------------------------------- */
	public boolean hasOwnerId(){
		return this.infobeadAllIds.hasOwnerIdAssociated();
	}
	
	
	public boolean hasUserId(){
		return this.infobeadAllIds.hasUserIdAssociated();
	}
	
	public boolean hasGroupId(){
		return this.infobeadAllIds.hasGroupIdAssociated();
	}
	
	public boolean isUserModelInfobead(){
		return this.infobeadAllIds.isUserModelInfobead();
	}
	
	public boolean isGroupModelInfobead(){
		return this.infobeadAllIds.isGrouprModelInfobead();
	}
		
	
	
	/* -------------------------------------------------------------------------
	   --------------------- basic getters and setters -------------------------
	   ------------------------------------------------------------------------- */
	



	public MetadataPart getMetadata() {
		return metadata;
	}

	public void setMetadata(MetadataPart metadata) {
		this.metadata = metadata;
	}


	public ArrayList<InfoItem> getContent() {
		return content;
	}


	public void setContent(ArrayList<InfoItem> content) {
		this.content = content;
	}


	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	
	public TripletId getInfobeadAllIds(){
		return infobeadAllIds;
	}
	
	public void setInfobeadAllIds(TripletId newIds){
		this.infobeadAllIds.setInfobeadID(newIds.getInfobeadID());
		this.infobeadAllIds.setModelID(newIds.getModelID());
		this.infobeadAllIds.setOwnerID(newIds.getOwnerID());
		this.infobeadAllIds.setUserOrGroupID(newIds.getUserOrGroupID());
	}
	
	public String getInfoBeadId() {
		return infobeadAllIds.getInfobeadID();
	}

	public void setInfoBeadId(String newId) {
		this.infobeadAllIds.setInfobeadID(newId);
	}

	
	public String getInfobeadOwnerId() {
		return infobeadAllIds.getOwnerID();
	}

	public void setInfobeadOwnerId(String ownerId) {
		this.infobeadAllIds.setOwnerID(ownerId);
	}

	public String getInfobeadModelId() {
		return infobeadAllIds.getModelID();
	}

	public void setInfobeadModelId(String modelId) {
		this.infobeadAllIds.setModelID(modelId);
	}

	public String getInfobeadUserOrGroupId() {
		return infobeadAllIds.getUserOrGroupID();
	}

	public void setInfobeadUserOrGroupId(String userOrGroupId) {
		this.infobeadAllIds.setUserOrGroupID(userOrGroupId);
	}

}
