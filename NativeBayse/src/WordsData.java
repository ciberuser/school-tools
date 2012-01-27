import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Vector;



public class WordsData 
{
	public static final int NOT_EXIST = -1 ;
	List<String> m_wordList ;
	Map<Integer,String> m_article ;
	
	
	public WordsData()
	{
		m_wordList = new Vector<String>();
		m_article= new HashMap<Integer, String>();
	}
	
	public void AddWord(String word)
	{
		m_wordList.add(word);
	}
	
	public int GetWordIndex(String word)
	{
		return (m_wordList.contains(word)) ?  m_wordList.indexOf(word) : NOT_EXIST;
	}
}
