package com.warehousemanagement.wms.dto;

public class ProviderSalesAndAcquisitionsDTO {
    private Integer id;
    private String image;
    private String name;
    private Double sales;
    private Double acquisitions;

    public ProviderSalesAndAcquisitionsDTO() {
    }

    public ProviderSalesAndAcquisitionsDTO(Integer id, String image, String name, Double sales, Double acquisitions) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.sales = sales;
        this.acquisitions = acquisitions;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSales() {
        return sales;
    }

    public void setSales(Double sales) {
        this.sales = sales;
    }

    public Double getAcquisitions() {
        return acquisitions;
    }

    public void setAcquisitions(Double acquisitions) {
        this.acquisitions = acquisitions;
    }
    public Double getBalance(){
        return  sales-acquisitions;
    }
}
