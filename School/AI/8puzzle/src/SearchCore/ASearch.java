package SearchCore;

public abstract class ASearch implements ISearch
{
	public static final int MATIRX_SIZE  = 3;
	
	public static final int[][] GOAL_MATRIX = { {1,2,3},
												{4,5,6},
												{7,8,-1}};
	
	
	public void Search()
	{
		
	}
		
	public void  PrintPhase()
	{
		System.out.println("the state is :\n");
		for (int i = 0 ; i < MATIRX_SIZE ;++i)
		{
			for (int j = 0 ; i <MATIRX_SIZE ; ++i) 
		
			{
				System.out.println(m_puzzleMatrix[i][j] +" ");				
			}
			System.out.println("\n");
		}
	}
	
	protected boolean IsGoal(int[][] matrix)
	{
		for (int i = 0 ; i < MATIRX_SIZE ; ++i)
			for(int j = 0 ; j<MATIRX_SIZE ; ++j)
				if (matrix[i][j]!= GOAL_MATRIX[i][j]) return false;
				
		return true;
	}
	
	// protected
	protected int [][] m_puzzleMatrix = new int[MATIRX_SIZE][MATIRX_SIZE] ;
	
	
}
