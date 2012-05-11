package Interfaces;

public interface IFinder 
{

	IElement Find();
	IElement Find(String item);
	boolean SaveItem();
	boolean CreateResultsPool(String Path);
	
	
	
	
	
}
