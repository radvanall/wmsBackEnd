package com.warehousemanagement.wms.dto;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
//
//
//
//@NamedNativeQuery(
//        name = "ProductTableDTO.getTableProductsDTOList",
//        query = "select p.id as id ,p.image as img, p.name as name,c.category_name as categorie,s.subcategory_name as subcategorie," +
//                "pr.provider_name as producator,p.unity as unitate  from work.position p inner join work.provider pr on p.provider_id=pr.id inner join " +
//                "work.subcategory s on p.subcategory_id=s.id inner join work.category c on p.category_id=c.id order by p.id",
//        resultSetMapping = "ProductTableDTO"
//)
//
//@SqlResultSetMapping(
//        name="ProductTableDTO",
//        classes = @ConstructorResult(
//                targetClass = ProductTableDTO.class,
//                columns={
//                        @ColumnResult(name="id", type=Integer.class),
//                        @ColumnResult(name="img",type=String.class),
//                        @ColumnResult(name="name",type=String.class),
//                        @ColumnResult(name="categorie",type=String.class),
//                        @ColumnResult(name="subcategorie",type=String.class),
//                        @ColumnResult(name="producator",type=String.class),
//                        @ColumnResult(name="unitate",type=String.class)
//
//                }
//        )
//)
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
