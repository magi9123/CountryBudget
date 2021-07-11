package com.controller;

import com.services.CountryService;
import com.services.impl.CountryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

@RestController
public class Country {

    @Autowired
    CountryService countryService;

    @GetMapping("/index")
    public Model getCountries(Model model) throws IOException, InterruptedException {

        List<? extends Object> countries = countryService.getAllCountries();
        model.addAttribute("countries", countries);

        return model;
    }

    @PostMapping("/index/{countryName}/{money}")
    public ModelAndView getCountry(@PathVariable(name = "countryName") String country, @PathVariable(name = "money") String money) {
        System.out.println(country + " " + " ");
        String countryCheck = "Andorra";
        int moneyForTrip = 4000; //Integer.parseInt(money);

        String result = countryService.calculateTrip(countryCheck, moneyForTrip);
        ModelAndView modelAndView = new ModelAndView("/index");
        modelAndView.addObject("message", result);
        modelAndView.addObject("countries", CountryServiceImpl.getList());

        return modelAndView;
    }
}
