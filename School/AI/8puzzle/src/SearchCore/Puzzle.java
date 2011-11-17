package SearchCore;

import SearchCore.Def;
public class Puzzle 
{
	int SIZE = Def.MATIRX_SIZE;
	int BLANK = Def.BLANK; 
	
	public int[][] getPuzzle(){	return m_puzzle; }

	private  int[][] m_puzzle = new int[SIZE][SIZE];
	
	
	public Puzzle(String vector)
	{
		int arr[] = ConverToInt(vector);
		int row =0;
		int col = 0;
		for(int i = 0; i< Def.VECTOR_SIZE ;++i)
		{
			m_puzzle[row][col] = arr[i];
			if (col == 2)
			{
				row++;
				col = -1;
			}
			col++;
		}
	}
	
	public void Up()
	{
		int posRow = FindBlankPosRow();
		int posColum = FindBlankPosColum();
		if (posRow > 0)
		{
			int temp = m_puzzle[posRow-1][posColum];
			m_puzzle[posRow-1][posColum] = BLANK;
			m_puzzle[posRow][posColum] = temp;
		}
	
	}
	
	public void Down()
	{
		int posRow = FindBlankPosRow();
		int posColum = FindBlankPosColum();
		if (posRow < 2) 
		{
			int temp = m_puzzle[posRow+1][posColum];
			m_puzzle[posRow+1][posColum] = BLANK;
			m_puzzle[posRow][posColum] = temp;
		}
	}
	
	public void Left()
	{
		int posRow = FindBlankPosRow();
		int posColum = FindBlankPosColum();
		if(posColum > 0)
		{
			int temp = m_puzzle[posRow][posColum-1];
			m_puzzle[posRow][posColum-1] = BLANK;
			m_puzzle[posRow][posColum] = temp;
		}
	}
	
	public void Right()
	{
		int posRow = FindBlankPosRow();
		int posColum = FindBlankPosColum();
		if (posColum < 2) 
		{
			int temp = m_puzzle[posRow][posColum+1];
			m_puzzle[posRow][posColum+1] = BLANK;
			m_puzzle[posRow][posColum] = temp;
		}
	}
	
	
	private int FindBlankPosRow()
	{
		for(int i =0; i< SIZE ; ++i )
		 for (int j=0 ;j< SIZE; ++j) if(m_puzzle[i][j] == Def.BLANK) return i;
		return -1;
	}
	
	private int FindBlankPosColum()
	{
		for(int i =0; i< SIZE ; ++i )
			 for (int j=0 ;j< SIZE; ++j) if(m_puzzle[i][j] == Def.BLANK) return j;
			return -1;
	}
	
	private int[] ConverToInt(String vector)
	{
		int[] arr = new int[Def.VECTOR_SIZE];
		String[] strArr = vector.split(" ");
		for (int i=0;i<strArr.length ; ++i )
		{ 
			if (strArr[i].matches("b")) arr[i]= BLANK;
			else
			{
				arr[i] = Integer.parseInt(strArr[i]);
			}
		}
		return arr;
	}
		
	
	
	
	
}
