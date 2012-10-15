package Core.Serialization;

import Elements.IElement;

public interface IElementSerializer
{
	void Save();
	void Link(IElement elemet);
	IElementSerializer Load();
	boolean Close();
}
