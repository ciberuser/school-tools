package SearchCore;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Vector;



public class AStarSearch extends ASearch {

	Vector<Node> m_open ;
	Vector<Node> m_close;
	Map<Node,Node> m_came_from ;
	Iheuristic m_heuristicAlgorithm; 
	Puzzle startPuzzle;
	Vector<String> m_history ;

	

	public AStarSearch(String puzzle, Iheuristic heurisitc )
	{
		startPuzzle = new Puzzle(puzzle);
		m_open = new Vector<Node>();
		m_close = new Vector<Node>();
		m_history = new Vector<String>();
		m_came_from = new HashMap<Node, Node>();
		m_heuristicAlgorithm = heurisitc;
		m_steps = 0;
	}
	
	@Override
	public void Search() 
	{
		super.Search();
		Node start = new Node(startPuzzle);
		start.g =0;
		start.h = m_heuristicAlgorithm.GetScore(start);
		start.f = start.g + start.h;
		
		m_open.add(start);
				
		while (!m_open.isEmpty())
		{
			m_steps++;
			if (m_steps > Def.MAX_ATTEMPT)
			{
				System.out.println("No solution (max attempt is 100,000)");
				return ;
			}
			Node x = FindTheLowF_score();
			if (IsGoal(x.getPuzzle().getPuzzle())) 
			{
												
				PrintTotalTime();
				System.out.println("Path details :");
				PrintPath(x);
				return ;
				// need to add depth
			}
			
			m_open.remove(x);
			m_close.add(x);
			LinkedList<Node> neighbors = neighbor_nodes(x);
			if (!neighbors.isEmpty()) //need to check if empty
			{
				
				for (Node y : neighbors)
				{
					if (m_close.contains(y))
						continue;
					
					int new_g = y.g + x.g + 1; // check it distance
					boolean better_g;
					if (!m_open.contains(y))
					{
						m_open.add(y);
						better_g = true;
					}
					else
					{
						if (new_g < y.g)
						{
						  better_g = true;	
						}
						else
						{
						  better_g = false;	
						}
					}
					
					if (better_g== true)
					{
						//PrintPhase(y.getPuzzle());
						m_came_from.put(y,x);
						y.g = new_g;
						y.h = m_heuristicAlgorithm.GetScore(y);
						y.f = y.h +y.g;
					}
						
				}
			}
			
			
		}
						
	}
 
	LinkedList<Node> neighbor_nodes(Node node)
	{
		LinkedList<Node> neighbors = new LinkedList<Node>();
		
		if (!m_history.contains(node.getPuzzle().GetPuzzelString()))
		{
			//if (node.parent == null) return null;
			m_history.add(node.getPuzzle().GetPuzzelString());
			
			Puzzle p = node.getPuzzle();
			Node tempNode =  ExpendNode(p.Up());
			if (tempNode != null) neighbors.add(tempNode);
		    tempNode = ExpendNode(p.Down());
		    if (tempNode != null) neighbors.add(tempNode);
		    tempNode = ExpendNode(p.Left());
		    if (tempNode != null) neighbors.add(tempNode);
		    tempNode = ExpendNode(p.Right());
		    if (tempNode != null) neighbors.add(tempNode);
		}
	    return neighbors;
	}
	
	Node ExpendNode(Puzzle p)
	{
		if (p != null)
			return new Node(p);
		else return null;
			
	}
	
	
	
	
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

	
	private void PrintPath(Node node)
	{
		if (!m_came_from.containsKey(node))
		{
			PrintPhase(node.getPuzzle());
			return;
		}
		else
		PrintPath(m_came_from.get(node));
		PrintPhase(node.getPuzzle());
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
