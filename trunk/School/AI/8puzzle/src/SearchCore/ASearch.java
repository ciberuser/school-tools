package SearchCore;

import SearchCore.Def;

public abstract class ASearch implements ISearch
{
	
		
	public ASearch()
	{
		
	}
			
	public ASearch(String vector)
	{
		
	}

		
		
	public void  PrintPhase(Puzzle p)
	{
		int[][] puzzleMatrix = p.getPuzzle();
		System.out.println("the state is :\n");
		for (int i = 0 ; i < Def.MATIRX_SIZE ;++i)
		{
			for (int j = 0 ; j < Def.MATIRX_SIZE ; ++j) 
			{
				System.out.print(puzzleMatrix[i][j] +" ");				
			}
			System.out.print("\n");
		}
	}
	
	protected boolean IsGoal(int[][] matrix)
	{
		for (int i = 0 ; i < Def.MATIRX_SIZE ; ++i)
			for(int j = 0 ; j<Def.MATIRX_SIZE ; ++j)
				if (matrix[i][j] != Def.GOAL_MATRIX[i][j]) return false;
				
		return true;
	}
	

	
	
}
