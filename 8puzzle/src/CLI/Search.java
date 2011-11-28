package CLI;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.*;

import sun.misc.Regexp;

import SearchCore.*;


public class Search {

	
	static final String A_STAR ="astar";
	static final String DFS = "dfs";
	static final String BFS ="bfs";
	/**
	 * 
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException
	{
		/*
		try
		{
		String s = new java.io.File(".").getCanonicalPath();
			System.out.println(" the path is : " + s);
		}
		catch (Exception e) {
			System.out.println("error");
		}
		*/
		
		
		if (args.length > 0)
		{
			if (args[0] == null) 
			{
				System.out.println("no file was insert ");
				return ;
			}
			if (args[1] ==  null)
			{
				System.out.println("no algorithm was insert");
				return ;
			}
			String filePath = args[0];
			String alg = args[1];
			String content = null;
			try
			{
				
				String line = null;
				StringBuffer sb = new StringBuffer();
				BufferedReader br = null;
				File pFile = new File(".\\"+filePath);
				br = new BufferedReader(new FileReader(pFile));
				content = br.readLine();
				if (content.isEmpty()) throw new Exception("no string input");
			
				
				if (Pattern.matches(DFS, alg))
				{
					System.out.println("DFS have been selected");
					ISearch dfsSearch = new DFSSearch(content);
					dfsSearch.Search();
				}
				
				if (Pattern.matches(BFS, alg))
				{
					System.out.println("BFS have been selected");
					ISearch bfsSearch =new BFSSearch(content);
					bfsSearch.Search();
				}
				
				if (Pattern.matches(A_STAR, alg))
				{
					System.out.println("A Star have been selected:\nstarting manhattan heurisic");
					ISearch manhattanSearch =new AStarSearch(content,new ManhattanPrio());
					manhattanSearch.Search();
					System.out.println("\nstarting hamming heurisic");
					ISearch hammingSearch = new AStarSearch(content,new HammingPrio());
					hammingSearch.Search();
					System.out.println("\nstarting student heurisic");
					ISearch studentSearch = new AStarSearch(content,new UserHeuristicPrio());
					studentSearch.Search();
				}
				
							
			}
			catch (Exception e) {
				PrintErr("Error : "+e.getMessage());
			}
			
			//ISearch bfsSearch = new BFSSearch("b 8 7 4 6 5 1 3 2");
			//ISearch astar = new AStarSearch(content ,new ManhattanPrio());
			//astar.Search();
			//ISearch astar2  = new AStarSearch(content , new HammingPrio());
			//astar2.Search();
			//ISearch se = new DFSSearch("b 8 7 4 6 5 1 3 2");
			//se.Search();
			//System.out.println("new BFS search");
			//search.Search();
			//ISearch astar = new AStarSearch("b 8 7 4 6 5 1 3 2",new ManhattanPrio());
			//ISearch astar2 = new AStarSearch("1 2 3 4 5 b 6 7 8",new HammingPrio());
			//System.out.println("start a star search using ");
			//astar.Search();
			//System.out.println("start a star search hamming prio");
			///astar2.Search();
			//System.out.println("start bfs search");
			///bfsSearch.Search();
			return ;
		}
	}
	
	static void PrintErr(String errMsg)
	{
		System.err.println(errMsg);
	}
	
}
