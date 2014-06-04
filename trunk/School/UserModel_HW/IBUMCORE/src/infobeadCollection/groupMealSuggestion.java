package infobeadCollection;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class groupMealSuggestion
{
	
public enum mealType {
	Big_Family_Meal_Breakfast, Big_Family_Meal_Launch, Big_Family_Meal_Dinner, 
	
	Big_Group_Meal_Breakfast, Big_Group_Meal_Launch, Big_Group_Meal_Dinner;

}

public mealType getSuggestion(int groupSize, Date timeInDate)
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
			return mealType.Big_Family_Meal_Breakfast;
		}
		
		if(noon.getTimeInMillis() > time)
		{
			return mealType.Big_Family_Meal_Launch;
		}
		
		if(evening.getTimeInMillis() < time)
		{
			return mealType.Big_Family_Meal_Dinner;
		}
	}
	else
	{
		if(noon.getTimeInMillis() < time)
		{
			return mealType.Big_Group_Meal_Breakfast;
		}
		
		if(noon.getTimeInMillis() > time)
		{
			return mealType.Big_Group_Meal_Launch;
		}
		
		if(evening.getTimeInMillis() < time)
		{
			return mealType.Big_Group_Meal_Dinner;
		}
	}
	
	return null;
	}
	
}

