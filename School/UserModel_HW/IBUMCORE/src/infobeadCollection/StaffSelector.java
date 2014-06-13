package infobeadCollection;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import au.com.bytecode.opencsv.CSV;
import au.com.bytecode.opencsv.CSVReadProc;
import au.com.bytecode.opencsv.CSVReader;

public class StaffSelector {

	private final static String STAFF_LIST_PATH = "src/infobeadCollection/Staff_list.csv";
	private final static int INDEX_NAME =0;
	private final static int INDEX_ROLE=1;
	private final static int INDEX_LAG =2;
	
	public final static String NOT_FOUND="not found";
	public enum ERol{Chef,Waiter}
	
	
	public StaffSelector()
	{
		File f = new File(STAFF_LIST_PATH);
		if(!f.exists())
		{
			System.err.println(" the file "+ STAFF_LIST_PATH+ " not exist ");
			return;
		}
		CSV csv = CSV.separator(',').create();
		CSVReader reader =	csv.reader(STAFF_LIST_PATH)	;
	 
		 try {
					m_staffObj =	reader.readAll();
		} catch (IOException e) {e.printStackTrace();}
		 
		 
		
		/*csv.read(STAFF_LIST_PATH, new CSVReadProc()
		{
		    public void procRow(int rowIndex, String... values)
		    {
		        System.out.println(rowIndex + ": " + Arrays.asList(values));
		        
		    }}
	
		    );
	*/	
	}
	
	public String GetWaiterKnowLang(String lang)
	{
		if (m_staffObj==null) return NOT_FOUND;
		for(String[] line : m_staffObj)
		{
			if (line[INDEX_LAG].contentEquals(lang))
			{
				return line[INDEX_NAME].toString()+" ";
			}
		}
		return NOT_FOUND;
	}
	
	
	
	private List<String[]>  m_staffObj = new ArrayList<String[]>();
	
	
	public static void main(String[] args) 
	{
		StaffSelector sf = new StaffSelector();
		
	}
	
	
}
