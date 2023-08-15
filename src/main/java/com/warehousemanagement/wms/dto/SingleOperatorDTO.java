package com.warehousemanagement.wms.dto;

import com.warehousemanagement.wms.model.OperatorWorkDays;

import java.util.List;

public class SingleOperatorDTO {
    private Integer id;
    private String nickname;
    private String avatar;
    private Integer phone;
    private String email;
    private String address;
    private String name;
    private String surname;
    private String status;
    private List<WorkDaysDTO> workedDays;
    private List<OperatorInvoiceDTO> invoices;

    public SingleOperatorDTO() {
    }

    public SingleOperatorDTO(Integer id,
                             String nickname, String avatar,
                             Integer phone, String email,String address,
                             String name, String surname, String status,
                             List<WorkDaysDTO> workedDays,
                             List<OperatorInvoiceDTO> invoices) {
        this.id = id;
        this.nickname = nickname;
        this.avatar = avatar;
        this.phone = phone;
        this.email=email;
        this.address = address;
        this.name = name;
        this.surname = surname;
        this.status = status;
        this.workedDays = workedDays;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<WorkDaysDTO> getWorkedDays() {
        return workedDays;
    }

    public void setWorkedDays(List<WorkDaysDTO> workedDays) {
        this.workedDays = workedDays;
    }

    public List<OperatorInvoiceDTO> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<OperatorInvoiceDTO> invoices) {
        this.invoices =invoices;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
