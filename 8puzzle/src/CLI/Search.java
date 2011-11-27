package CLI;

import SearchCore.*;


public class Search {

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		
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
			
			
			ISearch bfsSearch = new BFSSearch("b 8 7 4 6 5 1 3 2");
			
			//ISearch se = new DFSSearch("b 8 7 4 6 5 1 3 2");
			//se.Search();
			//System.out.println("new BFS search");
			//search.Search();
			ISearch astar = new AStarSearch("b 8 7 4 6 5 1 3 2",new ManhattanPrio());
			ISearch astar2 = new AStarSearch("1 2 3 4 5 b 6 7 8",new HammingPrio());
			System.out.println("start a star search using ");
			astar.Search();
			System.out.println("start a star search hamming prio");
			astar2.Search();
			//System.out.println("start bfs search");
			///bfsSearch.Search();
			return ;
		}
	}

}
