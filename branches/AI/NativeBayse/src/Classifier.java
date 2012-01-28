import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.List;
import java.util.Vector;


public class Classifier 
{
	public void AddWordList(String filename)
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
	
	public void ClassArticle(String fileName)
	{
		try {
			String[] AllLines = ReadFromFile(fileName);
			int i = 0;
			for(String line : AllLines)
			{
				m_Data.AddArticle2Class(Integer.parseInt(line.trim()), i++);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public double GetPR(int wordIndex,int classType)
	{ 
		intWarp K = new intWarp();
		int wicj =  m_Data.GetCountWordInClass(wordIndex, classType);
		int wjcj = m_Data.GetTotalWordSInClass(classType,K);
		return ((double)(wicj+1)/((double)wjcj+(double)K.getM_Value())); //need to fix it ...
	}
	
	public void Words2Articles(String fileName)
	{
		try
		{
			String[] AllLines = ReadFromFile(fileName);
			for(String line : AllLines)
			{
				String[] Items = line.split("\\s");
				m_Data.AddWordToArticle(Integer.parseInt(Items[0]), Integer.parseInt(Items[1]));
			}
		}
		catch (Exception e) {
			// TODO: handle exception
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
