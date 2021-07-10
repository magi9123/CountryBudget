package com.models;

import java.util.List;

public class Country {

    private String name;
    private List<String> borders;

    public Country(String name, List<String> borders) {
        this.name = name;
        this.borders = borders;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getBorders() {
        return borders;
    }

    public void setBorders(List<String> borders) {
        this.borders = borders;
    }
}
