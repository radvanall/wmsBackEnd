package com.warehousemanagement.wms.dto;

public class ProductTableDTO {
    private Integer id;
    private String img;
    private String name;
    private String categorie;
    private String subcategorie;
    private String producator;
    private String unitate;
    private Integer cantitate;
    private Integer minQuantity;

    public ProductTableDTO() {
    }

    public ProductTableDTO(Integer id, String img, String name, String categorie, String subcategorie, String producator, String unitate,Integer cantitate,Integer minQuantity) {
        this.id = id;
        this.img = img;
        this.name = name;
        this.categorie = categorie;
        this.subcategorie = subcategorie;
        this.producator = producator;
        this.unitate = unitate;
        this.cantitate=cantitate;
        this.minQuantity=minQuantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getSubcategorie() {
        return subcategorie;
    }

    public void setSubcategorie(String subcategorie) {
        this.subcategorie = subcategorie;
    }

    public String getProducator() {
        return producator;
    }

    public void setProducator(String producator) {
        this.producator = producator;
    }

    public String getUnitate() {
        return unitate;
    }

    public void setUnitate(String unitate) {
        this.unitate = unitate;
    }

    public Integer getCantitate() {
        return cantitate;
    }

    public void setCantitate(Integer cantitate) {
        this.cantitate = cantitate;
    }

    public Integer getMinQuantity() {
        return minQuantity;
    }

    public void setMinQuantity(Integer minQuantity) {
        this.minQuantity = minQuantity;
    }
}
