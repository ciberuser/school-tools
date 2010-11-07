package Classes.SQL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import Interfaces.ISQLConnection;

public class CXMLSQLConnection extends ASQLConnection implements ISQLConnection {

	String m_SQlDriver;
	
	
	public CXMLSQLConnection(){
		
	}
	
	public CXMLSQLConnection(String FilePath)
	{
		super(FilePath);
		
	}
	
	public CXMLSQLConnection(String FilePath,String Driver,boolean Create)
	{
		super(FilePath);
		if (Create)
		{
		
		}
		
		
		setSQlDriver(Driver);
		
	}
	
	public String getSQlDriver() {
		return m_SQlDriver;
	}

	public void setSQlDriver(String sqlDriver) {
		m_SQlDriver = sqlDriver;
	}

	
	
	
	
	public void Close() {
		// TODO Auto-generated method stub

	}

	@Override
	public void Connect() {
		// TODO Auto-generated method stub

	}

	@Override
	public void Connect(String connectionString, String userName,
			String Password) {
		// TODO Auto-generated method stub

	}

	@Override
	public void Connect(String userName, String Password) {
		// TODO Auto-generated method stub

	}

	@Override
	public ResultSet ExcuteQurey(String sqlQurey) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSet ExcuteStoredProceduresInParm(String procdureName,
			Object[] dataType) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSet ExcuteStoredProceduresOutParm(String procdureName,
			int[] dataType) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Connection GetConnection() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void Reconnect(String userName, String Password) {
		// TODO Auto-generated method stub

	}

}
