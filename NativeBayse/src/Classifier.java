import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;


public class Classifier 
{
	

	List<Integer> m_articleClasses;// = new Vector<Integer>();
	//List<Double>  m_class_1_Chances = new Vector<Double>();
	//List<Double>  m_class_2_Chances = new Vector<Double>();
	Map<Integer,Double> m_word_Chances_Class1 = new HashMap<Integer, Double>();
	Map<Integer,Double> m_word_Chances_Class2 = new HashMap<Integer, Double>();
	
	
	public Classifier(String WordList,String DataList,String LableList)
	{
		InitAll(WordList, DataList, LableList);
		m_Data.InitUnicWords();
	}
	
	public void InitAll(String WordList,String DataList,String LableList)
	{
		AddWordList(WordList);
		Words2Articles(DataList);
		ClassArticle(LableList);
	}
	
	private void AddWordList(String filename)
	{
		try 
		{
			String[] AllLines = ReadFromFile(filename);
			for(String line: AllLines)
			{
				m_Data.AddWord(line.toLowerCase().trim());
			}

		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public String GetWord(int index)
	{
		return m_Data.GetWord(index);
	}
	
	public  int GetCountOfWords()
	{
	 	return m_Data.GetNumOfWords();
	}
	
	
	private void ClassArticle(String fileName)
	{
		m_Data = ClassArticle(m_Data,fileName);
	}
	
	private WordsData ClassArticle(WordsData data,String fileName)
	{
		try {
			String[] AllLines = ReadFromFile(fileName);
			int i = 0;
			for(String line : AllLines)
			{
				data.AddArticle2Class(Integer.parseInt(line.trim()), i++);
			}
			
			return data;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return data;
		}
		
	}
	
	private void Words2Articles(String fileName)
	{
		Words2Articles(fileName, m_Data);
	}
	
	private WordsData Words2Articles(String fileName,WordsData data )
	{
		try
		{
			String[] AllLines = ReadFromFile(fileName);
			for(String line : AllLines)
			{
				String[] Items = line.split("\\s");
				data.AddWordToArticle(Integer.parseInt(Items[0]), Integer.parseInt(Items[1]));
			}
			return data;
		}
		catch (Exception e) {
			return null;
		}
	}
	
	public double GetPR(int wordIndex,int classType)
	{ 
		intWarp K = new intWarp();
		double wicj =  (double)m_Data.GetCountWordInClass(wordIndex, classType);
		double Sumwjcj = (double)m_Data.GetTotalWordSInClass(classType,K);
		
		return (((wicj+1))/(Sumwjcj+(double)K.getM_Value())); //need to fix it ...
	}
	
	private double GetPrArticle(int aIndex,int Wi,int Cj)
	{
		double WiCj= (double)m_Data.GetCountWordInClass(Wi, aIndex, Cj) +1;
		double wjcj = (double)m_Data.GetTotalWordSInClass2(Wi);
		return (WiCj/(wjcj+2));
	}
	
	private double GetProbArticleClass(int document ,int ClassType,boolean bUpdate)
	{
		double mul=1;
		Map<Integer,Double> word_chances = (ClassType==1)? m_word_Chances_Class1 : m_word_Chances_Class2; 
		for(Integer i :m_Data.GetWordInDocumnet(document))
		{
			if(i != m_Data.NOT_EXIST)
			{
				if (!word_chances.containsKey(i)&& bUpdate)
				{
					double t = GetPR(i, ClassType);
					word_chances.put(i,t);
				}
				mul*=word_chances.get(i)*5000; // size of double to small for the data 
			}
		}
		//mul*=(double)0.5;
		return mul;
	}
	
	public double Learn()
	{
		int count = 0;
		
		m_articleClasses =  ClassifeyArticles(m_Data,true);
		for (int i=0;i<m_articleClasses.size();i++)
		{
			if(m_articleClasses.get(i)== m_Data.m_articleClass.get(i)) count++; 
		}
		return  (double)count/(double)m_articleClasses.size();
		
	}
	
	public List<Integer> ClassifeyArticles(WordsData data,boolean bUpdate)
	{
		List<Integer> classifey= new Vector<Integer>();
		for(int i =0;i< data.GetNumOfArticles();i++)
		{
			double res1 =  GetProbArticleClass(i,1,bUpdate);
			double res2 = GetProbArticleClass(i,2,bUpdate);
			int classType	= (res1>res2)? 1 :2;
			classifey.add(classType);
		}
		return classifey;
	}
	
	public double  EvaluateData(String testData,String testLable )
	{
		int count =0;
		try
		{
			WordsData evData = new  WordsData();
			//evData.m_wordList = m_Data.m_wordList;
			evData = Words2Articles(testData, evData);
			String[] testlabeLines = ReadFromFile(testLable);
			evData = ClassArticle(evData, testLable);
			List<Integer> classVer = ClassifeyArticles(evData,false);
			for(int i =0 ;i<testlabeLines.length;i++)
			{
				if (classVer.get(i) == Integer.parseInt(testlabeLines[i].trim()))count++;
			}
			
			return (double)((double)count/(double)classVer.size());
			
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
		
		
	}
	
	private String[] ReadFromFile(String filename) throws IOException
	{
		List<String> lines = new Vector<String>();
		String line;
		try
		{
			m_in = new LineNumberReader(new FileReader(filename));
			while((line = m_in.readLine()) !=null)
			{
				lines.add(line);
			}
			return lines.toArray(new String[0]);
		}
		
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			return lines.toArray(new String[0]);
			
		}
		
		
	}
	
	private WordsData m_Data = new WordsData();
	private LineNumberReader m_in;
}
