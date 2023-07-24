package com.warehousemanagement.wms.dto;

import java.util.List;

public class StockFilterFieldsDTO {
  private List<FilterProviderDTO> filterProviderDTOList;
  private List<FilterProductDTO> filterProductDTOS;
  private List<FilterCategoryDTO> filterCategoryDTOS;
  private List<FilterSubcategoryDTO> filterSubcategoryDTOS;
  private Double maxBuyingPrice;
  private Double maxSellingPrice;
  private Integer maxQuantity;

    public StockFilterFieldsDTO(List<FilterProviderDTO> filterProviderDTOList,
                                List<FilterProductDTO> filterProductDTOS,
                                List<FilterCategoryDTO> filterCategoryDTOS,
                                List<FilterSubcategoryDTO> filterSubcategoryDTOS,
                                Double maxBuyingPrice,
                                Double maxSellingPrice,
                                Integer maxQuantity) {
        this.filterProviderDTOList = filterProviderDTOList;
        this.filterProductDTOS = filterProductDTOS;
        this.filterCategoryDTOS = filterCategoryDTOS;
        this.filterSubcategoryDTOS = filterSubcategoryDTOS;
        this.maxBuyingPrice = maxBuyingPrice;
        this.maxSellingPrice=maxSellingPrice;
        this.maxQuantity = maxQuantity;

    }

    public List<FilterProviderDTO> getFilterProviderDTOList() {
        return filterProviderDTOList;
    }

    public void setFilterProviderDTOList(List<FilterProviderDTO> filterProviderDTOList) {
        this.filterProviderDTOList = filterProviderDTOList;
    }

    public List<FilterProductDTO> getFilterProductDTOS() {
        return filterProductDTOS;
    }

    public void setFilterProductDTOS(List<FilterProductDTO> filterProductDTOS) {
        this.filterProductDTOS = filterProductDTOS;
    }

    public List<FilterCategoryDTO> getFilterCategoryDTOS() {
        return filterCategoryDTOS;
    }

    public void setFilterCategoryDTOS(List<FilterCategoryDTO> filterCategoryDTOS) {
        this.filterCategoryDTOS = filterCategoryDTOS;
    }

    public List<FilterSubcategoryDTO> getFilterSubcategoryDTOS() {
        return filterSubcategoryDTOS;
    }

    public void setFilterSubcategoryDTOS(List<FilterSubcategoryDTO> filterSubcategoryDTOS) {
        this.filterSubcategoryDTOS = filterSubcategoryDTOS;
    }

    public Double getMaxBuyingPrice() {
        return maxBuyingPrice;
    }

    public void setMaxBuyingPrice(Double maxBuyingPrice) {
        this.maxBuyingPrice = maxBuyingPrice;
    }

    public Double getMaxSellingPrice() {
        return maxSellingPrice;
    }

    public void setMaxSellingPrice(Double maxSellingPrice) {
        this.maxSellingPrice = maxSellingPrice;
    }

    public Integer getMaxQuantity() {
        return maxQuantity;
    }

    public void setMaxQuantity(Integer maxQuantity) {
        this.maxQuantity = maxQuantity;
    }
}
