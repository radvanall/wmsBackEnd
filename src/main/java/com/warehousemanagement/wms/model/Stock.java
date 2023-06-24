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
    private Double buyingPrice;
    private Double sellingPrice;
    @OneToMany(fetch = FetchType.LAZY,cascade = {CascadeType.ALL})
    @JoinColumn(name = "stock_id",referencedColumnName = "id")
    private List<Order> order;

    @ManyToOne(fetch = FetchType.EAGER,cascade = {CascadeType.ALL})
    @JoinColumn(name = "position_id",referencedColumnName = "id")
    private Position position;


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
        this.buyingPrice = buyingPrice;
        this.sellingPrice = sellingPrice;
        this.position = position;
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

    @Override
    public String toString() {
        return "Stock{" +
                "id=" + id +
                ", stockQuantity=" + stockQuantity +
                ", buyingPrice=" + buyingPrice +
                ", sellingPrice=" + sellingPrice +
                ", order=" + order +
//                ", position=" + position +
                '}';
    }
}
