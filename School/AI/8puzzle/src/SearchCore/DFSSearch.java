package SearchCore;

import java.awt.List;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;


import java.util.Stack;

public class DFSSearch extends ASearch {

	enum ECOLOR{eGReY , eWHITE, eBLACK}
	
	Stack<Node> m_stack = new Stack<Node>();
	Map<String, Boolean> history = new HashMap<String, Boolean>();

	Puzzle startPuzzle ;
	
	
	@Override
	public void Search()
	{
		Node headNode = new Node(startPuzzle);
		headNode.setM_color(ECOLOR.eWHITE);
		m_stack.push(headNode);
		while(!m_stack.isEmpty())
		{
			Node node = m_stack.pop();
			if (IsGoalNode(node)) 
				{
					System.exit(0);
				}
			node.setM_color(ECOLOR.eGReY);
			LinkedList<Node> childes = ExpandNode(node);
			for (Node c : childes)
			{
				if (c.getM_color() == ECOLOR.eWHITE)
				{
					m_stack.push(c);
				}
			}
			node.setM_color(ECOLOR.eBLACK);
		}
		
		
	}

	public DFSSearch(String vector)
	{
		super(vector);
		startPuzzle = new Puzzle(vector);
	}
	
	boolean IsGoalNode(Node node )
	{
		return ( IsGoal(node.getM_puzzle().getPuzzle()) );
	}
	
	
	private LinkedList<Node> ExpandRelveantNode(LinkedList<Node> nodes , Puzzle NewNodePuzzle , String OldNodeState)
	{
		String newPStr =  NewNodePuzzle.GetPuzzelString();
		if(!history.containsKey(newPStr))
		{
			history.put(newPStr, false);
			if (NewNodePuzzle.GetPuzzelString().compareTo(OldNodeState) !=0 )
			{
				
				Node node = new Node(NewNodePuzzle);
				node.setM_color(ECOLOR.eWHITE);
				PrintPhase(node.getM_puzzle());
				nodes.push(node);
			}
		}
		return nodes;
	}
	
	public LinkedList<Node> ExpandNode(Node node)
	{
	
		String oldStr;
		
		LinkedList<Node> nodes = new LinkedList<Node>();		
		Puzzle p = node.getM_puzzle();
		oldStr = p.GetPuzzelString();
		nodes = ExpandRelveantNode(nodes ,p.Down() ,oldStr);
		nodes = ExpandRelveantNode(nodes ,p.Up(),oldStr);
		nodes = ExpandRelveantNode(nodes ,p.Left() ,oldStr);
		nodes = ExpandRelveantNode(nodes ,p.Right() ,oldStr);
		//newStatePuzzle = p.Up();
		//newPuzzleStateStr = newStatePuzzle.GetPuzzelString();
		
		/*
		if (newPuzzleStateStr.compareTo(p.GetPuzzelString()) != 0)
		{
			newState = new Node(newStatePuzzle);
			newState.setM_color(ECOLOR.eWHITE);
			nodes.add(newState);
		}
		*/
		
		return nodes;
	}
	
	class Node
	{
		private Puzzle m_puzzle;
		private ECOLOR m_color;
		
		public Puzzle getM_puzzle() {
			return m_puzzle;
		}

		public void setM_puzzle(Puzzle m_puzzle) {
			this.m_puzzle = m_puzzle;
		}

		public ECOLOR getM_color() {
			return m_color;
		}

		public void setM_color(ECOLOR m_color) {
			this.m_color = m_color;
		}

		public Node(Puzzle p)
		{
			m_puzzle = p;
		}
		
		
	}
	
}
