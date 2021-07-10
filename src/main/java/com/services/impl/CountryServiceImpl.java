package com.services.impl;

import com.google.gson.GsonBuilder;
import com.services.CountryService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {
    @Override
    public List<Object> getAllCountries() throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://restcountries.eu/rest/v2/all?fields=name;borders"))
                .header("x-rapidapi-key", "0dc0ec0a8cmsh64e7423f3a7a16ep1cb06ejsn41ccb9fb0748")
                .header("x-rapidapi-host", "andruxnet-world-cities-v1.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        List<Object> list = Arrays.asList(new GsonBuilder().create().fromJson(response.body(), Object[].class));

        return list;
    }
}
