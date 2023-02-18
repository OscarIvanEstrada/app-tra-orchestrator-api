package com.apptraorchestratorapi.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.apptraorchestratorapi.enums.ErrorEnum;
import com.apptraorchestratorapi.exceptions.ApiException;

public class DateUtils {
	private static final String FORMAT = "yyyyMMddTHHmmssSSSZ";

    public static Date getDate(){
        return new Date();
    }
    
    public static Date getDate(String strategy){
		if(strategy == null || strategy.isEmpty()) {
			throw new ApiException(ErrorEnum.ORCHESTRATOR_CORE,"getDate");
		}
		return new Date();
	}

    /**
     * Por defecto retorna la fecha de hoy en el formato yyyyMMddTHHmmssSSSZ
     * @return
     */
    public static long getDateLong() {

                try {
                    // Create an instance of SimpleDateFormat used for formatting
                    // the string representation of date (month/day/year)
                    DateFormat df = new SimpleDateFormat(FORMAT);

                    // Get the date today using Calendar object.
                    Date today = Calendar.getInstance().getTime();

                    // Using DateFormat format method we can create a string
                    // representation of a date with the defined format.
                    String reportDate = df.format(today);

                    // Print what date is today!
                    return  Long.valueOf(reportDate);
                } catch (Exception e) {

                }
        return new Date().getTime();
    }

    public static String getDateString(String pattern){
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        String strDate = dateFormat.format(DateUtils.getDate());
        return strDate;
    }

}

