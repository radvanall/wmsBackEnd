package com.warehousemanagement.wms.dto;

import java.util.Date;
import java.util.List;

public class InvoiceDTO {
    private Integer operatorId;
    private Integer clientId;
    private Date date;
    private Boolean shipped;
    private String address;
    private List<InvoiceTableDTO> invoiceTableDTOS;

    public InvoiceDTO() {
    }

    public InvoiceDTO(Integer operatorId,
                      Integer clientId,
                      Date date,
                      Boolean shipped,
                      String address,
                      List<InvoiceTableDTO> invoiceTableDTOS) {
        this.operatorId = operatorId;
        this.clientId = clientId;
        this.date = date;
        this.invoiceTableDTOS = invoiceTableDTOS;
        this.shipped=shipped;
        this.address=address;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<InvoiceTableDTO> getInvoiceTableDTOS() {
        return invoiceTableDTOS;
    }

    public void setInvoiceTableDTOS(List<InvoiceTableDTO> invoiceTableDTOS) {
        this.invoiceTableDTOS = invoiceTableDTOS;
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

    @Override
    public String toString() {
        return "InvoiceDTO{" +
                "operatorId=" + operatorId +
                ", clientId=" + clientId +
                ", date=" + date +
                ", shipped=" + shipped +
                ", address='" + address + '\'' +
                ", invoiceTableDTOS=" + invoiceTableDTOS +
                '}';
    }
}
