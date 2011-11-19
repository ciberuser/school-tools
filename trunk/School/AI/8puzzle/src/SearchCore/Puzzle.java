package SearchCore;

import SearchCore.Def;
public class Puzzle 
{
	private int ROW_SIZE = Def.MATRIX_ROW_SIZE;
	private int COL_SIZE = Def.MATRIX_COL_SIZE;
	private int BLANK = Def.BLANK; 
	
	public int[][] getPuzzle(){	return m_puzzle; }

	private  int[][] m_puzzle = new int[ROW_SIZE][COL_SIZE];
	
		
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
		int [][] tempPuzzle = CopyPuzzleMatrix(m_puzzle);
		if (posRow > 0)
		{
			int temp = tempPuzzle[posRow-1][posColum];
			tempPuzzle[posRow-1][posColum] = BLANK;
			tempPuzzle[posRow][posColum] = temp;
		}
		return new Puzzle(tempPuzzle);
	
	}
	
	public Puzzle Down()
	{
		int posRow = FindBlankPosRow();
		int posColum = FindBlankPosColum();
		int[][] tempPuzzle = CopyPuzzleMatrix(m_puzzle);
		if (posRow < 2) 
		{
			int temp = tempPuzzle[posRow+1][posColum];
			tempPuzzle[posRow+1][posColum] = BLANK;
			tempPuzzle[posRow][posColum] = temp;
		}
		return new Puzzle(tempPuzzle);
	}
	
	public Puzzle Left()
	{
		int posRow = FindBlankPosRow();
		int posColum = FindBlankPosColum();
		int[][] tempPuzzle = CopyPuzzleMatrix(m_puzzle);
		if(posColum > 0)
		{
			int temp = tempPuzzle[posRow][posColum-1];
			tempPuzzle[posRow][posColum-1] = BLANK;
			tempPuzzle[posRow][posColum] = temp;
		}
		return new Puzzle(tempPuzzle);
	}
	
	public Puzzle Right()
	{
		int posRow = FindBlankPosRow();
		int posColum = FindBlankPosColum();
		int[][] tempPuzzle = CopyPuzzleMatrix(m_puzzle);
		if (posColum < 2) 
		{
			int temp = tempPuzzle[posRow][posColum+1];
			tempPuzzle[posRow][posColum+1] = BLANK;
			tempPuzzle[posRow][posColum] = temp;
		}
		return new Puzzle(tempPuzzle);
	}
	
	
	private int FindBlankPosRow()
	{
		for(int i =0; i< ROW_SIZE ; ++i )
		 for (int j=0 ;j< COL_SIZE; ++j) if(m_puzzle[i][j] == Def.BLANK) return i;
		return -1;
	}
	
	private int FindBlankPosColum()
	{
		for(int i =0; i< ROW_SIZE ; ++i )
			 for (int j=0 ;j< COL_SIZE; ++j) if(m_puzzle[i][j] == Def.BLANK) return j;
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
	
	private int[][] CopyPuzzleMatrix(int[][] puzzle)
	{
		int[][] newMatrix =new int[ROW_SIZE][COL_SIZE];
		for(int i=0; i< ROW_SIZE ; i++)
			for (int j=0 ; j<COL_SIZE ; j++)
			{
				newMatrix[i][j] = puzzle[i][j];
			}
			return newMatrix;
	}
	
}
