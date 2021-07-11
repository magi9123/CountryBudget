package com.services;

import java.io.IOException;
import java.util.List;

public interface CountryService {
    List<? extends Object> getAllCountries() throws IOException, InterruptedException;

    String calculateTrip(String country, int money);
}
