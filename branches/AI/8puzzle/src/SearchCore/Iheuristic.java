package SearchCore;

import SearchCore.AStarSearch.Node;

public interface Iheuristic 
{
	Integer GetScore(Node node);
}
