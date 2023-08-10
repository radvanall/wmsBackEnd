package com.warehousemanagement.wms.dto;

public class NewOrderDTO {
    private Integer stockId;
    private Integer quantity;
    private Integer invoiceId;

    public NewOrderDTO() {
    }

    public NewOrderDTO(Integer stockId, Integer quantity, Integer invoiceId) {
        this.stockId = stockId;
        this.quantity = quantity;
        this.invoiceId = invoiceId;
    }

    public Integer getStockId() {
        return stockId;
    }

    public void setStockId(Integer stockId) {
        this.stockId = stockId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Integer invoiceId) {
        this.invoiceId = invoiceId;
    }

    @Override
    public String toString() {
        return "NewOrderDTO{" +
                "stockId=" + stockId +
                ", quantity=" + quantity +
                ", invoiceId=" + invoiceId +
                '}';
    }
}
