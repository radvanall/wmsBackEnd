package com.warehousemanagement.wms.dto;

import java.util.ArrayList;
import java.util.List;

public class FilterSubcategoryDTO {
    private Integer id;
    private String subcategoryName;
    private List<Integer> productsIds;

    public FilterSubcategoryDTO(Integer id,
                                String subcategoryName,
                                List<Integer> productsIds) {
        this.id = id;
        this.subcategoryName = subcategoryName;
        if (productsIds == null) {
            this.productsIds = new ArrayList<>();
        } else {
            this.productsIds = productsIds;
        }
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

    public List<Integer> getProductsIds() {
        return productsIds;
    }

    public void setProductsIds(List<Integer> productsIds) {
        this.productsIds = productsIds;
    }
}
