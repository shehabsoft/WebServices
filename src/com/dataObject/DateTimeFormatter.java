/*
 * Used to format date/time strings
 * 
 * Auther       : Eng. Ayman Atiyeh
 * Creation Date: 06/09/2004
 * 
 * ver   Developer          Date        Comments
 * ----  -----------------  ----------  ----------------------------------------
 * 1.00  Eng. Ayman Atiyeh  06/09/2004  - First version
 */

package com.dataObject;


import java.text.*;
import java.util.*;
import java.sql.Timestamp;

public abstract class DateTimeFormatter {
    /*
     * Formatting inctances
     */

    /** Used to generate date and time representation */
    private static final SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

    /** Used to generate date representation */
    private static final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");

    /** Used to generate time representation */
    private static final SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm:ss");
    
    /** Used to generate date representation */
    private static final SimpleDateFormat parserDateFormatter = new SimpleDateFormat("dd/MM/yyyy");    

    /*
     * Methods
     */

    /**
     * Used to generate date and time representation
     */
    public static String formatDateTime(Date date) {
        return dateTimeFormatter.format(date);
    }

    /**
     * Used to generate date and time representation
     */
    public static String formatDateTime(long timeMillis) {
        return formatDateTime(new Date(timeMillis));
    }

    /**
     * Used to generate date representation
     */
    public static String formatDate(Date date) {
        return dateFormatter.format(date);
    }

    /**
     * Used to generate date representation
     */
    public static String formatDate(long timeMillis) {
        return formatDate(new Date(timeMillis));
    }

    /**
     * Used to generate time representation
     */
    public static String formatTime(long timeMillis) {
        return timeFormatter.format(new Date(timeMillis));
    }

    /**
     * Used to generate time representation
     */
    public static String formatTime(Timestamp time) {
        return formatTime(time.getTime());
    }
    
