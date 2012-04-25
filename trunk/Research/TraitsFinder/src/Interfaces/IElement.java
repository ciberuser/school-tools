package Interfaces;

import java.util.List;

public interface IElement 
{
	List<IElement> GetSessions();
	void AddElement(IElement element);
	void SetProperty(String ProperyDef,Object ProperyData);
	

}
