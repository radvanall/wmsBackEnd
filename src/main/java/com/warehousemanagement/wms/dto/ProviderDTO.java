package com.warehousemanagement.wms.dto;

import java.util.List;

public class ProviderDTO {
    private Integer id;
    private String providerName;
    private String email;
    private String tel;
    private String address;
    private String image;
    private List<PositionDTO> positions;
    private List<ProviderPageInvoiceDTO> invoices;

    public ProviderDTO() {
    }

    public ProviderDTO(Integer id,
                       String providerName,
                       String email,
                       String tel,
                       String address,
                       String image,
                       List<PositionDTO> positions,
                       List<ProviderPageInvoiceDTO> invoices) {
        this.id = id;
        this.providerName = providerName;
        this.email = email;
        this.tel = tel;
        this.address = address;
        this.image = image;
        this.positions = positions;
        this.invoices=invoices;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
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

    public List<PositionDTO> getPositions() {
        return positions;
    }

    public void setPositions(List<PositionDTO> positions) {
        this.positions = positions;
    }

    public List<ProviderPageInvoiceDTO> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<ProviderPageInvoiceDTO> invoices) {
        this.invoices = invoices;
    }
}
