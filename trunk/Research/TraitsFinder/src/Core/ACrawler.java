package Core;

import Services.FileServices;
import Services.ICollector;
import Core.Interfaces.ICrawler;

public class ACrawler  extends CommonCFinder
{
	protected ICollector m_collector;
	
	public ACrawler()
	{
		super();
		m_collector = new WgetCollector();
	}
	
	
	protected boolean DownloadFile(String filePath,String UrlPath)
	{
		if (FileServices.PathExist(filePath))
		{
			FileServices.Delete(this.GetClassName(), filePath);
		}
		if (m_collector.SaveDataFile(filePath, UrlPath))
		{
			if (!FileServices.PathExist(filePath))
			{
				WriteLineToLog("ERROR: failed to create main pinterset test ..." +filePath);
				return false;
			}
		}
	 return true;	
	}
	
	protected void PrintErrorParsing(Exception e,String cralwingType)
	{
		WriteLineToLog("Error on " +cralwingType +" error : "+ e.toString());
		WriteToLog(e.toString());
		e.printStackTrace();
	}
	
}
