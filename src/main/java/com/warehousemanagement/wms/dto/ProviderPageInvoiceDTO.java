package com.warehousemanagement.wms.dto;

import java.util.Date;

public class ProviderPageInvoiceDTO {
    private Integer id;
    private Date date;
    private String administrator;
    private Double price;
    private Boolean validated;

    public ProviderPageInvoiceDTO() {
    }

    public ProviderPageInvoiceDTO(Integer id,
                                  Date date,
                                  String administrator,
                                  Double price,
                                  Boolean validated) {
        this.id = id;
        this.date = date;
        this.administrator = administrator;
        this.price = price;
        this.validated=validated;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAdministrator() {
        return administrator;
    }

    public void setAdministrator(String administrator) {
        this.administrator = administrator;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Boolean getValidated() {
        return validated;
    }

    public void setValidated(Boolean validated) {
        this.validated = validated;
    }
}
