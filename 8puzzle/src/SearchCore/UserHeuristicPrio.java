package SearchCore;

import SearchCore.AStarSearch.Node;

public class UserHeuristicPrio implements Iheuristic {

	@Override
	public Integer GetScore(Node node)
	{
		int [][] puzzleMatrix = node.getPuzzle().getPuzzle();
		int GoalCube  = 0;
		int MatrixCube = 0;
		int sumQube = 0;
		//int hamming = GetHammingSum(node);
		for (int i = 0 ; i <Def.MATRIX_ROW_SIZE -1; ++i)
			for (int j = 0; j< Def.MATRIX_COL_SIZE-1 ;++j)
			{
				int localI = i;
				int localJ = j;
				if( localI +1  < Def.MATRIX_ROW_SIZE  && localJ +1 < Def.MATRIX_COL_SIZE)
				{
					MatrixCube = puzzleMatrix[localI][localJ]+puzzleMatrix[localI+1][localJ] + puzzleMatrix[localI][localJ+1] + puzzleMatrix[localI+1][localJ+1];
					GoalCube = Def.GOAL_MATRIX[localI][localJ] + Def.GOAL_MATRIX[localI+1][localJ] +Def.GOAL_MATRIX[localI][localJ+1] + Def.GOAL_MATRIX[localI+1][localJ+1];
				}
				sumQube += ((GoalCube - MatrixCube) == 0 )? 0 : 1;
				
			}
				
		return sumQube ;
	}
	
	int GetHammingSum(Node node)
	{
		int sum = 0;
		int[][] puzzleMatrix = node.getPuzzle().getPuzzle();
		for (int i = 0 ; i < Def.MATRIX_ROW_SIZE ;++i)
			for (int j = 0 ; j< Def.MATRIX_COL_SIZE ;++j )
				if (puzzleMatrix[i][j] != Def.GOAL_MATRIX[i][j]) sum++;
		
		return sum;
	}

}
