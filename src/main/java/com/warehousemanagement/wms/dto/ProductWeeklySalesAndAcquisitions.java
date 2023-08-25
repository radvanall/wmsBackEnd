package com.warehousemanagement.wms.dto;

import java.util.List;

public class ProductWeeklySalesAndAcquisitions {
    private Integer id;
    private String name;
    private String avatar;
    private List<SaleAndAcquisitionDTO> balance;

    public ProductWeeklySalesAndAcquisitions() {
    }

    public ProductWeeklySalesAndAcquisitions(Integer id, String name,
                                             String avatar, List<SaleAndAcquisitionDTO> balance) {
        this.id = id;
        this.name = name;
        this.avatar = avatar;
        this.balance = balance;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public List<SaleAndAcquisitionDTO> getBalance() {
        return balance;
    }
    public void addBalance(SaleAndAcquisitionDTO newBalance){
        this.balance.add(newBalance);
    }

    public void setBalance(List<SaleAndAcquisitionDTO> balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "ProductWeeklySalesAndAcquisitions{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", avatar='" + avatar + '\'' +
                ", balance=" + balance.stream().mapToDouble(obj->obj.getBalance()).sum() +
                '}';
    }
}
