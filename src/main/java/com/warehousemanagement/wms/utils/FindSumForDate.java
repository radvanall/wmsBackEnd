package com.warehousemanagement.wms.utils;

import com.warehousemanagement.wms.dto.WeeklySalesDTO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class FindSumForDate {
    public static double findSumForDate(List<WeeklySalesDTO> dtoList, Date weekStart,Date weekEnd)  {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
   try {
       for (WeeklySalesDTO dto : dtoList) {
           String formattedWeekStart = dateFormat.format(weekStart);
           String formattedWeekEnd = dateFormat.format(weekEnd);
           System.out.println("dtoDate" + dto.getWeekStart());
           System.out.println("weekStart" + weekStart.getTime());
           System.out.println("weekEnd" + weekEnd.getTime());
           if (dto.getWeekStart().compareTo(dateFormat.parse(formattedWeekStart)) >= 0 &&
                   dto.getWeekStart().compareTo(dateFormat.parse(formattedWeekEnd)) <= 0) {
               return dto.getTotalSales();
           }
       }
   }catch (ParseException e){
       System.out.println(e.getMessage());
   }
        return 0.0;
    }
}
