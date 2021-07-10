package com.services.impl;

import com.google.gson.Gson;
import com.models.CountryModel;
import com.services.CountryService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class CountryServiceImpl implements CountryService {
    @Override
    public CountryModel[] getAllCountries() throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://restcountries.eu/rest/v2/all?fields=name;borders"))
                .header("x-rapidapi-key", "0dc0ec0a8cmsh64e7423f3a7a16ep1cb06ejsn41ccb9fb0748")
                .header("x-rapidapi-host", "andruxnet-world-cities-v1.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        Gson gson = new Gson();
        CountryModel[] countries = gson.fromJson(response.body(), CountryModel[].class);

        return countries;
    }
}
