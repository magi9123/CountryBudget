package com.services;

import com.models.CountryModel;

import java.io.IOException;

public interface CountryService {
    CountryModel[] getAllCountries() throws IOException, InterruptedException;
}
