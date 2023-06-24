package com.warehousemanagement.wms.dto;

public class InvoiceStockDTO {
    private Integer id;
    private Integer productId;
    private Integer stockQuantity;
    private Double buyingPrice;
    private Double sellingPrice;

    public InvoiceStockDTO() {
    }

    public InvoiceStockDTO(Integer id, Integer productId, Integer stockQuantity, Double buyingPrice, Double sellingPrice) {
        this.id = id;
        this.productId = productId;
        this.stockQuantity = stockQuantity;
        this.buyingPrice = buyingPrice;
        this.sellingPrice = sellingPrice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public Double getBuyingPrice() {
        return buyingPrice;
    }

    public void setBuyingPrice(Double buyingPrice) {
        this.buyingPrice = buyingPrice;
    }

    public Double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    @Override
    public String toString() {
        return "InvoiceStockDTO{" +
                "id=" + id +
                ", productId=" + productId +
                ", stockQuantity=" + stockQuantity +
                ", buyingPrice=" + buyingPrice +
                ", sellingPrice=" + sellingPrice +
                '}';
    }
}
