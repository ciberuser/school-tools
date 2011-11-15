package CLI;

import SearchCore.*;


public class Search {

	/**
	 * @param args
	 */
	public static int main(String[] args)
	{
		// for debug ///
		
		
		//
		if (args[0] == null) 
		{
			System.out.println("no file was insert ");
			return 1;
		}
		if (args[1] == null)
		{
			System.out.println("no algorithm was insert");
			return 2;
		}
		ISearch search = new DFSSearch();
			
		return 0;
		
	}

}
