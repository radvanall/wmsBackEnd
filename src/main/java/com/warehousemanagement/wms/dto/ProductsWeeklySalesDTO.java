package com.warehousemanagement.wms.dto;

import java.util.List;
import java.util.stream.Collectors;

public class ProductsWeeklySalesDTO {
    private Integer id;
    private String name;
    private String avatar;
    private List<WeeklySalesDTO> sales;

    public ProductsWeeklySalesDTO() {
    }

    public ProductsWeeklySalesDTO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public ProductsWeeklySalesDTO(Integer id, String name, List<WeeklySalesDTO> sales) {
        this.id = id;
        this.name = name;
        this.sales = sales;
    }

    public ProductsWeeklySalesDTO(Integer id, String name, String avatar, List<WeeklySalesDTO> sales) {
        this.id = id;
        this.name = name;
        this.avatar = avatar;
        this.sales = sales;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<WeeklySalesDTO> getSales() {
        return sales;
    }

    public void setSales(List<WeeklySalesDTO> sales) {
        this.sales = sales;
    }
    public void addSale(WeeklySalesDTO sale){
        this.sales.add(sale);
    }

}
