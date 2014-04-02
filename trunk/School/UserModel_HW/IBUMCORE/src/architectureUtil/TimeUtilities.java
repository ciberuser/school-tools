package architectureUtil;

import java.sql.Date;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * Converts Calendar to UTC Date
 * @author Eyal
 *
 */

public class TimeUtilities {
	
	/** 
	 * Converts Calendar to Date UTC
	 * @param cal Calendar date and time
	 * @return the date and time in Date format and UTC time zone
	 */
	
	public static Date convertToUTC(Calendar cal) {

		Date date = (Date) cal.getTime();
		TimeZone tz = cal.getTimeZone();

		//Returns the number of milliseconds since January 1, 1970, 00:00:00 UTC 
		long msFromEpochUTC = date.getTime();

		//gives you the current offset in ms from UTC at the current date
		int offsetFromUTC = tz.getOffset(msFromEpochUTC);

		//create a new calendar in UTC time zone, set to this date and add the offset
		Calendar utcCal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		utcCal.setTime(date);
		utcCal.add(Calendar.MILLISECOND, offsetFromUTC);

		return (Date) utcCal.getTime();
	}
	
	
}
