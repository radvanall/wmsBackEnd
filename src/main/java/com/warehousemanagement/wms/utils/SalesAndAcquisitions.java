package com.warehousemanagement.wms.utils;

import com.warehousemanagement.wms.dto.SaleAndAcquisitionDTO;
import com.warehousemanagement.wms.dto.WeeklySalesDTO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SalesAndAcquisitions {
    public static List<SaleAndAcquisitionDTO> getSalesAndAcquisitions(Calendar monthsAgo,
                                                                     List<WeeklySalesDTO> salesDTOS,
                                                                     List<WeeklySalesDTO> acquisitionsDTOS){
        Calendar currentDate = Calendar.getInstance();
        List<SaleAndAcquisitionDTO> saleAndAcquisitionDTOS=new ArrayList<>();
        while (currentDate.after(monthsAgo)) {
            Calendar weekStart = (Calendar) currentDate.clone();
            weekStart.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            weekStart.set(Calendar.HOUR_OF_DAY, 0);
            weekStart.set(Calendar.MINUTE, 0);
            weekStart.set(Calendar.SECOND, 0);
            Calendar weekEnd = (Calendar) weekStart.clone();
            weekEnd.add(Calendar.DAY_OF_WEEK, 6);
            double sumSales = FindSumForDate.findSumForDate(salesDTOS, weekStart.getTime(),weekEnd.getTime());
            double sumAcquisitions = FindSumForDate.findSumForDate(acquisitionsDTOS, weekStart.getTime(),weekEnd.getTime());
            saleAndAcquisitionDTOS.add(new SaleAndAcquisitionDTO(weekStart.getTime(),sumSales,sumAcquisitions));
            currentDate.add(Calendar.WEEK_OF_YEAR, -1);
        }
        return saleAndAcquisitionDTOS;
    }
    public static List<SaleAndAcquisitionDTO> getValidSalesAndAcquisitions(Calendar monthsAgo,
                                                                      List<WeeklySalesDTO> salesDTOS,
                                                                      List<WeeklySalesDTO> acquisitionsDTOS){
        Calendar currentDate = Calendar.getInstance();
        List<SaleAndAcquisitionDTO> saleAndAcquisitionDTOS=new ArrayList<>();
        while (currentDate.after(monthsAgo)) {
            Calendar weekStart = (Calendar) currentDate.clone();
            weekStart.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            weekStart.set(Calendar.HOUR_OF_DAY, 0);
            weekStart.set(Calendar.MINUTE, 0);
            weekStart.set(Calendar.SECOND, 0);
            Calendar weekEnd = (Calendar) weekStart.clone();
            weekEnd.add(Calendar.DAY_OF_WEEK, 6);
            double sumSales = FindSumForDate.findSumForDate(salesDTOS, weekStart.getTime(),weekEnd.getTime());
            double sumAcquisitions = FindSumForDate.findSumForDate(acquisitionsDTOS, weekStart.getTime(),weekEnd.getTime());
            if(sumAcquisitions!=0||sumSales!=0)
            saleAndAcquisitionDTOS.add(new SaleAndAcquisitionDTO(weekStart.getTime(),sumSales,sumAcquisitions));
            currentDate.add(Calendar.WEEK_OF_YEAR, -1);
        }
        return saleAndAcquisitionDTOS;
    }

    public static List<WeeklySalesDTO> getWeeklyBalance(Calendar monthsAgo,
                                                                      List<WeeklySalesDTO> salesDTOS,
                                                                      List<WeeklySalesDTO> acquisitionsDTOS){
        Calendar currentDate = Calendar.getInstance();
        List<WeeklySalesDTO> weeklyBalance=new ArrayList<>();
        while (currentDate.after(monthsAgo)) {
            Calendar weekStart = (Calendar) currentDate.clone();
            weekStart.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            weekStart.set(Calendar.HOUR_OF_DAY, 0);
            weekStart.set(Calendar.MINUTE, 0);
            weekStart.set(Calendar.SECOND, 0);
            Calendar weekEnd = (Calendar) weekStart.clone();
            weekEnd.add(Calendar.DAY_OF_WEEK, 6);
            double sumSales = FindSumForDate.findSumForDate(salesDTOS, weekStart.getTime(),weekEnd.getTime());
            double sumAcquisitions = FindSumForDate.findSumForDate(acquisitionsDTOS, weekStart.getTime(),weekEnd.getTime());
            weeklyBalance.add(new WeeklySalesDTO(weekStart.getTime(),sumSales-sumAcquisitions));
            currentDate.add(Calendar.WEEK_OF_YEAR, -1);
        }
        return weeklyBalance;
    }
}
