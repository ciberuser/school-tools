package Parser;
import network.*;
import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NetLoader extends BasePareser
{
	static final String LOAD_PREFIX = "[Ll][oO][Aa][Dd]";
	static final String VARIABLE =  "variable[ ]+[A-Za-z]";
	static final String VAR = "variable";
	static final String PRO =  "probability";
	
	Pattern m_pattern;
	Matcher m_matcher;
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
	
	private void CreateNetwork()
	{
		if (m_netString == null)return ;
		m_pattern = Pattern.compile(VARIABLE);
		m_matcher = m_pattern.matcher(m_netString);
	    while(m_matcher.find())
	    {
	    	String s = m_matcher.group(0);
	    	s=s.replace(VAR, " ");
	    	System.out.println(s.trim());
	    }
	}
	
	
	
	public Network GetNetwork()
	{
		return new Network();
		
	}
	
}
