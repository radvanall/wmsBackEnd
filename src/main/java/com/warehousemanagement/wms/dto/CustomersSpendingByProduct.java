package com.warehousemanagement.wms.dto;

public class CustomersSpendingByProduct {
    private Integer id;
    private String name;
    private String image;
    private Double moneySpend;

    public CustomersSpendingByProduct() {
    }

    public CustomersSpendingByProduct(Integer id, String name, String image,Double moneySpend) {
        this.id = id;
        this.name = name;
        this.image=image;
        this.moneySpend = moneySpend;
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

    public Double getMoneySpend() {
        return moneySpend;
    }

    public void setMoneySpend(Double moneySpend) {
        this.moneySpend = moneySpend;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
