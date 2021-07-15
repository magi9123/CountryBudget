package com.models;

import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class CountryFormModel {

    @NotEmpty(message = "Must select the country")
    String countryName;

    @Value("#{new Integer('${formInfo.money}')}")
    @Min(value = 1, message = "You can trip without money.")
    int money;

    @Value("#{new Integer('${formInfo.costCountry}')}")
    @Min(value = 1, message = "Enter the average cost.")
    int costCountry;

    @NotEmpty(message = "Enter the currency.")
    String currency;

    public CountryFormModel() {
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getCostCountry() {
        return costCountry;
    }

    public void setCostCountry(int costCountry) {
        this.costCountry = costCountry;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
