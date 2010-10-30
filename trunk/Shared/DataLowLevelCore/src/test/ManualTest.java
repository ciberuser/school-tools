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
		DataBaseType v = null;
		try
		{
			q = ma.SetSQLTarget(EDataBaseType.eMYSQL,"jdbc:mysql://localhost:3306/quickhikedb1");
			v= ma.SetSQLTarget(EDataBaseType.eACCESS, "jdbc:odbc:Data12");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		q.getSqlConnection().Connect("root", "41825158");
		v.getSqlConnection().Connect("admin","41825158");
		
	
		
		Object[] parm = {(Integer)2};
		try {
			ResultSet s= null;
			s =  q.getSqlConnection().ExcuteStoredProceduresInParm("GetStationDetails",(Object[])parm);
			s = v.getSqlConnection().ExcuteQurey("SELECT * FROM Table1 T");
			while (s.next())
			{
				System.out.println(s.getString(2));
			}
			
			//s = q.getSqlConnection().ExcuteQurey("SELECT * FROM students s");
			//while (s.next())
			//{
			//	System.out.println(s.getString(2));
			//}
		} 
		
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		q.getSqlConnection().Close();
		v.getSqlConnection().Close();
	}

}
