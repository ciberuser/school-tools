package SearchCore;

import SearchCore.AStarSearch.Node;

public interface Iheuristic 
{
	Integer GetScore(AStarSearch.Node node);

	
}
