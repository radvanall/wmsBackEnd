package com.warehousemanagement.wms.dto;

import java.util.Date;

public class OperatorInvoiceDTO {
    private Integer id;
    private Date date;
    private String customer;
    private Boolean shipped;
    private Double totalPrice;

    public OperatorInvoiceDTO() {
    }

    public OperatorInvoiceDTO(Integer id, Date date,
                              String customer,
                              Boolean shipped,
                              Double totalPrice) {
        this.id = id;
        this.date = date;
        this.customer = customer;
        this.shipped = shipped;
        this.totalPrice = totalPrice;
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

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public Boolean getShipped() {
        return shipped;
    }

    public void setShipped(Boolean shipped) {
        this.shipped = shipped;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
