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
		//TODO add the link between them....
		
	}
	
}
