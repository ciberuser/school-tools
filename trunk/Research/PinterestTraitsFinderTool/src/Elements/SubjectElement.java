package Elements;

import Core.CoreContext;
import Core.Serialization.ESerializerType;
import Core.Serialization.IElementSerializer;
import Core.Serialization.SerializerFactory;
import Elements.IElement;
import Services.Log.ELogLevel;

public class SubjectElement extends  EnumElement implements IElement
{
	
	public SubjectElement(String subjectName)
	{
		super(subjectName);
		if( CoreContext.SET_GRAPH)
		{
			WriteLineToLog("attach serializer", ELogLevel.INFORMATION);
			IElementSerializer graphSerialzer =  SerializerFactory.GetInstance().AllocateSerializer(ESerializerType.eNeo4J, this, CoreContext.GRAPH_DB_DIR);
			if (graphSerialzer != null)
			{
				m_serializerList.add(graphSerialzer);
			}
		}
		AddProperty(EProperty.name.toString(), subjectName);
	}
	
	@Override
	public Object GetProperty(String ProperyDef) {
		
		if ( EProperty.valueOf(ProperyDef)==null)
		{
			WriteLineToLog("Error no "+ProperyDef+ "is exist ",ELogLevel.WARNING);
			return null;
		}
		return super.GetProperty(ProperyDef);
	}

	@Override
	public void Serialize() 
	{
		ActivateSerialiers(false);	
	}

	@Override
	public void Link(IElement elment)
	{
		ActivateLinker(elment);
	}

	
}
