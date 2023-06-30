package com.warehousemanagement.wms.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class Provider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String providerName;
    private String email;
    private String tel;
    private String address;
    private String image;


    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "provider_id",referencedColumnName = "id")
    private List<Position> positions;
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,cascade = {CascadeType.ALL} ,mappedBy="provider")
    private List<InvoiceReception> invoiceReceptions;


    public Provider() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Provider(Integer id, String providerName) {
        this.id = id;
        this.providerName = providerName;
    }

    public Provider(String providerName, String email, List<Position> positions) {
        this.providerName = providerName;
        this.email = email;
        this.positions = positions;
    }

    public Provider(String providerName, String email, List<Position> positions, List<InvoiceReception> invoiceReceptions) {
        this.providerName = providerName;
        this.email = email;
        this.positions = positions;
        this.invoiceReceptions = invoiceReceptions;
    }

    public Provider(Integer id, String providerName, String email, String image, List<Position> positions, List<InvoiceReception> invoiceReceptions) {
        this.id = id;
        this.providerName = providerName;
        this.email = email;
        this.image = image;
        this.positions = positions;
        this.invoiceReceptions = invoiceReceptions;
    }

    public Provider(Integer id, String providerName, String email, String tel, String address, String image, List<Position> positions, List<InvoiceReception> invoiceReceptions) {
        this.id = id;
        this.providerName = providerName;
        this.email = email;
        this.tel = tel;
        this.address = address;
        this.image = image;
        this.positions = positions;
        this.invoiceReceptions = invoiceReceptions;
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

    public List<Position> getPositions() {
        return positions;
    }

    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }

    public List<InvoiceReception> getInvoiceReceptions() {
        return invoiceReceptions;
    }

    public void setInvoiceReceptions(List<InvoiceReception> invoiceReceptions) {
        this.invoiceReceptions = invoiceReceptions;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public void addPositions(Position position) {
        this.positions.add(position);
    }
}
