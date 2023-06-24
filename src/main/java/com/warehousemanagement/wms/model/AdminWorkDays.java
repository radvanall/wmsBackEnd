package com.warehousemanagement.wms.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class AdminWorkDays {
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

    public AdminWorkDays() {
    }

    public AdminWorkDays(Date data) {
        this.data = data;
    }

    public void copyDay(AdminWorkDays getDay) {
        this.data=getDay.data;
    }
}
