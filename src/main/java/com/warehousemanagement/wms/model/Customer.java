package com.warehousemanagement.wms.model;


import javax.persistence.*;
import java.util.List;


@Entity
@Table
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nickname;
    private String password;
    private String avatar;
    private String email;
    private Integer phone;
    private String address;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="customer_id",referencedColumnName ="id")
    private List<Invoice> invoices;

    public Customer() {
    }

    public Customer(String nickname, String password, String avatar, String email, Integer phone, String address,List<Invoice> invoices) {
        this.nickname = nickname;
        this.password = password;
        this.avatar = avatar;
        this.email = email;
        this.phone = phone;
        this.address = address;
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

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void setOrder(List<Invoice> invoices) {
        this.invoices = invoices;
    }

    public void copyCustomer(Customer customer){
        this.address=customer.address;
        this.email=customer.email;
        this.avatar=customer.avatar;
        this.password=customer.password;
        this.nickname=customer.nickname;
        this.phone=customer.phone;
    }
}
