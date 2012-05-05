package Interfaces;

import java.util.List;

public interface IElement 
{
	List<IElement> GetElements();
	void AddElement(IElement element);
	void SetProperty(String ProperyDef,Object ProperyData);
	

}
