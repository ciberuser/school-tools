package editor.metadata;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "metadata")
@XmlType(propOrder = {"inputInterfaces", "outputInterface"})
@XmlAccessorType(XmlAccessType.FIELD)
/**
 * mock meta for only the stuff that the editor needs
 */
public class CustomMetadata {

	@XmlElement(name="input-interface")
	private String[]	inputInterfaces;		// one or more interfaces that are used as input
	@XmlElement(name="output-interface")
	private String[]	outputInterface;		// only one interface is used for output
	
	public String[] getInputInterfaces() {
		return inputInterfaces;
	}
	public void setInputInterfaces(String[] inputInterfaces) {
		this.inputInterfaces = inputInterfaces;
	}
	public String[] getOutputInterface() {
		return outputInterface;
	}
	public void setOutputInterface(String[] outputInterface) {
		this.outputInterface = outputInterface;
	}

}
