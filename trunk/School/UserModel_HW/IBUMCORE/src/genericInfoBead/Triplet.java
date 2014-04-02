package genericInfoBead;

//import InfoItem;

import java.util.Date;

/**
 * 
 * @author Eyal Dim
 * @version 1.0
 * This is the basic structure of an evidence in an info-bead
 *
 */
public class Triplet {
	
	private TripletId allIds = new TripletId();
	private Date timeDate;	// the time when the data was generated
	private InfoItem infoItem; // the data content
	
	//Setters and getters below
	
	public Triplet(String elementId) {
		allIds.setInfobeadID(elementId);
		timeDate = new Date(System.currentTimeMillis());
	}

	public String getId() {
		return allIds.getInfobeadID();
	}

	public void setId(String id) {
		this.allIds.setInfobeadID(id);
	}
	
	public TripletId getAllIds(){
		return this.allIds;
	}
	
	public void setAllIds(TripletId inAllIds){
		this.allIds.setInfobeadID(inAllIds.getInfobeadID());
		this.allIds.setModelID(inAllIds.getModelID());
		this.allIds.setOwnerID(inAllIds.getOwnerID());
		this.allIds.setUserOrGroupID(inAllIds.getUserOrGroupID());
	}
	
	
	public Date getTime() {
		return timeDate;
	}

	public void setTime(Date time) {
		this.timeDate = time;
	}

	public InfoItem getInfoItem() {
		return infoItem;
	}

	public void setInfoItem(InfoItem infoItem) {
		this.infoItem = infoItem;
	}
		
	
}
