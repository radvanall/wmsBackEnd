package com.warehousemanagement.wms.dto;

import java.util.Date;

public class SaleAndAcquisitionDTO {
    private Date weekStart;
    private Double totalSales;
    private Double totalAcquisitions;

    public SaleAndAcquisitionDTO() {
    }

    public SaleAndAcquisitionDTO(Date weekStart, Double totalSales, Double totalAcquisitions) {
        this.weekStart = weekStart;
        this.totalSales = totalSales;
        this.totalAcquisitions = totalAcquisitions;
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

    public Double getTotalAcquisitions() {
        return totalAcquisitions;
    }

    public void setTotalAcquisitions(Double totalAcquisitions) {
        this.totalAcquisitions = totalAcquisitions;
    }

    public double getBalance(){
        return totalSales-totalAcquisitions;
    }

}
