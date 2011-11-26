package SearchCore;

import SearchCore.AStartSearch.Node;

public interface Iheuristic 
{
	Integer GetScore(AStartSearch.Node node);

	
}
