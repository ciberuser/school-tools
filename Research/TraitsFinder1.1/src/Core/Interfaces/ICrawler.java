package Core.Interfaces;
import Elements.*;
public interface ICrawler 
{

	IElement Crawl();
	boolean SaveItem();
	boolean CreateResultsPool(String Path);
	
	
}
