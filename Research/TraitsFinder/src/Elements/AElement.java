package Elements;

import java.util.List;

import Interfaces.IElement;

public abstract class AElement  {

	protected List<IElement> m_elements;
	
	
	public List<IElement> GetElements() {
		return m_elements;
	}

	public void AddElement(IElement element) {
		m_elements.add(element);
	}
	
	public IElement[] GetSessionsArr()
	{
		return m_elements.toArray(new IElement[0]);
	}

	
	public void SetProperty(String ProperyDef, Object ProperyData) {
		// TODO Auto-generated method stub
		
	}
	
	
}
