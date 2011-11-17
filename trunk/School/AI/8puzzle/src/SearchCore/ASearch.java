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

	public void Search()
	{
		
	}
	
	
	
	public void  PrintPhase()
	{
		System.out.println("the state is :\n");
		for (int i = 0 ; i < Def.MATIRX_SIZE ;++i)
		{
			for (int j = 0 ; i < Def.MATIRX_SIZE ; ++i) 
		
			{
				System.out.println(m_puzzleMatrix[i][j] +" ");				
			}
			System.out.println("\n");
		}
	}
	
	protected boolean IsGoal(int[][] matrix)
	{
		for (int i = 0 ; i < Def.MATIRX_SIZE ; ++i)
			for(int j = 0 ; j<Def.MATIRX_SIZE ; ++j)
				if (matrix[i][j] != Def.GOAL_MATRIX[i][j]) return false;
				
		return true;
	}
	
	// protected
	protected int [][] m_puzzleMatrix = new int[Def.MATIRX_SIZE][Def.MATIRX_SIZE] ;
	
	
}
