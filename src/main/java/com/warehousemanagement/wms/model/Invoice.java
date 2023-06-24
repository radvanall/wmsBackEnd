package com.warehousemanagement.wms.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    private Boolean shipped;
    private String address;
    private Long totalPrice;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="invoice_id",referencedColumnName ="id")
    private List<Order> order;

    public Invoice() {
        this.date = new Date(System.currentTimeMillis());
    }

    public Invoice( String address, List<Order> order,Long totalPrice,Boolean shipped) {
        this.date = new Date(System.currentTimeMillis());
        this.address = address;
        this.order = order;
        this.totalPrice=totalPrice;
        this.shipped=shipped;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Order> getOrder() {
        return order;
    }

    public void setOrder(List<Order> order) {
        this.order = order;
    }

    public Boolean getShipped() {
        return shipped;
    }

    public void setShipped(Boolean shipped) {
        this.shipped = shipped;

    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void copyInvoice(Invoice invoice) {
        this.date = invoice.date;
        this.address = invoice.address;
        this.order = invoice.order;
        this.totalPrice=invoice.totalPrice;
        this.shipped=invoice.shipped;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", date=" + date +
                ", shipped=" + shipped +
                ", address='" + address + '\'' +
                ", totalPrice=" + totalPrice +
                ", order=" + order +
                '}';
    }
}
