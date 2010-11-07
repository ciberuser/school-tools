package Classes.SQL;


import DataCoreSrc.CDataCoreAppi;
import DataCoreSrc.DataBaseType;
import DataCoreSrc.EDataBaseType;
import Interfaces.ISQLConnection;
import Interfaces.ISQLManage;

public class CSQLManage implements ISQLManage 
{
	private CDataCoreAppi m_Cappi;

	public CSQLManage()
	{
        Init();
	}

	public void Init()
	{
		 m_Cappi = CDataCoreAppi.GetInstance();
	}
	@Override
	public ISQLConnection GetSQLTarget(String Url) {
		return  (ISQLConnection) m_Cappi.GetSQLTarget(Url).getSqlConnection();
    }
	
	@Override
	public void RegisterDataBase(EDataBaseType dataBaseEnum, String Url) {
		try
        {
            m_Cappi.SetSQLTarget(dataBaseEnum, Url);
        }
		catch (Exception e)
		{
			
		}
		
	}
	@Override
	public CDataCoreAppi getCappi() {
		
		return m_Cappi; 
	}
	
	

}
