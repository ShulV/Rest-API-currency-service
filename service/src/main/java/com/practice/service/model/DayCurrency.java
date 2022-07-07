package com.practice.service.model;

import java.sql.Date;

public class DayCurrency {

    private int PK_id;

    private int nominal;

    private double value;

    private Date date;

    public DayCurrency(){

    }
    public int getPK_Id() {
        return PK_id;
    }

    public void setPK_Id(int PK_id) {
        this.PK_id = PK_id;
    }

    public int getNominal() {
        return nominal;
    }

    public void setNominal(int nominal) {
        this.nominal = nominal;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
