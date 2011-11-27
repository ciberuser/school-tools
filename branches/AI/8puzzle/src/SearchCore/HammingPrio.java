package SearchCore;

import SearchCore.AStarSearch.Node;

public class HammingPrio implements Iheuristic {

	@Override
	public Integer GetScore(Node node) {
		
		int sum = 0;
		int[][] puzzleMatrix = node.getPuzzle().getPuzzle();
		for (int i = 0 ; i < Def.MATRIX_ROW_SIZE ;++i)
			for (int j = 0 ; j< Def.MATRIX_COL_SIZE ;++j )
				if (puzzleMatrix[i][j] != Def.GOAL_MATRIX[i][j]) sum++;
		
		return sum;
	}

}
