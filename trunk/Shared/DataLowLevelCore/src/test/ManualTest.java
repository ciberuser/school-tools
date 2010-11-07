package test;
import Classes.RMI.*;
import DataCoreSrc.CDataCoreAppi;
import DataCoreSrc.DataBaseType;
import DataCoreSrc.EDataBaseType;


import java.net.MalformedURLException;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.sql.*;

import com.sun.servicetag.Registry;

public class ManualTest {

	/**
	 * @param args
	 */
	public static void SQlTests()
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
			ResultSet sq = null;
			ResultSet sv = null;
			sq =  q.getSqlConnection().ExcuteStoredProceduresInParm("GetStationDetails",(Object[])parm);
			sq =  q.getSqlConnection().ExcuteQurey("SELECT * FROM students s");
			sv = v.getSqlConnection().ExcuteQurey("SELECT * FROM Table1 T");
			System.out.println("from MySql DataBase");
			while (sq.next())
			{
				System.out.println(sq.getString(2));
			}
			System.out.println("from Access DataBase");
			while (sv.next())
			{
				System.out.println(sv.getString(2));
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
	
	public static void TestRmi()
	{
			try
			{
				CGeneralServerRMI.main(null);
			}
			catch (Exception e) {
					
			}
	
			
	}
	
	public static void main(String[] args)
	{
		
		SQlTests();
//		TestRmi();

		
	}

}
