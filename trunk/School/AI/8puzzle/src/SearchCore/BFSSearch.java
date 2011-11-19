package SearchCore;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;


public class BFSSearch extends ASearch implements ISearch {

	Puzzle startPuzzle;	
	protected Queue<Puzzle> m_queue = new LinkedList<Puzzle>();	
	Map<String,Integer> m_phaseState = new HashMap<String,Integer>(); // 
		
	public BFSSearch() 
	{
		
	}

	public BFSSearch(String vector)
	{
		super(vector);
		startPuzzle  = new Puzzle(vector);
	}

	
	public void Search()
	{
		SetVisit(startPuzzle, null);
		while (!m_queue.isEmpty())
		{
				Puzzle p = m_queue.remove();
				ChildNode(p);
		}
		System.out.println("no solution in BFS");
	}

	private Puzzle ChildNode(Puzzle p)
	{
		String oldPuzzleStr = p.GetPuzzelString();
	 	SetVisit(p.Up(), oldPuzzleStr) ;
		if (CheckCompletion(p))return p ;
		oldPuzzleStr = p.GetPuzzelString();
		SetVisit(p.Down(), oldPuzzleStr);
		if(CheckCompletion(p))return p;
		oldPuzzleStr  = p.GetPuzzelString();
		SetVisit(p.Left(), oldPuzzleStr) ;
		if(CheckCompletion(p)) return p ;
		oldPuzzleStr = p.GetPuzzelString();
		SetVisit(p.Right(), oldPuzzleStr);
		if(CheckCompletion(p))return p;
		
		return p;
	}
	
	private void SetVisit(Puzzle p,String oldPhase)
	{
		PrintPhase(p);
		if(!m_phaseState.containsKey(p.GetPuzzelString()))
		{
			int newValue = (oldPhase== null) ? 0 : m_phaseState.get(oldPhase)+1;
			m_phaseState.put(p.GetPuzzelString(),newValue);
			m_queue.add(p);
		}
	}
	
	private boolean CheckCompletion(Puzzle p)
	{
		
		if(IsGoal(p.getPuzzle()))
		{
			System.out.println("success!! Depth:"+ m_phaseState.get(p.GetPuzzelString())) ;
			System.exit(0);
			return true;
		}
		return false;
	}
	
}
