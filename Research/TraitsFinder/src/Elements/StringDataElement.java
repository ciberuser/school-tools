
package Elements;



import Interfaces.*;

public class StringDataElement extends AElement implements IElement {

	
	public StringDataElement()
	{
		super();
	}
	
	public StringDataElement(String ProperyDef, String ProperyData)
	{
		super();
		m_propertyDef = ProperyDef;
		m_propertyData = ProperyData;
	}
	
	public String GetData()
	{
		return m_propertyData;
	}
		
	public String GetDefinition()
	{
		return m_propertyDef;
	}
	
		
	@Override
	public void SetProperty(String ProperyDef, Object ProperyData)
	{
		super.SetProperty(ProperyDef, ProperyData);
		m_propertyData = (String)ProperyData;
		m_propertyDef = ProperyDef;
	}

	public void SetProperty(String ProperyDef, String ProperyData)
	{
		super.SetProperty(ProperyDef, ProperyData);
		SetProperty(ProperyDef,(String)ProperyData);
	}
	
	
	private String m_propertyData;
	private String m_propertyDef;
	
}
