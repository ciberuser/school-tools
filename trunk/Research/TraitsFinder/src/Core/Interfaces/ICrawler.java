package Core.Interfaces;
import Elements.Interfaces.*;
public interface ICrawler 
{

	IElement Crawl();
	IElement Crawl(String item);
	boolean SaveItem();
	boolean CreateResultsPool(String Path);
	
	
	
	
	
}
