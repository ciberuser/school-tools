package Core;

import Services.Logger;

public abstract class CommonCFinder 
{
	
	
	protected String GetClassName()
	{
		m_Name=this.getClass().getName();
		return m_Name;
	}
	
	protected  void WriteLineToLog(String msg)
	{
		Logger.GetLogger().WriteLine(GetClassName(),msg);
	}

	
	
	
	public CommonCFinder(){}
	
	protected static String m_Name = ""; 
}
