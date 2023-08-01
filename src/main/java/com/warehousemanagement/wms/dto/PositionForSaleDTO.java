package com.warehousemanagement.wms.dto;


import java.util.List;

public class PositionForSaleDTO {
    private Integer id;
    private String productName;
    private String productImg;
    private String unity;
    private List<StockForSale> stocks;

    public PositionForSaleDTO() {
    }

    public PositionForSaleDTO(Integer id,
                              String productName,
                              String productImg,
                              String unity,
                              List<StockForSale> stocks) {
        this.id = id;
        this.productName = productName;
        this.productImg = productImg;
        this.unity = unity;
        this.stocks = stocks;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getUnity() {
        return unity;
    }

    public void setUnity(String unity) {
        this.unity = unity;
    }

    public List<StockForSale> getStocks() {
        return stocks;
    }

    public void setStocks(List<StockForSale> stocks) {
        this.stocks = stocks;
    }
}
