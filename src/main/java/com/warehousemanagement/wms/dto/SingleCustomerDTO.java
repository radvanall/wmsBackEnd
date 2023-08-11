package com.warehousemanagement.wms.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class SingleCustomerDTO {
    private Integer id;
    private String nickname;
    private String avatar;
    private String email;
    private Integer phone;
    private String address;
    private Boolean active;
    private List<SingleCustomerInvoice> invoices;

    public SingleCustomerDTO() {
    }

    public SingleCustomerDTO(Integer id,
                             String nickname,
                             String avatar,
                             String email,
                             Integer phone,
                             String address,
                             Boolean active,
                             List<SingleCustomerInvoice> invoices) {
        this.id = id;
        this.nickname = nickname;
        this.avatar = avatar;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.active = active;
        this.invoices = invoices;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<SingleCustomerInvoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<SingleCustomerInvoice> invoices) {
        this.invoices = invoices;
    }
}
