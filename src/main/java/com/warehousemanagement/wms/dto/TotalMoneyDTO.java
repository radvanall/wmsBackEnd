package com.warehousemanagement.wms.dto;

public class TotalMoneyDTO {
    private Integer id;
    private String image;
    private String name;
    private Double sum;

    public TotalMoneyDTO() {
    }

    public TotalMoneyDTO(Integer id, String image, String name, Double sum) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.sum = sum;
    }
    public TotalMoneyDTO(Integer id, String image, String name, Long sum) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.sum = Double.valueOf(sum);
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

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }
}
