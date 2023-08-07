package com.warehousemanagement.wms.dto;

public class InvoiceTableDTO {
    private Integer quantity;
    private Integer stockId;

    public InvoiceTableDTO() {
    }

    public InvoiceTableDTO(Integer quantity, Integer stockId) {
        this.quantity = quantity;
        this.stockId = stockId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getStockId() {
        return stockId;
    }

    public void setStockId(Integer stockId) {
        this.stockId = stockId;
    }
}
