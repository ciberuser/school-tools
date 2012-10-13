package Elements;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import Core.Serialization.IElementSerializer;
import Elements.IElement;
import Services.Log.ELogLevel;


public class EnumElement  extends AElement 
{
	
	IElementSerializer m_serializer;
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


	public void SetSerializer(IElementSerializer serializer) 
	{
		m_serializer = serializer;
			
	}
	
	private EProperty findProperty(String ProperyDef)
	{
		for (EProperty prp:EProperty.values())
		{
			if (prp.toString() == ProperyDef) return prp;
		}
		return null;
	}


	public Object GetProperty(String ProperyDef) {
		
		EProperty prop = findProperty(ProperyDef);
		if (prop!= null)	
		return m_properties.get(prop);
		
		return null;
				
		
	}

	
	public Map GetProperties() {
		
		return m_properties;
	}

	

}
