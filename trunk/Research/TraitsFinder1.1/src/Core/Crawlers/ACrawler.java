package Core.Crawlers;

import Core.CommonCBase;
import Services.FileServices;
import Services.ICollector;
import Services.WgetCollector;
import Services.Log.ELogLevel;


public class ACrawler  extends CommonCBase
{
	protected ICollector m_collector;
	
	public ACrawler()
	{
		super();
		m_collector = new WgetCollector();
	}
			
	protected boolean ForceDownLoadFile(String filePath,String UrlPath)
	{
		boolean fileExist = FileServices.PathExist(filePath);
		if (fileExist)
		{
			FileServices.DeleteFile(this.GetClassName(), filePath);
			
		}
		return  m_collector.SaveDataFile(filePath, UrlPath);
	}
	
	protected boolean DownloadFile(String filePath,String UrlPath)
	{
		
		boolean fileExist = FileServices.PathExist(filePath);
		if (fileExist && FileServices.NumberDaysFileNotModified(filePath)<30)
		{
			return true;
		}
		if (fileExist)
		{
			FileServices.DeleteFile(this.GetClassName(), filePath);
			
		}
		return  m_collector.SaveDataFile(filePath, UrlPath);
	}
	
	protected void PrintErrorParsing(Exception e,String cralwingType)
	{
		WriteLineToLog("Error on " +cralwingType +" error : "+ e.toString(),ELogLevel.ERROR);
		WriteToLog(e.toString(),ELogLevel.ERROR);
		e.printStackTrace();
	}
	
}
