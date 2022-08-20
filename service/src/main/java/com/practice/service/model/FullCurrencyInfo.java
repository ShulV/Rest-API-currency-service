package com.practice.service.model;

import java.sql.Date;

public class FullCurrencyInfo {
    private double value;
    private Date date;
    private int nominal;
    private String charcode;
    private String name;
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
    public String getCharcode() {
        return charcode;
    }
    public void setCharcode(String charcode) {
        this.charcode = charcode;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public FullCurrencyInfo(double value, Date date, int nominal, String charcode, String name) {
        this.value = value;
        this.date = date;
        this.nominal = nominal;
        this.charcode = charcode;
        this.name = name;
    }
    public FullCurrencyInfo() {}
    @Override
    public String toString() {
        return "FullCurrencyInfo{" +
                "value=" + value +
                ", date=" + date +
                ", nominal=" + nominal +
                ", charcode='" + charcode + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}