package com.practice.sberclientandroidapp.model;

import androidx.annotation.NonNull;

import java.sql.Date;
// Модель данных для курсов валют за дату.
public class DayCurrency {
    // Порядковый номер записи в базе данных.
    private int PK_daycur;
    // Курс валюты.
    private double value;
    // Дата, за которую был получен курс.
    private Date date;
    // Номинал валюты.
    private int nominal;
    // ID-код валюты.
    private String PK_id;
    // Пустой конструктор.
    public DayCurrency() {

    }
    // Конструктор по всем полям.
    public DayCurrency(int PK_daycur, double value, Date date, int nominal, String PK_id) {
        this.PK_daycur = PK_daycur;
        this.value = value;
        this.date = date;
        this.nominal = nominal;
        this.PK_id = PK_id;
    }
    // Конструктор по полям value, date, nominal.
    public DayCurrency(double value, Date date, int nominal) {
        this.value = value;
        this.date = date;
        this.nominal = nominal;
    }
    // Геттеры и сеттер для полей.
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
    // Метод для представления данных модели в виде строки.
    @NonNull
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
}