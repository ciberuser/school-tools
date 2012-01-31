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
	
	public int GetNumOfWords()
	{
		return m_wordList.size();
	}
	
	public void AddArticle2Class(int classType,int articleIndex)
	{
		m_articleClass.add(articleIndex, classType);
	}
	
	private List<Integer> GetWordInDocumnet(int articleIndex)
	{
		return m_word_article.get(articleIndex);
	}
	
	private List<Integer> GetAllArticlesInClass(int classType)
	{
		List<Integer> articlesList = new Vector<Integer>();
		int i =1;
		for(Integer aClass :m_articleClass)
		{
			if (aClass == classType)
			articlesList.add(i++);	
		}
		return articlesList;
	}
	
	
	
	public int GetCountWordInClass(int wordIndex,int classType)
	{
		int count = 0;
		for (Integer aIndex: GetAllArticlesInClass(classType))
		{
			if (GetWordInDocumnet(aIndex).contains((Integer)wordIndex)) count++;
		}
		return count;
	}
		
	public int GetTotalWordSInClass(int classType ,  intWarp K )
	{
		int sum = 0;
		int k = 0;
		for(Integer aIndex : GetAllArticlesInClass(classType))
		{
			k++;
			sum += GetWordInDocumnet(aIndex).size();
		}
		K.setM_Value(k);
		return sum;
	} 
	
	public int GetWordIndex(String word)
	{
		return (m_wordList.contains(word)) ?  m_wordList.indexOf(word)+1 : NOT_EXIST;
	}
	
	public void  AddWordToArticle(int articleIndex,int wordIndex)
	{
		int aIndex = articleIndex-1;
		int wIndex = wordIndex-1;
		List<Integer> tempWordList ;
		try
		{
			tempWordList = m_word_article.get(aIndex);
		}
		catch (IndexOutOfBoundsException  ex) 
		{
				tempWordList = new Vector<Integer>();
				m_word_article.add(tempWordList);
		}
		tempWordList.add(wIndex);
		m_word_article.set(aIndex,tempWordList);
			
	}
}
