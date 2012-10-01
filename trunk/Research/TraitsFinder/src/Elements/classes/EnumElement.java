package Elements.classes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Elements.Interfaces.IElement;
import Elements.Interfaces.IElemetSerializer;
import Services.Logger.ELogLevel;


public class EnumElement  extends AElement implements IElement
{
	
	IElemetSerializer m_serializer;
	Map<EProperty,Object> m_properties ;
	
	
	public EnumElement(String elementName)
	{
		super(elementName);
		m_properties = new HashMap<EProperty, Object>();
	}
	
	public EnumElement()
	{
		super();
		m_properties = new HashMap<EProperty, Object>();
		
	}
	
	@Override
	public void AddProperty(String  ProperyDef, Object ProperyData) 
	{
	
		for(EProperty property : EProperty.values())
		{
			if(property.toString() == ProperyDef.toLowerCase().trim())
			{
				m_properties.put(property, ProperyData);
				WriteLineToLog("new property have been added, "+property.toString()+":"+ProperyData ,ELogLevel.INFORMATION);
				return ;
			}
		}
		WriteLineToLog("property " + ProperyDef +" not found - value not added...",ELogLevel.WARNING);
	}

	@Override
	public void SetSerializer(IElemetSerializer serializer) 
	{
		m_serializer = serializer;
			
	}
	

	@Override
	public Object GetProperty(String ProperyDef) {
		
			
		if (!m_properties.containsKey((Object)ProperyDef))
		{	
			WriteLineToLog("the Property "+ ProperyDef +"is not found...",ELogLevel.WARNING);
			return null;
		}
		return m_properties.get(ProperyDef);
			
				
		
	}

	

}
