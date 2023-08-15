package com.warehousemanagement.wms.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class Operator {
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
    private String status;
    @OneToMany(fetch = FetchType.LAZY,cascade={CascadeType.DETACH,
            CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH},
             mappedBy = "operator")
//    @JoinColumn(name="operator_id",referencedColumnName ="id")
    private List<Invoice> invoices;
    @OneToMany(fetch = FetchType.LAZY,cascade = {CascadeType.ALL})
    @JoinColumn(name="operator_id", referencedColumnName ="id" )
    private List<OperatorWorkDays> operatorWorkDays;

    public Operator() {
    }

    public Operator(String nickname, String password, String avatar, String email, Integer phone, String address, String name, String surname,String status) {
        this.nickname = nickname;
        this.password = password;
        this.avatar = avatar;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.name = name;
        this.surname = surname;
        this.status=status;
    }

    public Operator(String nickname, String password, String avatar, String email, Integer phone, String address, String name, String surname, List<Invoice> invoices,List<OperatorWorkDays> operatorWorkDays,String status) {
        this.nickname = nickname;
        this.password = password;
        this.avatar = avatar;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.name = name;
        this.surname = surname;
        this.invoices = invoices;
        this.operatorWorkDays=operatorWorkDays;
        this.status=status;
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

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }

    public List<OperatorWorkDays> getOperatorWorkDays() {
        return operatorWorkDays;
    }

    public void setOperatorWorkDays(List<OperatorWorkDays> operatorWorkDays) {
        this.operatorWorkDays = operatorWorkDays;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void copyOperator(Operator operator){
        this.name=operator.name;
        this.surname=operator.surname;
        this.address=operator.address;
        this.email=operator.email;
        this.avatar=operator.avatar;
        this.password=operator.password;
        this.nickname=operator.nickname;
        this.phone=operator.phone;
        this.operatorWorkDays=operator.operatorWorkDays;
        this.status=operator.status;
    }

    public void addInvoice(Invoice invoice) {
        invoice.setShipped(true);
        this.invoices.add(invoice);
    }

    @Override
    public String toString() {
        return "Operator{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", avatar='" + avatar + '\'' +
                ", email='" + email + '\'' +
                ", phone=" + phone +
                ", address='" + address + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", invoices=" + invoices +
                ", operatorWorkDays=" + operatorWorkDays +
                ", status=" + status +
                '}';
    }
}
