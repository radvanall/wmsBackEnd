package com.warehousemanagement.wms.dto;

import com.warehousemanagement.wms.model.Category;
import com.warehousemanagement.wms.model.Provider;
import com.warehousemanagement.wms.model.Subcategory;

import java.util.List;

public class ProductObjectCriteriaDTO {

    List<Provider> providers;
    List<Category> categories;
    List<Subcategory> subcategories;
    List<String> unities;

    public ProductObjectCriteriaDTO() {
    }

    public ProductObjectCriteriaDTO(
            List<Provider> providers,
            List<Category> categories,
            List<Subcategory> subcategories,
            List<String> unities) {

        this.providers = providers;
        this.categories = categories;
        this.subcategories = subcategories;
        this.unities = unities;
    }


    public List<Provider> getProviders() {
        return providers;
    }

    public void setProviders(List<Provider> providers) {
        this.providers = providers;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Subcategory> getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(List<Subcategory> subcategories) {
        this.subcategories = subcategories;
    }

    public List<String> getUnities() {
        return unities;
    }

    public void setUnities(List<String> unities) {
        this.unities = unities;
    }
}
