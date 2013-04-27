package App;



import java.util.Iterator;
import java.util.List;
import java.util.Map;

import Services.CSVBuilder;
import Services.Log.ELogLevel;
import Core.CommonCBase;
import Core.PinterestStatisticsBuilder;
import Core.StatisticsBuilder;

public class StatisticsDumper extends CommonCBase
{
	enum EState {eUeser2Subject,eSubject2User,esubject2Item};
	
	public StatisticsDumper(String csvRootFolderPath)
	{
		m_csvPoplarUserFilePath = csvRootFolderPath+ "/" +"users.csv" ;
		m_csvPoplarSubjectsFilePath = csvRootFolderPath + "/"  + "subjects.csv";
		m_csvItemCount = csvRootFolderPath + "/" + "items.csv"; 
	}
	
	public boolean DumpStatistics()
	{
		boolean ret = true;
		String errMsg ="";
		WriteLineToLog("going to write subject statistics file...filepath = " + m_csvPoplarSubjectsFilePath, ELogLevel.INFORMATION);
		if( !DumpCountStatistics(StatisticsBuilder.GetInstance().GetStatistics(PinterestStatisticsBuilder.EPinStatsType.eUserSubject).getSubTargetTargetDB(),
				m_csvPoplarSubjectsFilePath, EState.eSubject2User))
			
		{
			errMsg += "failed to dump subject statistics!";
			ret &=false;
			WriteLineToLog(errMsg, ELogLevel.ERROR);
		}
		
		WriteLineToLog("going to write users statistics file...filepath =" +m_csvPoplarUserFilePath, ELogLevel.INFORMATION);
		if (!DumpCountStatistics(StatisticsBuilder.GetInstance().GetStatistics(PinterestStatisticsBuilder.EPinStatsType.eUserSubject).getTargetToSubTargetDB(), m_csvPoplarUserFilePath, EState.eUeser2Subject))
		{
			String errMsguser = "Failed to dump users statistics !";
			ret&=false;
			WriteLineToLog(errMsguser, ELogLevel.ERROR);
			errMsg+= "\n" + errMsguser;
		}
		
		WriteLineToLog("going to write item statictics file... filepath = "+ m_csvItemCount, ELogLevel.INFORMATION);
		if(!DumpCountStatistics(StatisticsBuilder.GetInstance().GetStatistics(PinterestStatisticsBuilder.EPinStatsType.eSubjectItem).getTargetToSubTargetDB(), m_csvItemCount, EState.esubject2Item))
		{
			String errMsguser = "Failed to dump number of items statistics !";
			ret&=false;
			WriteLineToLog(errMsguser, ELogLevel.ERROR);
			errMsg+= "\n" + errMsguser;
		}
		if (!ret) WriteToConsole(errMsg);
		return ret;
	}
	
	private String[] CreateCountHeader(EState state)
	{
		String[] header  = new String[2];
		switch (state) {
		case  eUeser2Subject:
			header[0] = "users";
			header[1] = "Number of subjects";
			break;
		case eSubject2User :
			header[0] = "Subjects";
			header[1] = "Number of users";	
			break;
		case esubject2Item:
			header[0] = "Subject";
			header[1] = "Number of Items";
			break;
		default:
			break;
		}
				
		return header;
	}
	
	
	private boolean DumpCountStatistics(Map<String,List<String>> statDB, String filepath,EState state)
	{
		
		boolean res = true ; 
		CSVBuilder builer = new CSVBuilder(filepath);
		
		res &= builer.WriteHeader(CreateCountHeader(state));
		
		
		Iterator<Map.Entry<String, List<String>>>  it = statDB.entrySet().iterator();
		while(it.hasNext())
		{
			
			Map.Entry<String, List<String>> mapEntry = (Map.Entry<String, List<String>>) it.next();
			String count =" "+ mapEntry.getValue().size();
			
			String[] line = {mapEntry.getKey(),count};
			WriteLineToLog(String.format("add the line{%s,%s} to statistics %s file ", filepath,line[0],line[1]) , ELogLevel.INFORMATION);
			res &=  builer.WriteLine(line);
			
		}
		return res;
	}
	
	
	private String m_csvPoplarSubjectsFilePath;
	private String m_csvPoplarUserFilePath;
	
	private String m_csvItemCount;
	
}
