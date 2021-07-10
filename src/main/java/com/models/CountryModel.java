package com.models;

public class CountryModel {

    private String name;
    private String[] borders;

    public CountryModel(String name, String[] borders) {
        this.name = name;
        this.borders = borders;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getBorders() {
        return borders;
    }

    public void setBorders(String[] borders) {
        this.borders = borders;
    }
}
