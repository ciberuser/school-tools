package Interfaces;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;



public interface ISQLConnection {

	Connection GetConnection();
	void Connect();
	void Close();
	void Connect(String connectionString,String userName,String Password);
	void Connect(String userName,String Password);
	void Reconnect(String userName,String Password);
	
	ResultSet ExcuteQurey(String sqlQurey) throws SQLException;
	ResultSet ExcuteStoredProceduresOutParm(String procdureName,int[] dataType) throws Exception;
	ResultSet ExcuteStoredProceduresInParm(String procdureName,Object[] dataType) throws Exception;
}

