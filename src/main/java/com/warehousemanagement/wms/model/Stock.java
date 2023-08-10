package com.warehousemanagement.wms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Entity
@Table
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer stockQuantity;
    private Integer remainingQuantity;
    private Double buyingPrice;
    private Double sellingPrice;
    private String state;

    @OneToMany(fetch = FetchType.LAZY ,mappedBy="stock")
//    @JoinColumn(name = "stock_id",referencedColumnName = "id")
    private List<Order> order;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "position_id",referencedColumnName = "id")
    private Position position;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invoice_reception_id",referencedColumnName = "id")
    InvoiceReception invoiceReception;
    public Stock() {

    }

    public Stock( Integer stockQuantity, Double buyingPrice, Double sellingPrice, List<Order> order, Position position) {
        this.stockQuantity = stockQuantity;
        this.buyingPrice = buyingPrice;
        this.sellingPrice = sellingPrice;
        this.order = order;
        this.position = position;
    }

    public Stock( Integer stockQuantity, Double buyingPrice, Double sellingPrice, Position position) {
        this.stockQuantity = stockQuantity;
        this.remainingQuantity=stockQuantity;
        this.buyingPrice = buyingPrice;
        this.sellingPrice = sellingPrice;
        this.position = position;
        this.state="unvalidated";
    }

    public Stock(Integer stockQuantity, Double sellingPrice, Double buyingPrice, List<Order> order) {
        this.stockQuantity = stockQuantity;
        this.sellingPrice = sellingPrice;
        this.buyingPrice=buyingPrice;
        this.order=order;
    }

    public Stock(Integer stockQuantity, Double sellingPrice, Double buyingPrice) {
        this.stockQuantity = stockQuantity;
        this.sellingPrice = sellingPrice;
        this.buyingPrice=buyingPrice;
    }

    public Stock(Integer id,
                 Integer stockQuantity,
                 Integer remainingQuantity,
                 Double buyingPrice,
                 Double sellingPrice,
                 String state,
                 List<Order> order,
                 Position position) {
        this.id = id;
        this.stockQuantity = stockQuantity;
        this.remainingQuantity = remainingQuantity;
        this.buyingPrice = buyingPrice;
        this.sellingPrice = sellingPrice;
        this.state = state;
        this.order = order;
        this.position = position;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }


    public Double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public Double getBuyingPrice() {
        return buyingPrice;
    }

    public void setBuyingPrice(Double buyingPrice) {
        this.buyingPrice = buyingPrice;
    }

    public List<Order> getOrder() {
        return order;
    }

    public void setOrder(List<Order> order) {
        this.order = order;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void copyStock(Stock stock) {
        this.stockQuantity = stock.stockQuantity;
        this.sellingPrice = stock.sellingPrice;
        this.buyingPrice=stock.buyingPrice;
    }

    public Integer getRemainingQuantity() {
        return remainingQuantity;
    }

    public void setRemainingQuantity(Integer remainingQuantity) {
        this.remainingQuantity = remainingQuantity;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public InvoiceReception getInvoiceReception() {
        return invoiceReception;
    }

    public void setInvoiceReception(InvoiceReception invoiceReception) {
        this.invoiceReception = invoiceReception;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "id=" + id +
                ", stockQuantity=" + stockQuantity +
                ", remainingQuantity=" + remainingQuantity +
                ", buyingPrice=" + buyingPrice +
                ", sellingPrice=" + sellingPrice +
                ", state='" + state + '\'' +
                ", order=" + order +
                ", position=" + position.getName() +
                ", invoiceReception=" + invoiceReception +
                '}';
    }
}
