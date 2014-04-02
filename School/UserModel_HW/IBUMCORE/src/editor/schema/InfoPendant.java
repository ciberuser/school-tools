package editor.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * An info-pendant is a sub-model that contains all info-beads that supply
 * the data required to generate the info-pendant attribute
 * 
 * @author Eyal
 *
 */
@XmlRootElement(name = "InfoPendant")
//@XmlType(propOrder = { "modelId", "vertexMap", "edgeMap", "outputLinkMap", "inputLinkMap" })
@XmlAccessorType(XmlAccessType.FIELD)

public class InfoPendant extends Model {
	
	public InfoPendant (){
		super();
	}
}
