package infobeadCollection;

import infobeadCollection.groupMealSuggestion.mealType;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class groupServiceLevelSuggestion
{
	
public enum groupServiceLevel
{
	Big_Group_High_Service_Level, Big_Group_Low_Service_Level, 
	Small_Group_High_Service_Level, Small_Group_Low_Service_Level;

}

public groupServiceLevel getSuggestion(int groupSize,  Date timeInDate)
{

	Calendar noon = new GregorianCalendar();
	
	noon.set(Calendar.HOUR_OF_DAY, 16);
	
	Calendar evening = new GregorianCalendar();
	evening.set(Calendar.HOUR_OF_DAY, 16);
	
	long time = timeInDate.getTime();
	
	if(groupSize < 10)
	{
		if(noon.getTimeInMillis() < time)
		{
			return groupServiceLevel.Small_Group_Low_Service_Level;
		}
		
		if(noon.getTimeInMillis() > time)
		{
			return groupServiceLevel.Small_Group_High_Service_Level;
		}
		
		if(evening.getTimeInMillis() < time)
		{
			return groupServiceLevel.Small_Group_High_Service_Level;
		}
	}
	else
	{
		if(noon.getTimeInMillis() < time)
		{
			return groupServiceLevel.Big_Group_Low_Service_Level;
		}
		
		if(noon.getTimeInMillis() > time)
		{
			return groupServiceLevel.Big_Group_Low_Service_Level;
		}
		
		if(evening.getTimeInMillis() < time)
		{
			return groupServiceLevel.Big_Group_High_Service_Level;
		}
	}
	
	return null;
	}

}