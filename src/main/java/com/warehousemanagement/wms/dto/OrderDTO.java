package com.warehousemanagement.wms.dto;

public class OrderDTO {
    private Integer id;
    private Integer stockId;
    private Integer productId;
    private String productName;
    private String productImg;
    private Integer quantity;
    private Double price;
    private Double totalPrice;
    private String unity;

    public OrderDTO() {
    }

    public OrderDTO(Integer id,
                    Integer stockId,
                    Integer productId,
                    String productName,
                    String productImg,
                    Integer quantity,
                    Double price,
                    Double totalPrice,
                    String unity) {
        this.id = id;
        this.stockId = stockId;
        this.productId = productId;
        this.productName = productName;
        this.productImg = productImg;
        this.quantity = quantity;
        this.price = price;
        this.totalPrice = totalPrice;
        this.unity = unity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStockId() {
        return stockId;
    }

    public void setStockId(Integer stockId) {
        this.stockId = stockId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getUnity() {
        return unity;
    }

    public void setUnity(String unity) {
        this.unity = unity;
    }
}
