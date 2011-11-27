package SearchCore;

public class Def 
{
	
	public static final int BLANK = -1;
	public static final int MATRIX_ROW_SIZE  = 3;
	public static final int MATRIX_COL_SIZE = 3;
	public static final int VECTOR_SIZE = MATRIX_COL_SIZE*MATRIX_ROW_SIZE;
	public static final int[][] GOAL_MATRIX = { {1,2,3},{4,5,6},{7,8,BLANK}};
	public static final int MAX_ATTEMPT = 100000;
}
