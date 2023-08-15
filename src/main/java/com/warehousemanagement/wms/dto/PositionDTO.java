package com.warehousemanagement.wms.dto;

public class PositionDTO {
    private Integer id;
    private String name;
    private String image;
    private Boolean isActive;
    private Integer remainingQuantity;

    public PositionDTO() {
    }

    public PositionDTO(Integer id,
                       String name,
                       String image,
                       Boolean isActive,
                       Integer remainingQuantity) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.isActive=isActive;
        this.remainingQuantity = remainingQuantity;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getRemainingQuantity() {
        return remainingQuantity;
    }

    public void setRemainingQuantity(Integer remainingQuantity) {
        this.remainingQuantity = remainingQuantity;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
