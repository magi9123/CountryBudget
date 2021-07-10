package com.services.impl;

import com.google.gson.GsonBuilder;
import com.models.CountryModel;
import com.services.CountryService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    private static List<Object> list = new LinkedList<>();

    @Override
    public List<Object> getAllCountries() throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://restcountries.eu/rest/v2/all?fields=name;borders"))
                .header("x-rapidapi-key", "0dc0ec0a8cmsh64e7423f3a7a16ep1cb06ejsn41ccb9fb0748")
                .header("x-rapidapi-host", "andruxnet-world-cities-v1.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        list = Arrays.asList(new GsonBuilder().create().fromJson(response.body(), Object[].class));

        return list;
    }

    @Override
    public String calculateTrip(String country, int money) {
        StringBuilder result = new StringBuilder();
        result.append(country).append(" has ");

        for (int i = 0; i < list.size(); i++) {
            CountryModel countryModel = (CountryModel) list.get(i);
            if (countryModel.getName().equals(country)) {

                result.append(countryModel.getBorders().length)
                        .append(" neighbor countries (");
                for (String c : countryModel.getBorders()) {
                    result.append(c).append(" ");
                }

                int countTrip = money / countryModel.getBorders().length;
                int remainder = money % countryModel.getBorders().length;
                result.append(") and Angel can travel around them ").append(countTrip).append(" times");

                result.append("He will have ").append(remainder).append(" EUR leftover");

            }
        }
        //Bulgaria has 5 neighbor countries (TR, GR, MK, SR, RO)
        // and Angel can travel around them 2 times. He will have 200 EUR leftover.

        return result.toString();
    }
}
