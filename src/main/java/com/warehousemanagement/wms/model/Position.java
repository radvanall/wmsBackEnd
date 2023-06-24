package com.warehousemanagement.wms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.warehousemanagement.wms.dto.ProductDTO;
import com.warehousemanagement.wms.dto.ProductTableDTO;

import javax.persistence.*;
import java.util.List;

@NamedNativeQuery(
        name = "Position.getTableProductsDTOList",
        query = "select p.id as id ,p.image as img, p.name as name,c.category_name as categorie,s.subcategory_name as subcategorie," +
                "pr.provider_name as producator,p.unity as unitate  from work.position p inner join work.provider pr on p.provider_id=pr.id inner join " +
                "work.subcategory s on p.subcategory_id=s.id inner join work.category c on p.category_id=c.id where p.active=true order by p.id",
        resultSetMapping = "ProductTableDTO"
)

@SqlResultSetMapping(
        name="ProductTableDTO",
        classes = @ConstructorResult(
                targetClass = ProductTableDTO.class,
                columns={
                        @ColumnResult(name="id", type=Integer.class),
                        @ColumnResult(name="img",type=String.class),
                        @ColumnResult(name="name",type=String.class),
                        @ColumnResult(name="categorie",type=String.class),
                        @ColumnResult(name="subcategorie",type=String.class),
                        @ColumnResult(name="producator",type=String.class),
                        @ColumnResult(name="unitate",type=String.class)

                }
        )
)
@NamedNativeQuery(
        name = "Position.getProductById",
        query = "select p.id as id ,p.image as img, p.name as name,p.category_id as categorie,p.subcategory_id as subcategorie, " +
                " p.provider_id as producator,p.unity as unitate,p.description as descriere  from work.position p where p.id=:id ",
        resultSetMapping = "ProductDTO"
)

@SqlResultSetMapping(
        name="ProductDTO",
        classes = @ConstructorResult(
                targetClass = ProductDTO.class,
                columns={
                        @ColumnResult(name="id", type=Integer.class),
                        @ColumnResult(name="img",type=String.class),
                        @ColumnResult(name="name",type=String.class),
                        @ColumnResult(name="categorie",type=Integer.class),
                        @ColumnResult(name="subcategorie",type=Integer.class),
                        @ColumnResult(name="producator",type=Integer.class),
                        @ColumnResult(name="unitate",type=String.class),
                        @ColumnResult(name="descriere",type=String.class),
                }
        )
)
@Entity
@Table
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private String image;
    private String unity;
    private boolean active;
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,cascade = {CascadeType.ALL} ,mappedBy="position")
//    @JoinColumn(name = "position_id",referencedColumnName = "id")
    private List<Stock> stocks;

    public Position() {
    }


    public Position(Integer id, String name, String description, String image, String unity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.unity = unity;

    }

    public Position(Integer id, String name, String description, String image, String unity, boolean active, List<Stock> stocks) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.unity = unity;
        this.active = active;
        this.stocks = stocks;
    }

    public Position(String name, String description, String image, String unity) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.unity=unity;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Stock> getStocks() {
        return stocks;
    }

    public String getUnity() {
        return unity;
    }

    public void setUnity(String unity) {
        this.unity = unity;
    }

    public void setStocks(List<Stock> stocks) {
        this.stocks = stocks;
    }
    public void createStock() {
            Stock stock=new Stock();
        this.stocks.add(stock);
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void copyPosition(Position position) {
        this.name = position.name;
        this.description = position.description;
        this.image = position.image;
//        this.stocks = position.stocks;
        this.unity=position.unity;

    }

    @Override
    public String toString() {
        return "Position{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", unity='" + unity + '\'' +
                ", active=" + active +
                ", stocks=" + stocks +
                '}';
    }
}
