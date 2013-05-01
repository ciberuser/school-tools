package Elements;

import org.hamcrest.core.IsEqual;

import Services.FileServices;
import Core.CoreContext;
import Core.PinterestContext;
import Core.Serialization.ESerializerType;
import Core.Serialization.IElementSerializer;
import Core.Serialization.SerializerFactory;

public class UserElement extends StringDataElement
{
	@Override
	public void Serialize()
	{
		
		if (!IsVisited())
		{
			for(IElementSerializer ser :m_serializerList)
			{
				ser.Open(); //TODO need to be tested!!
			}
			
			for(IElement element :m_elements)
			{
				element.Serialize();
			}
		
			for (int i=0 ; i<m_elements.size() ; ++i)
			{
				for (int j=i ; j<m_elements.size() ; ++j)
				{
					if(i != j) m_elements.get(i).Link(m_elements.get(j));
				}
			}
			FileServices.CreateTextFile(GetClassName(), GetVisitFilePath());
			for(IElementSerializer ser :m_serializerList)
			{
				ser.Close(); //TODO need a test
			}
		}
		
	}
	public UserElement(String name)
	{
		super(EProperty.name.toString(), name);
		AddSerializer(SerializerFactory.GetInstance().AllocateSerializer(ESerializerType.eNeo4J, this, CoreContext.GRAPH_DB_DIR));
	}
	
	@Override
	public void Link(IElement elment) { }
	
	private String GetVisitFilePath()
	{
		return GetSubjectUserPath() + "/" + CoreContext.VISIT_NAME;
	}
	
	private boolean IsVisited()
	{
		return  FileServices.PathExist(GetVisitFilePath());
	}
	
	private String GetSubjectUserPath()
	{
		String userPath =  PinterestContext.USERS_FOLDER_POOL_PATH +"/" + this.GetName();
		return (FileServices.PathExist(userPath))? userPath : "";
	}
	
	
}
