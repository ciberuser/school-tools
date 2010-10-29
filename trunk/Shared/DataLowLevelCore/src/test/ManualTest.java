package test;
import Interfaces.*;
import Classes.*;
import DataCoreSrc.CDataCoreAppi;
import DataCoreSrc.DataBaseType;
import DataCoreSrc.EDataBaseType;
import java.sql.*;

public class ManualTest {

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{

		CDataCoreAppi ma = CDataCoreAppi.GetInstance();
		DataBaseType q = null;
		try
		{
			q = ma.SetSQLTarget(EDataBaseType.eMYSQL,"jdbc:mysql://localhost:3306/quickhikedb1");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		q.getSqlConnection().Connect("root", "41825158");
		Object[] parm = {};
		try {
			ResultSet s= null;
			s =  q.getSqlConnection().ExcuteStoredProceduresInParm("GetAllStations",(Object[])parm);
			while (s.next())
			{
				System.out.println(s.getString(2));
			}
			s = q.getSqlConnection().ExcuteQurey("SELECT * FROM students s");
			while (s.next())
			{
				System.out.println(s.getString(2));
			}
		} 
		
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		q.getSqlConnection().Close();
		
	}

}
