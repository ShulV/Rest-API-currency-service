package com.practice.sberclientandroidapp.model;

public class CurrencyMenuItem {
    private String charCode;
    private String name;

    public CurrencyMenuItem(String charCode, String name) {
        this.charCode = charCode;
        this.name = name;
    }

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
