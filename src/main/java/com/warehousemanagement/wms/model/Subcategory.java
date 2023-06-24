package com.warehousemanagement.wms.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class Subcategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String subcategoryName;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="subcategory_id",referencedColumnName = "id")
    private List<Position> positions;

    public Subcategory() {
    }

    public Subcategory(Integer id, String subcategoryName) {
        this.id = id;
        this.subcategoryName = subcategoryName;
    }

    public Subcategory(String subcategoryName) {
        this.subcategoryName = subcategoryName;
    }

    public Subcategory(String subcategoryName, List<Position> positions) {
        this.subcategoryName = subcategoryName;
        this.positions = positions;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSubcategoryName() {
        return subcategoryName;
    }

    public void setSubcategoryName(String subcategoryName) {
        this.subcategoryName = subcategoryName;
    }

    public List<Position> getPositions() {
        return positions;
    }

    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }

    @Override
    public String toString() {
        return "Subcategory{" +
                "id=" + id +
                ", subcategoryName='" + subcategoryName + '\'' +
                ", positions=" + positions +
                '}';
    }

    public void addPositions(Position position) {this.positions.add(position);
    }
}
