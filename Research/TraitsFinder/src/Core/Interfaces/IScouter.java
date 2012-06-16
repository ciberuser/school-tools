package Core.Interfaces;
import Elements.Interfaces.*;
public interface IScouter 
{

	IElement Scout();
	IElement Scout(String item);
	boolean SaveItem();
	boolean CreateResultsPool(String Path);
	
	
	
	
	
}
