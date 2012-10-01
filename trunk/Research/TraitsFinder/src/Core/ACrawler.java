package Core;

import Services.FileServices;
import Services.ICollector;
import Services.Logger.ELogLevel;


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
			FileServices.DeleteFile(this.GetClassName(), filePath);
		}
		if (m_collector.SaveDataFile(filePath, UrlPath))
		{
			if (!FileServices.PathExist(filePath))
			{
				WriteLineToLog("failed to create main pinterset test ..." +filePath,ELogLevel.ERROR);
				return false;
			}
		}
	 return true;	
	}
	
	protected void PrintErrorParsing(Exception e,String cralwingType)
	{
		WriteLineToLog("Error on " +cralwingType +" error : "+ e.toString(),ELogLevel.ERROR);
		WriteToLog(e.toString(),ELogLevel.ERROR);
		e.printStackTrace();
	}
	
}
