package editor.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import editor.factory.ModelFactory;



/**
 * An user-model is a sub-model that contains all info-beads that supply
 * the data required to generate the user-model attributes
 * 
 * @author Eyal
 *
 */
@XmlRootElement(name = "UserModel")
//@XmlType(propOrder = { "modelId", "vertexMap", "edgeMap", "outputLinkMap", "inputLinkMap" })
@XmlAccessorType(XmlAccessType.FIELD)

public class UserModel extends Model {
	
	public UserModel(){
		super();
		this.setModelId (ModelFactory.getInstance().getCounterForNewModel());
	}
	
	
}
