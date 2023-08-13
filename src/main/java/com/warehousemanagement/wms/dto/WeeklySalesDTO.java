package com.warehousemanagement.wms.dto;

import java.util.Date;

public class WeeklySalesDTO {
    private Date weekStart;
    private Double totalSales;

    public WeeklySalesDTO() {
    }

    public WeeklySalesDTO(Date weekStart, Double totalSales) {
        this.weekStart = weekStart;
        this.totalSales = totalSales;
    }

    public Date getWeekStart() {
        return weekStart;
    }

    public void setWeekStart(Date weekStart) {
        this.weekStart = weekStart;
    }

    public Double getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(Double totalSales) {
        this.totalSales = totalSales;
    }
}