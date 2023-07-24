package com.warehousemanagement.wms.dto;

import java.util.ArrayList;
import java.util.List;

public class FilterProviderDTO {
    private Integer id;
    private String providerName;
    private List<Integer> productsIds;

    public FilterProviderDTO(Integer id,
                             String providerName,
                             List<Integer> productsIds) {
        this.id = id;
        this.providerName = providerName;
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

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public List<Integer> getProductsIds() {
        return productsIds;
    }

    public void setProductsIds(List<Integer> productsIds) {
        this.productsIds = productsIds;
    }
}
