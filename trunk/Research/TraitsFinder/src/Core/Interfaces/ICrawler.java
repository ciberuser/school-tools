package Core.Interfaces;
import Elements.Interfaces.*;
public interface ICrawler 
{

	IElement Crawl();
	boolean SaveItem();
	boolean CreateResultsPool(String Path);
	
	
}
