
package Elements.classes;

import Elements.Interfaces.*;
import Services.GenericDictionary;
import Services.Logger.ELogLevel;


import java.io.ObjectInputStream.GetField;
import java.util.HashMap;
import java.util.Map;



public class StringDataElement extends AElement implements IElement {

	private GenericDictionary<String> m_properties;
	private IElemetSerializer m_serializer;
	
	public StringDataElement()
	{
		super();
		m_properties =new GenericDictionary<String>();
	}
	
	public StringDataElement(String ProperyDef, String ProperyData)
	{
		super();
		m_properties = new GenericDictionary<String>();
		m_properties.AddItem(ProperyData, ProperyData);
	}
			
	@Override
	public void AddProperty(String ProperyDef, Object ProperyData)
	{
		super.SetProperty(ProperyDef, ProperyData);
		m_properties.AddItem(ProperyDef,  ProperyData.toString());
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
	
	
	@Override
	public Object GetProperty(String ProperyDef) {
		
		if (!m_properties.IsExist(ProperyDef))
		{
			WriteLineToLog("the property " + ProperyDef +"is not exist ",ELogLevel.WARNING);
			return null;
		}
		return m_properties.GetItem(ProperyDef);		
	}
	
	
	
	
}
