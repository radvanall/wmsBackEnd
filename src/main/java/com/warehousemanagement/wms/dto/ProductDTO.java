package com.warehousemanagement.wms.dto;

import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;


public class ProductDTO {

    private Integer id;
    private String img;
    private String name;
    private Integer categorie;
    private Integer subcategorie;
    private Integer producator;
    private String unitate;
    private String descriere;
    private String imgName;

    public ProductDTO() {
    }


    public ProductDTO(Integer id, String img, String name, Integer categorie, Integer subcategorie, Integer producator, String unitate, String descriere) {
        this.id = id;
        this.img = img;
        this.name = name;
        this.categorie = categorie;
        this.subcategorie = subcategorie;
        this.producator = producator;
        this.unitate = unitate;
        this.descriere=descriere;
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

    public Integer  getCategorie() {
        return categorie;
    }

    public void setCategorie(Integer categorie) {
        this.categorie = categorie;
    }

    public Integer getSubcategorie() {
        return subcategorie;
    }

    public void setSubcategorie(Integer subcategorie) {
        this.subcategorie = subcategorie;
    }

    public Integer getProducator() {
        return producator;
    }

    public void setProducator(Integer producator) {
        this.producator = producator;
    }

    public String getUnitate() {
        return unitate;
    }

    public void setUnitate(String unitate) {
        this.unitate = unitate;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "id=" + id +
                ", img='" + img + '\'' +
                ", name='" + name + '\'' +
                ", categorie=" + categorie +
                ", subcategorie=" + subcategorie +
                ", producator=" + producator +
                ", unitate='" + unitate + '\'' +
                ", descriere='" + descriere + '\'' +
                '}';
    }
}
