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
