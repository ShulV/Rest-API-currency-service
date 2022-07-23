package com.practice.service.model;

import java.sql.Date;

public class DayCurrency implements Cloneable {
    private int PK_daycur;
    private double value;
    private Date date;
    private int nominal;
    private String PK_id;

    public DayCurrency() {

    }

    public DayCurrency(int PK_daycur, double value, Date date, int nominal, String PK_id) {
        this.PK_daycur = PK_daycur;
        this.value = value;
        this.date = date;
        this.nominal = nominal;
        this.PK_id = PK_id;
    }

    public DayCurrency(double value, Date date, int nominal) {
        this.value = value;
        this.date = date;
        this.nominal = nominal;
    }

    public DayCurrency(double value, Date date, int nominal, String PK_id) {
        this.value = value;
        this.date = date;
        this.nominal = nominal;
        this.PK_id = PK_id;
    }

    public int getPK_daycur() {
        return PK_daycur;
    }

    public void setPK_daycur(int PK_daycur) {
        this.PK_daycur = PK_daycur;
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

    public int getNominal() {
        return nominal;
    }

    public void setNominal(int nominal) {
        this.nominal = nominal;
    }

    public String getPK_id() {
        return PK_id;
    }

    public void setPK_id(String PK_id) {
        this.PK_id = PK_id;
    }

    @Override
    public String toString() {
        return "DayCurrency{" +
                "PK_daycur=" + PK_daycur +
                ", value=" + value +
                ", date=" + date +
                ", nominal=" + nominal +
                ", PK_id='" + PK_id + '\'' +
                '}';
    }

    @Override
    public DayCurrency clone() {
        try {
            // Глубокое копирование
            DayCurrency clone = (DayCurrency) super.clone();
            Date date = (Date) this.getDate().clone();
            clone.setDate(date);
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
