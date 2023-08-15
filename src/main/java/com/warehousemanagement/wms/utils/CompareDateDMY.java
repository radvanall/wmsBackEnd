package com.warehousemanagement.wms.utils;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class CompareDateDMY {
    public static boolean compareDates(Date firstDate, Date secondDate){
        Calendar firstCalendar=Calendar.getInstance();
        firstCalendar.setTime(firstDate);

        Calendar secondCalendar=Calendar.getInstance();
        secondCalendar.setTime(secondDate);

        boolean sameDay = firstCalendar.get(Calendar.DAY_OF_MONTH) == secondCalendar.get(Calendar.DAY_OF_MONTH);
        boolean sameMonth = firstCalendar.get(Calendar.MONTH) == secondCalendar.get(Calendar.MONTH);
        boolean sameYear = firstCalendar.get(Calendar.YEAR) == secondCalendar.get(Calendar.YEAR);
         if(sameDay && sameMonth && sameYear) return  true;
        else return false;

    }
}
