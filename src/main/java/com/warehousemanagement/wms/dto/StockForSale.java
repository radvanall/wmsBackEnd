package com.warehousemanagement.wms.dto;

public class StockForSale {
    private Integer id;
    private Integer positionId;
    private Double sellingPrice;
    private Integer remainingQuantity;
    private String state;

    public StockForSale() {
    }

    public StockForSale(Integer id,
                        Integer positionId,
                        Double sellingPrice,
                        Integer remainingQuantity,
                        String state) {
        this.id = id;
        this.positionId = positionId;
        this.sellingPrice = sellingPrice;
        this.remainingQuantity = remainingQuantity;
        this.state = state;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    public Double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public Integer getRemainingQuantity() {
        return remainingQuantity;
    }

    public void setRemainingQuantity(Integer remainingQuantity) {
        this.remainingQuantity = remainingQuantity;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
