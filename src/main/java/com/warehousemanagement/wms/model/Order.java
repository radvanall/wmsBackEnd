package com.warehousemanagement.wms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;

@Entity
@Table
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer quantity;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="stock_id",referencedColumnName = "id")
    private Stock stock;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="invoice_id",referencedColumnName = "id")
    private Invoice invoice;

    public Order() {
    }

    public Order(Integer quantity, Stock stock) {
        this.quantity = quantity;
        this.stock = stock;
    }
    public Order(Integer quantity, Stock stock, Invoice invoice) {
        this.quantity = quantity;
        this.stock = stock;
        this.invoice=invoice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public void copyOrder(Order order) {
        this.quantity=order.quantity;

    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", stock=" + stock.getId() +
                ", invoice=" + invoice.getId() +
                '}';
    }
}
