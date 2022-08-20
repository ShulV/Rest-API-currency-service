package com.practice.service.model;

public class Currency {
    private String PK_id;
    private int numCode;
    private String charCode;
    private String name;
    public Currency() {
    }
    public Currency(String PK_id,int numCode, String charCode, String name) {
        this.PK_id = PK_id;
        this.numCode = numCode;
        this.charCode = charCode;
        this.name = name;
    }
    public String getPK_Id() {
        return PK_id;
    }
    public void setPK_Id(String PK_id) { this.PK_id = PK_id; }
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
    @Override
    public String toString() {
        return "Currency{" +
                "PK_id='" + PK_id + '\'' +
                ", numCode='" + numCode + '\'' +
                ", charCode='" + charCode + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
