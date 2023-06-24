package com.warehousemanagement.wms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class Administrator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nickname;
    private String password;
    private String avatar;
    private String email;
    private Integer phone;
    private String address;
    private String name;
    private String surname;
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,cascade = {CascadeType.ALL},mappedBy = "administrator")
//    @JoinColumn(name="administrator_id", referencedColumnName ="id" )
    private List<InvoiceReception> invoiceReceptions;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="administrator_id", referencedColumnName ="id" )
    private List<AdminWorkDays> adminWorkDays;

    public Administrator() {
    }

    public Administrator(String nickname, String password, String avatar, String email, Integer phone, String address, String name, String surname, List<InvoiceReception> invoiceReceptions,List<AdminWorkDays> adminWorkDays) {
        this.nickname = nickname;
        this.password = password;
        this.avatar = avatar;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.name = name;
        this.surname = surname;
        this.invoiceReceptions = invoiceReceptions;
        this.adminWorkDays=adminWorkDays;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public List<InvoiceReception> getInvoiceReceptions() {
        return invoiceReceptions;
    }

    public void setInvoiceReceptions(List<InvoiceReception> invoiceReceptions) {
        this.invoiceReceptions = invoiceReceptions;
    }

    public List<AdminWorkDays> getAdminWorkDays() {
        return adminWorkDays;
    }

    public void setAdminWorkDays(List<AdminWorkDays> adminWorkDays) {
        this.adminWorkDays = adminWorkDays;
    }

    public void copyAdministrator(Administrator administrator){
        this.name=administrator.name;
        this.surname=administrator.surname;
        this.address=administrator.address;
        this.email=administrator.email;
        this.avatar=administrator.avatar;
        this.password=administrator.password;
        this.nickname=administrator.nickname;
        this.phone=administrator.phone;
        this.adminWorkDays=administrator.adminWorkDays;
    }
}
