package Core.Crawlers;

import Core.Interfaces.ICrawler;
import Elements.IElement;
import Elements.ItemElement;
import Services.Log.ELogLevel;

public class ItemCrawler extends ACrawler implements ICrawler {

	private String m_itemURL;
	private String m_ItemPath;
	private String m_itemName;
		
	public ItemCrawler(String ItemName, String ItemPath, String ItemUrl )
	{
		m_itemURL = ItemUrl;
		m_ItemPath =ItemPath;
		m_itemName = ItemName;
	}

	@Override
	public IElement Crawl(boolean recursive) 
	{
		IElement itemElement = null;
		if (m_itemURL.length()>0 && m_ItemPath.length()>0) 
		{
			try
			{
				if (!DownloadFile(m_ItemPath, m_itemURL))
				{
					WriteLineToLog("failed to download Item itemPath:" +m_ItemPath +" item URL:" +m_itemURL, ELogLevel.ERROR);
					return null;
				}
			} catch (InterruptedException e)
			{
				WriteLineToLog("problem with when download file ", ELogLevel.ERROR);
				return null;
			}
			itemElement = new ItemElement(m_itemName);	
		}
		return itemElement;
	}

	@Override
	public boolean SaveItem() {
		// TODO Auto-generated method stub
		return false;
	}


}
