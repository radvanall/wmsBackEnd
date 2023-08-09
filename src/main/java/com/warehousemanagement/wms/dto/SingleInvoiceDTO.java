package com.warehousemanagement.wms.dto;

import java.util.Date;
import java.util.List;

public class SingleInvoiceDTO {
    private Integer id;
    private String operatorName;
    private String clientName;
    private String image;
    private String address;
    private Boolean shipped;
    private Date date;
    private Double totalPrice;
    private List<OrderDTO> orderDTOS;

    public SingleInvoiceDTO() {
    }

    public SingleInvoiceDTO(Integer id,
                            String operatorName,
                            String clientName,
                            String image,
                            Boolean shipped,
                            String address,
                            Date date,
                            Double totalPrice,
                            List<OrderDTO> orderDTOS) {
        this.id = id;
        this.operatorName = operatorName;
        this.clientName=clientName;
        this.image=image;
        this.shipped = shipped;
        this.address=address;
        this.date = date;
        this.totalPrice = totalPrice;
        this.orderDTOS = orderDTOS;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public Boolean getShipped() {
        return shipped;
    }

    public void setShipped(Boolean shipped) {
        this.shipped = shipped;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<OrderDTO> getOrderDTOS() {
        return orderDTOS;
    }

    public void setOrderDTOS(List<OrderDTO> orderDTOS) {
        this.orderDTOS = orderDTOS;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
