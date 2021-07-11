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

    @RequestMapping("/index")
    public Model getCountries(Model model) throws IOException, InterruptedException {

        List<? extends Object> countries = countryService.getAllCountries();
        model.addAttribute("countries", countries);

        return model;
    }

    @GetMapping("/index/{countryName}/{money}/{costCountry}/{currency}")
    public ModelAndView getCountry(@RequestParam("countryName") String countryName, @RequestParam("money") String money,
                                   @RequestParam("costCountry") String costCountry, @RequestParam("currency") String currency) {
        int moneyForTrip = Integer.parseInt(money);
        int costForCountry = Integer.parseInt(costCountry);

        String result = countryService.calculateTrip(countryName, moneyForTrip, costForCountry, currency);
        ModelAndView modelAndView = new ModelAndView("/index");
        modelAndView.addObject("message", result);
        modelAndView.addObject("countries", CountryServiceImpl.getList());

        return modelAndView;
    }
}
