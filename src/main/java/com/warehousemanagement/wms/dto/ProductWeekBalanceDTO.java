package com.warehousemanagement.wms.dto;

public class ProductWeekBalanceDTO {
    private String name;
    private Double sum;

    public ProductWeekBalanceDTO() {
    }

    public ProductWeekBalanceDTO(String name, Double sum) {
        this.name = name;
        this.sum = sum;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }
}
