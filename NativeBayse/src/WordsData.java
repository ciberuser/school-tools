import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.sun.org.apache.bcel.internal.generic.CPInstruction;



public class WordsData 
{
	public static final int NOT_EXIST = -1 ;
	List<String> m_wordList ;
	Map<Integer,Integer> WordCont1;
	Map<Integer,Integer> WordCont2;
	//articel index:  word-index  
	public  List<       List<Integer>    > m_word_article ;
	
   //index - article  - value class
	public List<Integer> m_articleClass;

	List<Integer> words_class1;
	List<Integer> words_Class2;
	

	
	public WordsData()
	{
		m_wordList = new Vector<String>();
		m_word_article= new Vector< List<Integer>>();
		WordCont1 = new HashMap<Integer, Integer>();
		WordCont2 = new HashMap<Integer, Integer>();
		m_articleClass = new Vector<Integer>();
		words_class1 = new Vector<Integer>();
		words_Class2 =new Vector<Integer>();
	}
	
	public void InitUnicWords()
	{
		
		for (int i=1;i<3;i++)
		{
			for (Integer articleIndex : GetAllArticlesInClass(i))
			{
				for(Integer wordIndex :GetWordInDocumnet(articleIndex))
				{
					List<Integer> word_class = (i==1)? words_class1: words_Class2;
					Map<Integer, Integer> wordCont =(i==1)? WordCont1 : WordCont2;
				    
					if (!word_class.contains(wordIndex)) word_class.add(wordIndex);
					
					if (wordCont.containsKey(wordIndex) )
					{
						Integer newVal = wordCont.get(wordIndex)+1;
						wordCont.put(wordIndex,newVal);
					}
					else
					{
						wordCont.put(wordIndex, 1);
					}
				    
				}
			}
		}
	}
	
	public void AddWord(String word)
	{
		m_wordList.add(word);
	}
	
	public int GetNumOfWords()
	{
		return m_wordList.size();
	}
	
	public int GetNumOfArticles()
	{
		return m_articleClass.size();
	}
	
	public void AddArticle2Class(int classType,int articleIndex)
	{
		m_articleClass.add(articleIndex, classType);
	}
	
	public List<Integer> GetWordInDocumnet(int articleIndex)
	{
		try
		{
			return m_word_article.get(articleIndex);
		}
		catch (Exception e) {
			List<Integer> te = new Vector<Integer>();
			te.add(NOT_EXIST);
			return te;
		}
		
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
	
	
	public int GetCountWordInClass(int wordIndex,int aIndex,int classType)
	{
		int count = 0;
		for(Integer Wi :GetWordInDocumnet(aIndex))
		{
			if (Wi == (wordIndex-1)) count++;
		}
		return count;
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
		
	
	public int GetTotalWordSInClass2(int wordIndex)
	{
		return GetCountWordInClass(wordIndex,1)+GetCountWordInClass(wordIndex,2);
		
	}
	
	public int GetTotalWordSInClass( int classType ,  intWarp K )
	{
		int sum = 0;
		int k ;
		List<Integer> ListWords;
		Map<Integer,Integer> WordCont;
		if (classType==1)
		{
			k = words_class1.size();
			ListWords = words_class1;
			WordCont = WordCont1;
		}
		else
		{
			ListWords =words_Class2 ;
			k = words_Class2.size();
			WordCont = WordCont2;
		}
				
		for(Integer word : ListWords)
		{
			sum += WordCont.get(word);
		}
				
		K.setM_Value(k);
		return sum;
	} 
	
	public int GetWordIndex(String word)
	{
		return (m_wordList.contains(word)) ?  m_wordList.indexOf(word)+1 : NOT_EXIST;
	}
	
	public String GetWord(int index)
	{
		return m_wordList.get(index);
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
