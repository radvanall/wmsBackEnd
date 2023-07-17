package com.warehousemanagement.wms.dto;

import com.warehousemanagement.wms.model.Stock;

import java.util.Date;
import java.util.List;

public class InvoiceReceptionDTO {
    private Integer id;
    private Boolean validated;
    private Date dateOfCreation;
    private Date dateOfValidation;
    private String validatedBy;
    private String createdBy;
    private Double totalBuyingPrice;
    private Double totalSellingPrice;
    private String provider;
    private Integer providerId;
    private String image;
    private Integer numberOfStocks;
    private Integer numberOfProducts;
    private List<Stock> stocks;

    public InvoiceReceptionDTO() {
    }

    public InvoiceReceptionDTO(Integer id,
                               Boolean validated,
                               Date dateOfCreation,
                               Date dateOfValidation,
                               String validatedBy,
                               String createdBy,
                               Double totalBuyingPrice,
                               Double totalSellingPrice,
                               String provider,
                               Integer providerId,
                               String image,
                               Integer numberOfStocks,
                               Integer numberOfProducts,
                               List<Stock>stocks) {
        this.id = id;
        this.validated = validated;
        this.dateOfCreation = dateOfCreation;
        this.dateOfValidation = dateOfValidation;
        this.validatedBy = validatedBy;
        this.createdBy = createdBy;
        this.totalBuyingPrice = totalBuyingPrice;
        this.totalSellingPrice = totalSellingPrice;
        this.provider = provider;
        this.providerId=providerId;
        this.image = image;
        this.numberOfStocks = numberOfStocks;
        this.numberOfProducts = numberOfProducts;
        this.stocks=stocks;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getValidated() {
        return validated;
    }

    public void setValidated(Boolean validated) {
        this.validated = validated;
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

    public String getValidatedBy() {
        return validatedBy;
    }

    public void setValidatedBy(String validatedBy) {
        this.validatedBy = validatedBy;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
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

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getNumberOfStocks() {
        return numberOfStocks;
    }

    public void setNumberOfStocks(Integer numberOfStocks) {
        this.numberOfStocks = numberOfStocks;
    }

    public Integer getNumberOfProducts() {
        return numberOfProducts;
    }

    public void setNumberOfProducts(Integer numberOfProducts) {
        this.numberOfProducts = numberOfProducts;
    }

    public Integer getProviderId() {
        return providerId;
    }

    public void setProviderId(Integer providerId) {
        this.providerId = providerId;
    }

    public List<Stock> getStocks() {
        return stocks;
    }

    public void setStocks(List<Stock> stocks) {
        this.stocks = stocks;
    }
}
