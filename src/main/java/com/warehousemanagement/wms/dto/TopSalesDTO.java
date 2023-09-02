package com.warehousemanagement.wms.dto;

import java.util.Date;

public class TopSalesDTO {
    private Integer id;
    private String name;
    private String avatar;
    private Date date;
    private Double weeklySum;

    public TopSalesDTO() {
    }


    public TopSalesDTO(Integer id, String name, Date date, Double weeklySum) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.weeklySum = weeklySum;
    }

    public TopSalesDTO(Integer id, String name, String avatar, Date date, Double weeklySum) {
        this.id = id;
        this.name = name;
        this.avatar = avatar;
        this.date = date;
        this.weeklySum = weeklySum;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getWeeklySum() {
        return weeklySum;
    }

    public void setWeeklySum(Double weeklySum) {
        this.weeklySum = weeklySum;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "TopSalesDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", weeklySum=" + weeklySum +
                '}';
    }
}
