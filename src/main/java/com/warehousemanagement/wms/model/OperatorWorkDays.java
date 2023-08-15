package com.warehousemanagement.wms.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class OperatorWorkDays {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;
    private Integer workedHours;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    public Date getData() {
        return data;
    }


    public OperatorWorkDays( Date data, Integer workedHours) {
        this.data = data;
        this.workedHours = workedHours;
    }

    public OperatorWorkDays() {
    }

    public OperatorWorkDays(Date data) {
        this.data = data;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Integer getWorkedHours() {
        return workedHours;
    }

    public void setWorkedHours(Integer workedHours) {
        this.workedHours = workedHours;
    }

    @Override
    public String toString() {
        return "OperatorWorkDays{" +
                "id=" + id +
                ", data=" + data.toString() +
                ", workedHours=" + workedHours +
                '}';
    }

    public void copyDay(OperatorWorkDays operatorWorkDay) {
        this.data=operatorWorkDay.data;
    }
}
