import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Vector;



public class WordsData 
{
	public static final int NOT_EXIST = -1 ;
	List<String> m_wordList ;
	
	//articel index:  word-index  
	List<       List<Integer>    > m_word_article ;
	
   //index - article  - value class
	List<Integer> m_articleClass;

	

	
	public WordsData()
	{
		m_wordList = new Vector<String>();
		m_word_article= new Vector< List<Integer>>();
		m_articleClass = new Vector<Integer>();
	}
	
	public void AddWord(String word)
	{
		m_wordList.add(word);
		
		
	}
	
	public int GetWordIndex(String word)
	{
		return (m_wordList.contains(word)) ?  m_wordList.indexOf(word)+1 : NOT_EXIST;
	}
	
	public void  AddWordToArticle(int articleIndex,int wordIndex)
	{
		List<Integer> tempWordList ;
		try
		{
			tempWordList = m_word_article.get(articleIndex);
		}
		catch (IndexOutOfBoundsException  ex) 
		{
				tempWordList = new Vector<Integer>();
				m_word_article.add(tempWordList);
		}
		tempWordList.add(wordIndex);
		m_word_article.set(articleIndex,tempWordList);
			
	}
}
