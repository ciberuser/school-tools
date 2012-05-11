package Services;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Logger
{
	final static String LOG_NAME ="TraitsFinder.log";
	
	public static Logger GetLogger()
	{
		if (m_logger==null)
		{
			if (new File(LOG_NAME).exists())
			{
				File file = new File(LOG_NAME);
				file.delete();
			}
			m_logger = new Logger(LOG_NAME);
		}
		return m_logger;
				
	}
	
	
	
	public static void Write(String module, String msg)
	{
		WriteStringToFile(String.format("-[%s]-:[%s]", module,msg),m_filePath);
	}
	
	public static void WriteLine(String module, String msg)
	{
		WriteStringToFile(String.format("-[%s]-:[%s]\n", module,msg),m_filePath);
	}
	
	private Logger(String filePath)
	{
		m_filePath = filePath;
	}
	
	
	static boolean WriteStringToFile(String str,String filePath )
	{
		BufferedWriter out = null;
		try
		{
			  fstream = new FileWriter(filePath,true);
			  out = new BufferedWriter(fstream);
			  out.write(str);
			  out.close();
		
		}
		catch (Exception e)
		{
			if (out != null)
			{
				try { out.close();} catch (IOException e1){}
				return false;
			}
		}
		return true;
			
	}

	private static FileWriter fstream =null;
	private static Logger m_logger = null;
	private static String m_filePath;
}
