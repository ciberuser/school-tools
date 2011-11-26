package SearchCore;

import java.lang.Math;


public class ManhattanPrio implements Iheuristic 
{
	public Integer GetScore(AStartSearch.Node node)
	{
		int sum =0;
		int rowPos,colPos;
		Puzzle p = node.getPuzzle();
		int[][] goalMatrix = Def.GOAL_MATRIX;
		for(int i = 0 ; i< Def.MATRIX_ROW_SIZE ; ++i )
			for (int j=0 ; j<Def.MATRIX_COL_SIZE ; ++j )
			{
				rowPos = p.FindNunPosRow(goalMatrix[i][j]);
				colPos = p.FindNunPosCol(goalMatrix[i][j]);
				sum +=(Math.abs(rowPos-i) + Math.abs(colPos-j));
     		}
	return sum;
			
		 
	}
	
	
}
