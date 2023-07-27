package com.warehousemanagement.wms.dto;

import java.util.List;

public class StockFilterCriteriaDTO {
    private List<Integer> providers;
    private List<Integer> categories;
    private List<Integer> subcategories;
    private List<Integer> products;
    private List<String> status;
    private Double maxBuyingPrice;
    private Double minBuyingPrice;
    private Double maxSellingPrice;
    private Double minSellingPrice;
    private Integer minQuantity;
    private Integer maxQuantity;

    public StockFilterCriteriaDTO() {
    }

    public StockFilterCriteriaDTO(List<Integer> providers,
                                  List<Integer> categories,
                                  List<Integer> subcategories,
                                  List<Integer> products,
                                  List<String> status,
                                  Double maxBuyingPrice,
                                  Double minBuyingPrice,
                                  Double maxSellingPrice,
                                  Double minSellingPrice,
                                  Integer minQuantity,
                                  Integer maxQuantity) {
        this.providers = providers;
        this.categories = categories;
        this.subcategories = subcategories;
        this.products = products;
        this.status = status;
        this.maxBuyingPrice = maxBuyingPrice;
        this.minBuyingPrice = minBuyingPrice;
        this.maxSellingPrice = maxSellingPrice;
        this.minSellingPrice = minSellingPrice;
        this.minQuantity = minQuantity;
        this.maxQuantity = maxQuantity;
    }

    public List<Integer> getProviders() {
        return providers;
    }

    public void setProviders(List<Integer> providers) {
        this.providers = providers;
    }

    public List<Integer> getCategories() {
        return categories;
    }

    public void setCategories(List<Integer> categories) {
        this.categories = categories;
    }

    public List<Integer> getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(List<Integer> subcategories) {
        this.subcategories = subcategories;
    }

    public List<Integer> getProducts() {
        return products;
    }

    public void setProducts(List<Integer> products) {
        this.products = products;
    }

    public List<String> getStatus() {
        return status;
    }

    public void setStatus(List<String> status) {
        this.status = status;
    }

    public Double getMaxBuyingPrice() {
        return maxBuyingPrice;
    }

    public void setMaxBuyingPrice(Double maxBuyingPrice) {
        this.maxBuyingPrice = maxBuyingPrice;
    }

    public Double getMinBuyingPrice() {
        return minBuyingPrice;
    }

    public void setMinBuyingPrice(Double minBuyingPrice) {
        this.minBuyingPrice = minBuyingPrice;
    }

    public Double getMaxSellingPrice() {
        return maxSellingPrice;
    }

    public void setMaxSellingPrice(Double maxSellingPrice) {
        this.maxSellingPrice = maxSellingPrice;
    }

    public Double getMinSellingPrice() {
        return minSellingPrice;
    }

    public void setMinSellingPrice(Double minSellingPrice) {
        this.minSellingPrice = minSellingPrice;
    }

    public Integer getMinQuantity() {
        return minQuantity;
    }

    public void setMinQuantity(Integer minQuantity) {
        this.minQuantity = minQuantity;
    }

    public Integer getMaxQuantity() {
        return maxQuantity;
    }

    public void setMaxQuantity(Integer maxQuantity) {
        this.maxQuantity = maxQuantity;
    }

    @Override
    public String toString() {
        return "StockFilterCriteriaDTO{" +
                "providers=" + providers +
                ", categories=" + categories +
                ", subcategories=" + subcategories +
                ", products=" + products +
                ", status=" + status +
                ", maxBuyingPrice=" + maxBuyingPrice +
                ", minBuyingPrice=" + minBuyingPrice +
                ", maxSellingPrice=" + maxSellingPrice +
                ", minSellingPrice=" + minSellingPrice +
                ", minQuantity=" + minQuantity +
                ", maxQuantity=" + maxQuantity +
                '}';
    }
}
