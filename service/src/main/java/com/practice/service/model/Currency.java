package com.practice.service.model;

public class Currency {
    private int PK_id;
    private int numCode;
    private String charCode;

    private String name;

    public Currency() {
    }

    public Currency(int PK_id, int numCode, String charCode, String name) {
        this.PK_id = PK_id;
        this.numCode = numCode;
        this.charCode = charCode;
        this.name = name;
    }


    public int getPK_Id() {
        return PK_id;
    }

    public void setPK_Id(int PK_id) { this.PK_id = PK_id; }

    public int getNumCode() {
        return numCode;
    }

    public void setNumCode(int numCode) { this.numCode = numCode; }

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