package SearchCore;


public abstract class ASearch implements ISearch
{
	protected int m_steps;
	
	protected long m_startTime ;
	
	public ASearch()
	{
		
	}
			
	public ASearch(String vector)
	{
		
	}

	public void Search()
	{
		m_startTime = System.currentTimeMillis(); 
		
	}
	
	protected void PrintTotalTime()
	{
		long time = System.currentTimeMillis()- m_startTime;
		System.out.println("it's take " +time+ " milli sec");
	}
	
	public void  PrintPhase(Puzzle p)
	{ 
		
		int[][] puzzleMatrix = p.getPuzzle();
		System.out.print("(");
		for (int i = 0 ; i < Def.MATRIX_ROW_SIZE ;++i)
		{
			for (int j = 0 ; j < Def.MATRIX_COL_SIZE ; ++j) 
			{
				if (puzzleMatrix[i][j]==-1) System.out.print("b ");
				else
				System.out.print(puzzleMatrix[i][j]+" " );				
			}
			
		}
		System.out.print(")->\n");
	}
	
	public void printPhase(String puzzleStr)
	{
		System.out.println("(" + puzzleStr + ")->");
	}
	
	protected boolean IsGoal(int[][] matrix)
	{
		for (int i = 0 ; i < Def.MATRIX_ROW_SIZE ; ++i)
			for(int j = 0 ; j<Def.MATRIX_COL_SIZE ; ++j)
				if (matrix[i][j] != Def.GOAL_MATRIX[i][j])	return false;
						
		System.out.println("success!!") ;
		
		return true;
	}
	

	
	
}