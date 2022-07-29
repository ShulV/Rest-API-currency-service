package com.practice.sberclientandroidapp.model;
// Модель данных для обозначений валют.
public class CurrencyMenuItem {
    // Символьный код валюты.
    private String charCode;
    // Название валюты.
    private String name;
    // Конструктор по всем полям.
    public CurrencyMenuItem(String charCode, String name) {
        this.charCode = charCode;
        this.name = name;
    }
    // Геттеры и сеттеры для полей.
    public String getCharCode() {
        return charCode;
    }
    public void setCharCode(String charCode) {
        this.charCode = charCode;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
