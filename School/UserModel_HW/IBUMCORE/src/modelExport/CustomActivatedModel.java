package modelExport;


import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "activatedModel")
@XmlType(propOrder = {"infoBeads",  "infoLinks"})
@XmlAccessorType(XmlAccessType.FIELD)
/**
 * mock meta for only the stuff that the editor needs
 */
public class CustomActivatedModel {

	@XmlElement(name="infoBeads")
	private ArrayList<String>	infoBeads = new ArrayList<String>() ;	//Contains the class name and the model id separated by comma
	

	@XmlElement(name="infoLinks")
	private ArrayList<String>	infoLinks = new ArrayList<String>() ;	// Contains the source info-bead and the target info-bead separated by comma


	public ArrayList<String> getInfoBeads() {
		return infoBeads;
	}


	public void setInfoBeads(ArrayList<String> infoBeads) {
		this.infoBeads = infoBeads;
	}


	public ArrayList<String> getInfoLinks() {
		return infoLinks;
	}


	public void setInfoLinks(ArrayList<String> infoLinks) {
		this.infoLinks = infoLinks;
	}



	
	
	
}
