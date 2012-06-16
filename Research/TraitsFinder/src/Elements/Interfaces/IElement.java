package Elements.Interfaces;

import java.util.List;

public interface IElement 
{
	List<IElement> GetElements();
	void AddElement(IElement element);
	void AddProperty(String ProperyDef,Object ProperyData);
	void SetSerializer(IElemetSerializer serializer);
	
}
