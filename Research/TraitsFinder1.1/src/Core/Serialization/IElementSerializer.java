package Core.Serialization;

import Elements.IElement;

public interface IElementSerializer
{
	void Save();
	void Save(IElement elemet);
	IElementSerializer Load();
	boolean Close();
}
