package com.warehousemanagement.wms.dto;

import java.util.ArrayList;
import java.util.List;

public class FilterCategoryDTO {
    private Integer id;
    private String categoryName;
    private List<Integer> productsIds;


    public FilterCategoryDTO(Integer id,
                             String categoryName,
                             List<Integer> productsIds
                           ) {
        this.id = id;
        this.categoryName = categoryName;
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

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<Integer> getProductsIds() {
        return productsIds;
    }

    public void setProductsIds(List<Integer> productsIds) {
        this.productsIds = productsIds;
    }
}
