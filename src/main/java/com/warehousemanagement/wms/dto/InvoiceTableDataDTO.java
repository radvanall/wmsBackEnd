package com.warehousemanagement.wms.dto;

import java.util.Date;

public class InvoiceTableDataDTO {
    private  Integer id;
    private String image;
    private String customer;
    private Date date;
    private String operator;
    private Double price;
    private Boolean shipped;

    public InvoiceTableDataDTO() {
    }

    public InvoiceTableDataDTO(Integer id,
                               String image,
                               String customer,
                               Date date,
                               String operator,
                               Double price) {
        this.id = id;
        this.image = image;
        this.customer = customer;
        this.date = date;
        this.operator = operator;
        this.price = price;
    }

    public InvoiceTableDataDTO(Integer id,
                               String image,
                               String customer,
                               Date date, String operator,
                               Double price, Boolean shipped) {
        this.id = id;
        this.image = image;
        this.customer = customer;
        this.date = date;
        this.operator = operator;
        this.price = price;
        this.shipped = shipped;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Boolean getShipped() {
        return shipped;
    }

    public void setShipped(Boolean shipped) {
        this.shipped = shipped;
    }
}
