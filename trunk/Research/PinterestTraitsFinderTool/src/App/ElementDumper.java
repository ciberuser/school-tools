package App;

import Core.CommonCBase;
import Core.PinterestContext;
import Core.Serialization.ESerializerType;
import Core.Serialization.SerializerFactory;
import Elements.IElement;

public class ElementDumper extends CommonCBase 
{
	private static ElementDumper m_instance ; 
	
	public static ElementDumper GetInstance()
	{
		if (m_instance == null)
		{
			m_instance  = new ElementDumper();
		}
		return m_instance;
	}
	
	private ElementDumper()
	{
		
	}
	
	private IElement m_elm2Dump;
	
	public void SetElemenet(IElement elm)
	{
		m_elm2Dump = elm;
	}
	
	public boolean DumpElemenet()
	{
		if (m_elm2Dump != null)
		{
			m_elm2Dump.AddSerializer(SerializerFactory.GetInstance().AllocateSerializer(ESerializerType.eElemnetObj,m_elm2Dump , PinterestContext.MAIN_ELM_OBJECT_FILE));
			m_elm2Dump.Serialize();
			return true;
		}
		return false;
		
	}
	
	
	
}
