package com.controller;

import com.models.CountryModel;
import com.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
//@RequestMapping("api/countries")
public class Country {

    @Autowired
    CountryService countryService;

    @GetMapping("/index")
    public Model getCountries(Model model) throws IOException, InterruptedException {

        List<Object> countries = countryService.getAllCountries();
        model.addAttribute("countries", countries);

        return model;
    }

//    @GetMapping("/{country}")
//    public void getCountry(@PathVariable(name = "country") String country, @PathVariable(name = "money") int money) {
//
//    }
}
