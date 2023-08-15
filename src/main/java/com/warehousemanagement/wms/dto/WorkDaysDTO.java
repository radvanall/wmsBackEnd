package com.warehousemanagement.wms.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

public class WorkDaysDTO {
    private Integer id;
    private Date data;
    private Integer workedHours;

    public WorkDaysDTO() {
    }

    public WorkDaysDTO(Integer id, Date data, Integer workedHours) {
        this.id = id;
        this.data = data;
        this.workedHours = workedHours;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String  getData() {
        return data.toString();
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
        return "WorkDaysDTO{" +
                "id=" + id +
                ", data=" + data +
                ", workedHours=" + workedHours +
                '}';
    }
}
