package App;

import Services.FileServices;
import Services.Log.*;
import Services.Log.ELogLevel;
import Core.CommonCBase;
import Core.CommonDef;
import Core.CrawlerProccessor;
import Core.ECrawlingType;
import Core.Crawlers.PintersetCrawler;
import Core.Interfaces.ICrawler;
import Elements.IElement;


public class AppRunner extends CommonCBase {
	
	private final static String APP_NAME = "T_R_cli"; 
	
	private static void  Init()
	{
		if (!FileServices.PathExist(CommonDef.ROOT_DATA_FOLDER))
		{
			FileServices.CreateFolder(APP_NAME, CommonDef.ROOT_DATA_FOLDER);
		}
		if (!FileServices.PathExist(CommonDef.USERS_FOLDER_POOL_PATH)) FileServices.CreateFolder(APP_NAME,CommonDef.USERS_FOLDER_POOL_PATH);
		if (!FileServices.PathExist(CommonDef.GRAPH_DB_DIR) )FileServices.CreateFolder(APP_NAME, CommonDef.GRAPH_DB_DIR);
		Logger.SetPrintLevel(ELogLevel.WARNING);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Init();
		
		IElement mainElement  = null;
		ICrawler pinterserCrawler = new PintersetCrawler();
		System.out.println("start crawling main page...");
		mainElement =  pinterserCrawler.Crawl(CrawlerProccessor.GetInstance().GetDepthCrawling(ECrawlingType.Main));
		if (mainElement !=null)
		{
			CrawlerProccessor.GetInstance().ExcuteCrawler(mainElement, 10);
		
		}
		

	}

}
