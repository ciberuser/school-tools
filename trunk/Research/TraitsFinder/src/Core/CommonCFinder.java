package Core;

import Services.Logger.ELogLevel;
import Services.Logger.Logger;

public abstract class CommonCFinder 
{
	protected String GetClassName()
	{
		m_Name=this.getClass().getSimpleName();
		return m_Name;
	}
	
	protected  void WriteLineToLog(String msg,ELogLevel logLevel)
	{
		Logger.GetLogger().WriteLine(GetClassName(),  msg,logLevel);
	}

	protected void WriteToLog(String msg,ELogLevel logLevel)
	{
		Logger.GetLogger().Write(GetClassName(), logLevel, msg);
	}
	
	
	
	public CommonCFinder(){}
	
	protected static String m_Name = ""; 
}
