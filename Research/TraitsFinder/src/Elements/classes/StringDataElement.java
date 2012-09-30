
package Elements.classes;

import Elements.Interfaces.*;


import java.util.HashMap;
import java.util.Map;



public class StringDataElement extends AElement implements IElement {

	
	public StringDataElement()
	{
		super();
		m_properties = new HashMap<String, String>();
	}
	
	public StringDataElement(String ProperyDef, String ProperyData)
	{
		super();
		m_properties = new HashMap<String, String>();
		m_properties.put(ProperyDef, ProperyData);
	}
			
	@Override
	public void AddProperty(String ProperyDef, Object ProperyData)
	{
		super.SetProperty(ProperyDef, ProperyData);
		m_properties.put(ProperyDef,(String) ProperyData);
	}

	@Override
	public void SetSerializer(IElemetSerializer serializer)
	{
		m_serializer = serializer;
	}
	
	public void SetProperty(String ProperyDef, String ProperyData)
	{
		super.SetProperty(ProperyDef, ProperyData);
		SetProperty(ProperyDef,(String)ProperyData);
	}
	
	private Map<String,String> m_properties;
	private IElemetSerializer m_serializer;
	@Override
	public Object GetProperty(String ProperyDef) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
}
