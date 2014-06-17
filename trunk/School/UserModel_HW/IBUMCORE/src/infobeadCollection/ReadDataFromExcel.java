package infobeadCollection;

import java.io.FileInputStream;
import java.io.FilterInputStream;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class ReadDataFromExcel {
	
	private String FileName;
	private int colNumber;
	private int rowNumber;
	
	public ReadDataFromExcel (String fileName , int colNumber , int rowNumber){
		this.FileName=fileName;
		this.colNumber = colNumber;
		this.rowNumber=rowNumber;
	}
	
	
	public  String ReadData()
	{
		int rowCount=0;
		Cell cell = null;
		Row row;
		String value = null;
	
			  try
		    {
				// InputStream input = new BufferedInputStream( new FileInputStream(fileName));
				Workbook wb = WorkbookFactory.create(new FileInputStream(FileName));
		                    
			    //Get first/desired sheet from the workbook
			    XSSFSheet sheet = (XSSFSheet) wb.getSheetAt(0);
		
		        //Iterate through each rows one by one
		        Iterator<Row> rowIterator = sheet.iterator();
		        
		     
		       
		        while (rowIterator.hasNext()) 
		        {	
		        	row = rowIterator.next();
		            //For each row, iterate through all the columns
		            Iterator<Cell> cellIterator = row.cellIterator();
		         
		            if(row.getRowNum()>=1)
		            {	
		            	
			            while (cellIterator.hasNext() && rowCount==rowNumber) 
			            {
			            	cell = cellIterator.next(); 
			            	for (int i=1;i<this.colNumber ; i++)
			            	{
			            		cell = cellIterator.next(); 
			            	}
	
			            	value= cell.getStringCellValue();
			            	//System.out.println(cell.getStringCellValue());
			            	  ((FilterInputStream) wb).close();
			            }
			            
			            rowCount++;
			           
		            }
		        }
		      
		    }
			  catch (Exception e) {}
			   
			  return value;
	}
}
