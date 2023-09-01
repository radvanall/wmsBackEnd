package com.warehousemanagement.wms.dto;

public class RemainingStock {
    private Integer id;
    private String image;
    private String name;
    private Long quantity;
    private String unit;

    public RemainingStock() {
    }

    public RemainingStock(Integer id,
                          String image,
                          String name,
                          Long quantity,
                          String unit) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
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

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
