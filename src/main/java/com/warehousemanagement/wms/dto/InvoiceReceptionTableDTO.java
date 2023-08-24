package com.warehousemanagement.wms.dto;

import java.util.Date;

public class InvoiceReceptionTableDTO {
    private Integer id;
    private Boolean validated;
    private Date dateOfCreation;
    private Date dateOfValidation;
    private String validatedBy;
    private String createdBy;
    private Double totalBuyingPrice;
    private Double totalSellingPrice;
    private String provider;

    public InvoiceReceptionTableDTO() {
    }

    public InvoiceReceptionTableDTO(Integer id, Date dateOfCreation,
                                    String createdBy, Double totalBuyingPrice,
                                    Double totalSellingPrice, String provider) {
        this.id = id;
        this.dateOfCreation = dateOfCreation;
        this.createdBy = createdBy;
        this.totalBuyingPrice = totalBuyingPrice;
        this.totalSellingPrice = totalSellingPrice;
        this.provider = provider;
    }

    public InvoiceReceptionTableDTO(Integer id,
                                    Boolean validated,
                                    Date dateOfCreation,
                                    Date dateOfValidation,
                                    String createdBy,
                                    Double totalBuyingPrice,
                                    Double totalSellingPrice,
                                    String provider) {
        this.id = id;
        this.validated = validated;
        this.dateOfCreation = dateOfCreation;
        this.dateOfValidation = dateOfValidation;
        this.createdBy = createdBy;
        this.totalBuyingPrice = totalBuyingPrice;
        this.totalSellingPrice = totalSellingPrice;
        this.provider = provider;
    }

    public InvoiceReceptionTableDTO(Integer id,
                                    Boolean validated,
                                    Date dateOfCreation,
                                    Date dateOfValidation,
                                    String validatedBy,
                                    String createdBy,
                                    Double totalBuyingPrice,
                                    Double totalSellingPrice,
                                    String provider) {
        this.id = id;
        this.validated = validated;
        this.dateOfCreation = dateOfCreation;
        this.dateOfValidation = dateOfValidation;
        this.validatedBy = validatedBy;
        this.createdBy = createdBy;
        this.totalBuyingPrice = totalBuyingPrice;
        this.totalSellingPrice = totalSellingPrice;
        this.provider = provider;
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

}
