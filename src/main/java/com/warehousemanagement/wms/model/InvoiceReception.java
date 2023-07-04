package com.warehousemanagement.wms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table
public class InvoiceReception {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Boolean validated;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfCreation;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfValidation;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="invoice_reception_id",referencedColumnName ="id")
    private List<Stock> stocks;

    @ManyToOne(fetch = FetchType.EAGER,cascade = {CascadeType.ALL})
     @JoinColumn(name="createdByAdministrator", referencedColumnName ="id" )
     private Administrator createdBy;

    @ManyToOne(fetch = FetchType.EAGER,cascade = {CascadeType.ALL})
    @JoinColumn(name="validatedByAdministrator", referencedColumnName ="id" )
    private Administrator validatedBy;

    @ManyToOne(fetch = FetchType.EAGER,cascade = {CascadeType.ALL})
     @JoinColumn(name="provider_id",referencedColumnName = "id")
     private Provider provider;




    public InvoiceReception() {
        this.dateOfCreation = new Date(System.currentTimeMillis());
        this.validated=false;
    }


    public InvoiceReception(List<Stock> stocks, Administrator createdBy, Provider provider) {
        this.validated = false;
        this.dateOfCreation = new Date(System.currentTimeMillis());
        this.stocks = stocks;
        this.createdBy = createdBy;
        this.provider = provider;
    }

    public InvoiceReception(List<Stock> stocks) {
        this.validated=false;
        this.dateOfCreation = new Date(System.currentTimeMillis());
        this.stocks = stocks;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
//
//    public Date getData() {
//        return dateOfCreation;
//    }
//
//    public void setData(Date dateOfCreation) {
//        this.dateOfCreation = dateOfCreation;
//    }

    public List<Stock> getStocks() {
        return stocks;
    }

    public void setStocks(List<Stock> stocks) {
        this.stocks = stocks;
    }

    public Boolean getValidated() {
        return validated;
    }

    public void setValidated(Boolean validated) {
        this.validated = validated;
    }
//    @JsonIgnore
    public Administrator getCreatedBy() {
        return createdBy;
    }
//    @JsonIgnore
    public void setCreatedBy(Administrator createdBy) {
        this.createdBy = createdBy;
    }
//    @JsonIgnore

    public Administrator getValidatedBy() {
        return validatedBy;
    }

    public void setValidatedBy(Administrator validatedBy) {
        this.validatedBy = validatedBy;
    }

    public Provider getProvider() {
        return provider;
    }
//    @JsonIgnore
    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public Date getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(Date dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public Date getDateOfValidation() {
        return dateOfValidation;
    }

    public void setDateOfValidation(Date dateOfValidation) {
        this.dateOfValidation = dateOfValidation;
    }



    public void copyInvoiceReception(InvoiceReception invoiceReception) {
        this.dateOfCreation = new Date(System.currentTimeMillis());
        this.stocks = invoiceReception.stocks;
    }

}
