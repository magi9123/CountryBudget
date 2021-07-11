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

    @GetMapping("/index/{countryName}/{money}")
    public ModelAndView getCountry(@PathVariable("countryName") String countryName, @PathVariable("money") String money) {
        String countryCheck = countryName.split("=")[1];
        int moneyForTrip = Integer.parseInt(money.split("=")[1]);

        String result = countryService.calculateTrip(countryCheck, moneyForTrip);
        ModelAndView modelAndView = new ModelAndView("/index");
        modelAndView.addObject("message", result);
        modelAndView.addObject("countries", CountryServiceImpl.getList());

        return modelAndView;
    }
}
