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
			Puzzle p = new Puzzle("1 2 3 4 5 6 7 8 b");
			p.Up();
			p.Down();
			p.Left();
			p.Right();
			return ;
		}
	}

}
