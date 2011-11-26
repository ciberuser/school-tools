package SearchCore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class AStartSearch extends ASearch {

	Vector<Node> m_open ;
	Vector<Node> m_close;
	Map<Node,Node> m_came_from ;
	//Map<Node,Integer> m_gScore ;
	//Map<Node,Integer> m_fScore ;
	//Map<Node,Integer> m_hScore;
	Iheuristic m_heuristicAlgorithm; 
	Puzzle startPuzzle;
	

	public AStartSearch(String puzzle)
	{
		startPuzzle = new Puzzle(puzzle);
		m_open =new Vector<Node>();
		m_close = new Vector<Node>();
		//m_gScore = new HashMap<Node, Integer>();
		//m_fScore =  new HashMap<Node, Integer>();
		//m_hScore = new HashMap<Node, Integer>();
		m_came_from = new HashMap<Node, Node>();
		
		
		m_heuristicAlgorithm = new ManhattanPrio();
		//m_hScore = new HashMap<Node, Integer>();
	}
	
	@Override
	public void Search() 
	{
		Node start = new Node(startPuzzle);
		start.g =0;
		start.h = m_heuristicAlgorithm.GetScore(start);
		start.f = start.g + start.h;
		start.parent = null; // no parent at the first one;
		m_open.add(start);
				
		while (!m_open.isEmpty())
		{
			Node x = FindTheLowF_score();
			if (IsGoal(x.getPuzzle().getPuzzle())) 
			{
				System.out.println("success!!");
				// need to add depth
			}
			
			m_open.remove(x);
			m_close.add(x);
			
			
		}
		
		
		
	}
 /*	
	Node[] neighbor_nodes(Node node)
	{
		Node parent;
		if (node.parent == null) return null;
		parent = node.parent;
		
	}
	*/
	
	Node FindTheLowF_score()
	{
				
			int minFScore = m_open.get(0).f;
			Node minNode = m_open.get(0) ;
			for(Node n:m_open)
			{
				if (n.f <= minFScore)
				{
					minNode = n;
					minFScore = n.f;
				}
			}
		return minNode;
		
	}
	
    List<Node> NeighborNodes()
    {
    	return null;
    }
	
	
	class Node
	{
		Puzzle m_puzzle;
		public int f;
		public int h;
		public int g;
		
		public Node parent;	
				
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
