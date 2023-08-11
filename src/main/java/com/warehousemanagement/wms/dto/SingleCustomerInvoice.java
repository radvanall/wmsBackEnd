package com.warehousemanagement.wms.dto;

import java.util.Date;

public class SingleCustomerInvoice {
    private Integer id;
    private Date date;
    private Boolean shipped;
    private String address;
    private Double totalPrice;
    private String operatorName;

    public SingleCustomerInvoice() {
    }

    public SingleCustomerInvoice(Integer id,
                                 Date date,
                                 Boolean shipped,
                                 String address,
                                 Double totalPrice,
                                 String operatorName) {
        this.id = id;
        this.date = date;
        this.shipped = shipped;
        this.address = address;
        this.totalPrice = totalPrice;
        this.operatorName = operatorName;
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

    public Boolean getShipped() {
        return shipped;
    }

    public void setShipped(Boolean shipped) {
        this.shipped = shipped;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }
}
