package com.warehousemanagement.wms.dto;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

public class StockCardDTO {
    private Integer id;
    private String position;
    private String positionImg;
    private String provider;
    private String providerImg;
    private Integer quantity;
    private Integer remainingQuantity;
    private String unity;
    private Double buyingPrice;
    private Double sellingPrice;
    private Double totalBuyingPrice;
    private Double totalSellingPrice;
    private Date dateOfCreation;
    private Date dateOfValidation;
    private String state;
    private String categoryName;
    private String subcategoryName;

    private Integer invoiceId;

    public StockCardDTO(Integer id,
                        String position,
                        String positionImg,
                        String provider,
                        String providerImg,
                        Integer quantity,
                        Integer remainingQuantity,
                        String unity,
                        Double buyingPrice,
                        Double sellingPrice,
                        Double totalBuyingPrice,
                        Double totalSellingPrice,
                        Date dateOfCreation,
                        Date dateOfValidation,
                        String state,
                        Integer invoiceId) {
        this.id = id;
        this.position = position;
        this.positionImg = positionImg;
        this.provider = provider;
        this.providerImg = providerImg;
        this.quantity = quantity;
        this.remainingQuantity = remainingQuantity;
        this.unity=unity;
        this.buyingPrice = buyingPrice;
        this.sellingPrice = sellingPrice;
        this.totalBuyingPrice = totalBuyingPrice;
        this.totalSellingPrice = totalSellingPrice;
        this.dateOfCreation = dateOfCreation;
        this.dateOfValidation = dateOfValidation;
        this.state = state;
        this.invoiceId = invoiceId;
    }

    public StockCardDTO(Integer id,
                        String position,
                        String positionImg,
                        String provider,
                        String providerImg,
                        Integer quantity,
                        Integer remainingQuantity,
                        String unity,
                        Double buyingPrice,
                        Double sellingPrice,
                        Double totalBuyingPrice,
                        Double totalSellingPrice,
                        Date dateOfCreation,
                        Date dateOfValidation,
                        String state,
                        String categoryName,
                        String subcategoryName,
                        Integer invoiceId) {
        this.id = id;
        this.position = position;
        this.positionImg = positionImg;
        this.provider = provider;
        this.providerImg = providerImg;
        this.quantity = quantity;
        this.remainingQuantity = remainingQuantity;
        this.unity = unity;
        this.buyingPrice = buyingPrice;
        this.sellingPrice = sellingPrice;
        this.totalBuyingPrice = totalBuyingPrice;
        this.totalSellingPrice = totalSellingPrice;
        this.dateOfCreation = dateOfCreation;
        this.dateOfValidation = dateOfValidation;
        this.state = state;
        this.categoryName = categoryName;
        this.subcategoryName = subcategoryName;
        this.invoiceId = invoiceId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPositionImg() {
        return positionImg;
    }

    public void setPositionImg(String positionImg) {
        this.positionImg = positionImg;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getProviderImg() {
        return providerImg;
    }

    public void setProviderImg(String providerImg) {
        this.providerImg = providerImg;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getRemainingQuantity() {
        return remainingQuantity;
    }

    public void setRemainingQuantity(Integer remainingQuantity) {
        this.remainingQuantity = remainingQuantity;
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

    public Double getTotalBuyingPrice() {
        return totalBuyingPrice;
    }

    public void setTotalBuyingPrice(Double totalBuyingPrice) {
        this.totalBuyingPrice = totalBuyingPrice;
    }

    public Double getTotalSellingPrice() {
        return totalSellingPrice;
    }

    public void setTotalSellingPrice(Double totalSellingPrice) {
        this.totalSellingPrice = totalSellingPrice;
    }

    public Date getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(Date dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public Date getDateOfValidation() {
        return dateOfValidation;
    }

    public void setDateOfValidation(Date dateOfValidation) {
        this.dateOfValidation = dateOfValidation;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Integer invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getUnity() {
        return unity;
    }

    public void setUnity(String unity) {
        this.unity = unity;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getSubcategoryName() {
        return subcategoryName;
    }

    public void setSubcategoryName(String subcategoryName) {
        this.subcategoryName = subcategoryName;
    }
}
