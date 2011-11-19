package CLI;

import SearchCore.*;


public class Search {

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// for debug ///
		
		
		//
		if (args.length > 0)
		{
			if (args[0].isEmpty()) 
			{
				System.out.println("no file was insert ");
				return ;
			}
			if (args[1] == null)
			{
				System.out.println("no algorithm was insert");
				return ;
			}
			//ISearch search = new BFSSearch("1 2 3 4 5 6 7 8 b");
			
			ISearch se = new DFSSearch("b 8 7 4 6 5 1 3 2");
			se.Search();
			return ;
		}
	}

}
