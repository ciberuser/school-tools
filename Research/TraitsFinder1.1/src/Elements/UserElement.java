package Elements;

import Services.FileServices;
import Core.CommonDef;

public class UserElement extends StringDataElement
{
	@Override
	public void Serialize()
	{
		
		if (!IsVisited())
		{
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
		}
		
	}
	public UserElement(String name)
	{
		super(EProperty.name.toString(), name);
	}
	
	@Override
	public void Link(IElement elment) { }
	
	private String GetVisitFilePath()
	{
		return GetSubjectUserPath() + "/" + CommonDef.VISIT_NAME;
	}
	
	private boolean IsVisited()
	{
		return  FileServices.PathExist(GetVisitFilePath());
	}
	
	private String GetSubjectUserPath()
	{
		String userPath =  CommonDef.USERS_FOLDER_POOL_PATH +"/" + this.GetName();
		return (FileServices.PathExist(userPath))? userPath : "";
	}
	
	
}
