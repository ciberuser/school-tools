package test;
import Interfaces.*;
import Classes.*;

public class ManualTest {

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{

		ISQLConnection icon = new CmySQLConnection();
		icon.Connect("jdbc:mysql://localhost:3306/quickhikedb1","root","41825158");
		// TODO Auto-generated method stub
		icon.Close();
	}

}
