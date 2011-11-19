package SearchCore;

import SearchCore.Def;
public class Puzzle 
{
	private int SIZE = Def.MATIRX_SIZE;
	private int BLANK = Def.BLANK; 
	
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
	
	public Puzzle(int [][] puzzle)
	{
		m_puzzle = puzzle;
	}
	
	public Puzzle Up()
	{
		int posRow = FindBlankPosRow();
		int posColum = FindBlankPosColum();
		if (posRow > 0)
		{
			int temp = m_puzzle[posRow-1][posColum];
			m_puzzle[posRow-1][posColum] = BLANK;
			m_puzzle[posRow][posColum] = temp;
		}
		return this;
	
	}
	
	public Puzzle Down()
	{
		int posRow = FindBlankPosRow();
		int posColum = FindBlankPosColum();
		if (posRow < 2) 
		{
			int temp = m_puzzle[posRow+1][posColum];
			m_puzzle[posRow+1][posColum] = BLANK;
			m_puzzle[posRow][posColum] = temp;
		}
		return this;
	}
	
	public Puzzle Left()
	{
		int posRow = FindBlankPosRow();
		int posColum = FindBlankPosColum();
		if(posColum > 0)
		{
			int temp = m_puzzle[posRow][posColum-1];
			m_puzzle[posRow][posColum-1] = BLANK;
			m_puzzle[posRow][posColum] = temp;
		}
		return this;
	}
	
	public Puzzle Right()
	{
		int posRow = FindBlankPosRow();
		int posColum = FindBlankPosColum();
		if (posColum < 2) 
		{
			int temp = m_puzzle[posRow][posColum+1];
			m_puzzle[posRow][posColum+1] = BLANK;
			m_puzzle[posRow][posColum] = temp;
		}
		return this;
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
	
	public String GetPuzzelString()
	{
		String str = new String();
		int col = 0;
		int row = 0;
		for (int i=0 ; i<Def.VECTOR_SIZE ; ++i)
		{
			Integer temp = m_puzzle[row][col];
			if (m_puzzle[row][col] == BLANK) str+="b";
			else str += temp.toString();
			if (col == 2)
			{
				row++;
				col = -1;
			}
			col++;
		}
		
		return str;
	}
	
	
	
	
}
