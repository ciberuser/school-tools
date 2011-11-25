package SearchCore;

import java.util.List;
import java.util.Map;
import java.util.Vector;

public class AStartSearch extends ASearch {

	Vector<Node> m_open ;
	Vector<Node> m_close;
	Map<Node,Node> came_from ;
	Iheuristic m_heuristicAlgorithm; 
	Puzzle startPuzzle;
	

	public AStartSearch(String puzzle)
	{
		startPuzzle = new Puzzle(puzzle);
		
	}
	
	@Override
	public void Search() {
		// TODO Auto-generated method stub
		 
		
	}
	
    List<Node> NeighborNodes()
    {
    	return null;
    }
	
	
	class Node
	{
		Puzzle m_puzzle;
		
		
		public Node()
		{
			
		}
		
		Puzzle getPuzzle()
		{
			return m_puzzle;
		}
				
		public String  GetString()
		{
			return  m_puzzle.GetPuzzelString();
		}
		
		public Node(Puzzle p)
		{
			m_puzzle = p;
		}
		
	}

}
