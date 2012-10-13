package Core.Serialization;

import Core.CommonCFinder;
import Elements.Interfaces.IElement;

public abstract class  ASerializer extends CommonCFinder 
{
	
	protected IElement m_element;
	
	public ASerializer(IElement element)
	{
		m_element = element;
	}
}
