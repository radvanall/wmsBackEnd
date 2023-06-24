package com.warehousemanagement.wms.model;

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

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public OperatorWorkDays() {
    }

    public OperatorWorkDays(Date data) {
        this.data = data;
    }

    public void copyDay(OperatorWorkDays operatorWorkDay) {
        this.data=operatorWorkDay.data;
    }
}
