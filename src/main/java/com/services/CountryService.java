package com.services;

import com.models.CountryFormModel;

import java.io.IOException;
import java.util.List;

public interface CountryService {
    List<? extends Object> getAllCountries() throws IOException, InterruptedException;

    String calculateTrip(CountryFormModel countryFormModel);
}
