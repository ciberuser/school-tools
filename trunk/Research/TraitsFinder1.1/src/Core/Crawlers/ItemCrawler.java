package Core.Crawlers;

import Core.Interfaces.ICrawler;
import Elements.IElement;
import Services.Log.ELogLevel;

public class ItemCrawler extends ACrawler implements ICrawler {

	private String m_itemURL;
	private String m_ItemPath;
	private String m_itemName;
	
	public ItemCrawler(String ItemUrl, String ItemPath,String ItemName)
	{
		m_itemURL = ItemUrl;
		m_ItemPath =ItemPath;
		m_itemName = ItemName;
	}

	@Override
	public IElement Crawl() 
	{
		if (m_itemURL.length()>0 && m_ItemPath.length()>0) 
		{
			if (!DownloadFile(m_ItemPath, m_itemURL))
			{
				WriteLineToLog("failed to download Item itemPath:" +m_ItemPath +" item URL:" +m_itemURL, ELogLevel.ERROR);
				return null;
			}
				
		}
		return null;
	}

	@Override
	public boolean SaveItem() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean CreateResultsPool(String Path) {
		// TODO Auto-generated method stub
		return false;
	}

}
