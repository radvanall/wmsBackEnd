package com.warehousemanagement.wms.dto;

import java.util.List;

public class ProductCriteriaDTO {
    private Integer id;
    List<String> options;
    String label;
    String keyName;
    String defaultValue;

    public ProductCriteriaDTO() {
    }

    public ProductCriteriaDTO(Integer id, List<String> options, String label, String keyName, String defaultValue) {
        this.id = id;
        this.options = options;
        this.label = label;
        this.keyName = keyName;
        this.defaultValue = defaultValue;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }
}
