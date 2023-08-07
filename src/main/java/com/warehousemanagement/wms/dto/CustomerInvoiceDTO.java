package com.warehousemanagement.wms.dto;

public class CustomerInvoiceDTO {
    private Integer id;
    private String name;
    private String address;
    private String image;

    public CustomerInvoiceDTO() {
    }

    public CustomerInvoiceDTO(Integer id,
                              String name,
                              String address,
                              String image) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.image = image;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
