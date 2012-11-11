package Elements;

public class UserElement extends StringDataElement
{
	@Override
	public void Serialize()
	{
		for(IElement element :m_elements)
		{
			element.Serialize();
		}
	
		for (int i=0;i<m_elements.size();++i)
		{
			for (int j=i;j<m_elements.size();++j)
			{
				if(i!=j) m_elements.get(i).Link(m_elements.get(j));
			}
		}
		
	}
	public UserElement(String name)
	{
		super(EProperty.name.toString(), name);
	}
	
	@Override
	public void Link(IElement elment) 
	{
		
		
		
	}

	
	
}
