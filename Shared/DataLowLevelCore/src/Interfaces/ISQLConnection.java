package Interfaces;
import java.sql.Connection;
import java.sql.ResultSet;


public interface ISQLConnection {

	Connection GetConnection();
	void Connect();
	void Close();
	void Connect(String connectionString,String userName,String Password);
	void Connect(String userName,String Password);
	void Reconnect(String userName,String Password);
	
	void SetDriver(String driver);
	void LoadDriver(String driver);
	ResultSet ExcuteQurey(String sqlQurey);
	ResultSet ExcuteStoredProcedures(String procdureName);
	
}
