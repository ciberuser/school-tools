package Parser;
import network.*;
import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NetLoader extends BasePareser
{
	static final String LOAD_PREFIX = "[Ll][oO][Aa][Dd]";
	static final String VARIABLE =  "variable[ ]+[A-Za-z]+";
	static final String VAR = "variable";
	static final String PRO =  "probability[ ]+[A-Za-z]+[ ]|[ ][A-Za-z ]+[\r\n][ ]+";
	static final String PRIO = "[ft][ ][0-9.]{3,5}";
	
	Pattern m_patternVar;
	Pattern m_patternPrio;
	
	Matcher m_matcherVar;
	Matcher m_matcherPrio;
	Scanner m_FileScanner;
	String m_netString ;
	Network m_network; 
	
	public NetLoader(String str) throws Exception
	{
		String[] AllCommand = str.split(" ");
		m_netString = ReadFile(AllCommand[1]);
	    if (m_netString == null) throw new Exception("file empty or no file find");
	    CreateNetwork();
	}
	
	private void AddNode(String line)
	{
		m_patternVar = Pattern.compile(VARIABLE);
		m_matcherVar = m_patternVar.matcher(line);
		while(m_matcherVar.find())
	    {
	    	String s = m_matcherVar.group(0);
	    	s=s.replace(VAR, " ");
	    	System.out.println(s.trim());
	    }
		
	}
	
	private void AddProbability(String line )
	{
		m_patternPrio = Pattern.compile(PRO);
		m_matcherPrio = m_patternPrio.matcher(line);
		while (m_matcherPrio.find())
		{
			String s = m_matcherPrio.group(0);
			s.replace("probability", "");
			String[] sArr = s.split("|");
			
		}
	}
	
	private void CreateNetwork()
	{
		if (m_netString == null)return ;
		
		
		while(m_matcherVar.find())
	    {
	    	String s = m_matcherVar.group(0);
	    	s=s.replace(VAR, " ");
	    	System.out.println(s.trim());
	    }
				
		
	}
	
	void AddPrio(String nodeName, String[] parents )
	{
		String str;
        
		BufferedReader reader = new BufferedReader(
		  new StringReader(nodeName));
		        
		try 
		{
		  while ((str = reader.readLine()) != null) 
		  {
		        if (str.length() > 0) 
		        {
		        		AddNode(str);
	        	}
		  }
		} 
		catch(IOException e)
		{
		  e.printStackTrace();
		}
	}
	
	
	public Network GetNetwork()
	{
		return new Network();
		
	}
	
}
