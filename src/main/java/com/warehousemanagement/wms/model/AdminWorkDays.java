package com.warehousemanagement.wms.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class AdminWorkDays {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer workedHours;
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;

    public AdminWorkDays(Date newDate, Integer workedHours) {
        this.data=newDate;
        this.workedHours=workedHours;
    }

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    public Date getData() {
        return data;
    }
    public AdminWorkDays() {
    }

    public AdminWorkDays(Integer id, Integer workedHours, Date data) {
        this.id = id;
        this.workedHours = workedHours;
        this.data = data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public AdminWorkDays(Date data) {
        this.data = data;
    }

    public void copyDay(AdminWorkDays getDay) {
        this.data=getDay.data;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWorkedHours() {
        return workedHours;
    }

    public void setWorkedHours(Integer workedHours) {
        this.workedHours = workedHours;
    }
}
