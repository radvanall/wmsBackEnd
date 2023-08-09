package com.warehousemanagement.wms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
    private Double totalPrice;
//    @OneToMany(fetch = FetchType.LAZY)
//    @JoinColumn(name="invoice_id",referencedColumnName ="id")
    @OneToMany(fetch = FetchType.LAZY,cascade = {CascadeType.ALL} ,mappedBy="invoice")
    private List<Order> order;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="customer_id",referencedColumnName = "id")
    private Customer customer;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="operator_id",referencedColumnName = "id")
    private Operator operator;

    public Invoice() {
        this.date = new Date(System.currentTimeMillis());
    }

    public Invoice( String address, List<Order> order,Double totalPrice,Boolean shipped,Operator operator,Date date,Customer customer) {
        this.date = date;
        this.address = address;
        this.order = order;
        this.totalPrice=totalPrice;
        this.shipped=shipped;
        this.operator=operator;
        this.customer=customer;
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

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public void copyInvoice(Invoice invoice) {
        this.date = invoice.date;
        this.address = invoice.address;
        this.order = invoice.order;
        this.totalPrice=invoice.totalPrice;
        this.shipped=invoice.shipped;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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
