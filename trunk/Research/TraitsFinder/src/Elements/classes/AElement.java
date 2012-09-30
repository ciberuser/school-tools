package Elements.classes;

import java.util.LinkedList;
import java.util.List;

import Core.CommonCFinder;
import Elements.Interfaces.IElement;

public abstract class AElement  extends CommonCFinder{

	protected List<IElement> m_elements;
	protected String m_name;
	
	public AElement()
	{
		m_elements = new LinkedList<IElement>();
	}
	
	public AElement(String elementName )
	{
		m_elements = new LinkedList<IElement>();
		m_name = elementName;
	}
	
	public void SetName(String elementName) {	m_name = elementName;}
	
	public String GetName() { 	return m_name; }
	
	public List<IElement> GetElements() { return m_elements; }

	public void AddElement(IElement element) { 	m_elements.add(element); }
	 
	public IElement[] GetSessionsArr() 	{ 	return m_elements.toArray(new IElement[0]); }

	
	public void SetProperty(String ProperyDef, Object ProperyData) {
		// TODO Auto-generated method stub
		
	}
	
	
}
