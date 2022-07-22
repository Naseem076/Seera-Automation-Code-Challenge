package pageObjects;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class DryRunPgms {
	
	private static final DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

	public static void main(String[] args) 
	{
		
		

	   

	        Date currentDate = new Date();
	        System.out.println(dateFormat.format(currentDate));

	        // convert date to calendar
	        Calendar c = Calendar.getInstance();
	        c.setTime(currentDate);

	        // manipulate date
	        c.add(Calendar.YEAR, 1);
	        c.add(Calendar.MONTH, 1);
	        c.add(Calendar.DATE, 1); //same with c.add(Calendar.DAY_OF_MONTH, 1);

	        // convert calendar to date
	        Date currentDatePlusOne = c.getTime();

	        System.out.println(dateFormat.format(currentDatePlusOne));

	    }

	}


