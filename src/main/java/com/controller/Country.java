package com.controller;

import com.models.CountryFormModel;
import com.services.CountryService;
import com.services.impl.CountryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
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

    @PostMapping("/index")
    public ModelAndView getCountry(@Valid CountryFormModel countryFormModel, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView("/index");

        if (bindingResult.hasErrors()) {
            return modelAndView;
        } else {
             String result = countryService.calculateTrip(countryFormModel);
            //  modelAndView.addObject("message", result);
            modelAndView.addObject("countries", CountryServiceImpl.getList());
        }

        return modelAndView;
    }
}
