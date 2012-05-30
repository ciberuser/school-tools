package Services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Interfaces.IDictionary;

public class GenericDictionary<T>  implements IDictionary<T>
{

	Map<String,T> m_item ;
	
	public  GenericDictionary()
	{
		m_item = new HashMap<String, T>();
	}
	
	
	@Override
	public void AddItem(String id, T item)
	{

		m_item.put(id, item);
		
	}

	@Override
	public T GetItem(int index) {
		return (T) ((GenericDictionary) m_item.values()).GetItem(index);
	}

	@Override
	public T GetItem(String Id) {
		return m_item.get(Id);
	}


	@Override
	public List<T> GetAllItem() {
		return (List<T>)m_item.values();
	}


	@Override
	public int Size() {
		return GetAllItem().size();
		
	}

	
}