    public static boolean isDateAfterSysDate(String date,String time){
        
        GregorianCalendar sysCal = new java.util.GregorianCalendar();            
        sysCal.set(sysCal.SECOND,0);

        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy H:m");
        StringBuffer ticketDateTime = new StringBuffer(date).append(" ").append(time);
        ticketDateTime.setCharAt(ticketDateTime.indexOf("-"),'/');
        ticketDateTime.setCharAt(ticketDateTime.lastIndexOf("-"),'/');
        try{
            GregorianCalendar givenDateCal = new java.util.GregorianCalendar();
            givenDateCal.setTime(sdf.parse(ticketDateTime.toString())); 
            givenDateCal.set(givenDateCal.SECOND,0);
            return givenDateCal.after(sysCal);      
        } catch(ParseException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    /**
     * Check if the passed Date is after system date
     * 
     * @return true if yes
     * @param date Compared Date
     */
    public static boolean isDateAfterSysDate(Date date) {
        
        Calendar dateCalendar = Calendar.getInstance();
        dateCalendar.setTime(date);

        dateCalendar.set(Calendar.HOUR_OF_DAY, 0);
        dateCalendar.set(Calendar.MINUTE, 0);
        dateCalendar.set(Calendar.SECOND, 0);
        dateCalendar.set(Calendar.MILLISECOND, 0);
        
        Calendar systemCalendar = Calendar.getInstance();
        systemCalendar.setTime(new Date());

        systemCalendar.set(Calendar.HOUR_OF_DAY, 0);
        systemCalendar.set(Calendar.MINUTE, 0);
        systemCalendar.set(Calendar.SECOND, 0);
        systemCalendar.set(Calendar.MILLISECOND, 0);
        
        return dateCalendar.after(systemCalendar);
    }
    
    /**
     * Check if the passed Date is after system date
     * 
     * @return true if yes
     * @param date Compared Date
     */
    public static boolean isDateBeforeSysDate(Date date) {
        
        Calendar dateCalendar = Calendar.getInstance();
        dateCalendar.setTime(date);

        dateCalendar.set(Calendar.HOUR_OF_DAY, 0);
        dateCalendar.set(Calendar.MINUTE, 0);
        dateCalendar.set(Calendar.SECOND, 0);
        dateCalendar.set(Calendar.MILLISECOND, 0);
        
        Calendar systemCalendar = Calendar.getInstance();
        systemCalendar.setTime(new Date());

        systemCalendar.set(Calendar.HOUR_OF_DAY, 0);
        systemCalendar.set(Calendar.MINUTE, 0);
        systemCalendar.set(Calendar.SECOND, 0);
        systemCalendar.set(Calendar.MILLISECOND, 0);
        
        return dateCalendar.before(systemCalendar);
    }
    
    /**
     * Check if the passed Date is after system date
     * 
     * @return true if yes
     * @param date Compared Date
     */
    public static boolean isDateEqualSysDate(Date date) {
        
        Calendar dateCalendar = Calendar.getInstance();
        dateCalendar.setTime(date);

        dateCalendar.set(Calendar.HOUR_OF_DAY, 0);
        dateCalendar.set(Calendar.MINUTE, 0);
        dateCalendar.set(Calendar.SECOND, 0);
        dateCalendar.set(Calendar.MILLISECOND, 0);
        
        Calendar systemCalendar = Calendar.getInstance();
        systemCalendar.setTime(new Date());

        systemCalendar.set(Calendar.HOUR_OF_DAY, 0);
        systemCalendar.set(Calendar.MINUTE, 0);
        systemCalendar.set(Calendar.SECOND, 0);
        systemCalendar.set(Calendar.MILLISECOND, 0);
        
        return dateCalendar.equals(systemCalendar);
    }    
    
    /**
     * Check if the passed Date is after the other date
     * 
     * @return true if yes
     * @param fromDate From Date
     * @param toDate To Date
     */
    public static boolean isDateAfterDate(Date fromDate,Date toDate) {
        
        Calendar fromDateCalendar = Calendar.getInstance();
        fromDateCalendar.setTime(fromDate);

        fromDateCalendar.set(Calendar.HOUR_OF_DAY, 0);
        fromDateCalendar.set(Calendar.MINUTE, 0);
        fromDateCalendar.set(Calendar.SECOND, 0);
        fromDateCalendar.set(Calendar.MILLISECOND, 0);
        
        Calendar toDateCalendar = Calendar.getInstance();
        toDateCalendar.setTime(toDate);

        toDateCalendar.set(Calendar.HOUR_OF_DAY, 0);
        toDateCalendar.set(Calendar.MINUTE, 0);
        toDateCalendar.set(Calendar.SECOND, 0);
        toDateCalendar.set(Calendar.MILLISECOND, 0);
        
        return fromDateCalendar.after(toDateCalendar);
    }
    
    /**
     * Get Number of days between
     * 
     * 
     * @param dateFrom Date From
     * @param dateTo Date To
     * 
     * @return Number of days between
     */
    public static long noOfDaysBetween(Date dateFrom,Date dateTo) {
        
        if(dateFrom == null || dateTo == null) {
            
            return -1;
        }
        
        long timeInDays = -1;
        
        if(dateTo.after(dateFrom)) {
            
            long timeInMill = dateTo.getTime() - dateFrom.getTime();
            timeInDays = Math.round(( timeInMill/(24*60*60*1000) ));
            
        } else {

            long timeInMill = dateFrom.getTime() - dateTo.getTime();
            timeInDays = Math.round(( timeInMill/(24*60*60*1000) ));
            
        }
        
        return timeInDays;
    }
    
    /**
     * Parse Date
     * 
     * @return Date
     * @param date Date representation in string
     */
    public static Date parseDate(String date) {
        try {
            return parserDateFormatter.parse(date);            
        } catch(ParseException ex) {
            throw new TrafficException(ex);
        }
    }    
}