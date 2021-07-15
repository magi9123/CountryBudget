package com.services.impl;

import com.google.gson.GsonBuilder;
import com.google.gson.internal.LinkedTreeMap;
import com.models.CountryFormModel;
import com.services.CountryService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

@Service
public class CountryServiceImpl implements CountryService {

    private static List<? extends Object> list = new LinkedList<>();

    @Override
    public List<? extends Object> getAllCountries() throws IOException, InterruptedException {

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
    public String calculateTrip(CountryFormModel countryFormModel) {
        String country = checkForJunckSymbol(countryFormModel.getCountryName());
        StringBuilder result = new StringBuilder();
        result.append(country).append(" has ");

        boolean findMatch = false;
        for (int i = 0; i < list.size() && !findMatch; i++) {
            LinkedTreeMap<String, String[]> countryModel = (LinkedTreeMap<String, String[]>) list.get(i);
            if (countryModel.containsValue(country)) {

                for (Map.Entry<String, String[]> entry : countryModel.entrySet()) {
                    if (entry.getKey().equals("borders")) {
                        int countNeighbor = getNumberOfBorders(entry);

                        if (countNeighbor != 0) {
                            result.append(countNeighbor).append(" neighbor countries ")
                                    .append(entry.getValue() + "").append(" ");

                            int countTrip = countryFormModel.getMoney() / (countNeighbor * countryFormModel.getCostCountry());
                            int remainder = countryFormModel.getMoney() % (countNeighbor * countryFormModel.getCostCountry());
                            result.append(" and Angel can travel around them ").append(countTrip).append(" times. ");

                            if (remainder != 0) {
                                result.append("He will have ").append(remainder + " ").append(countryFormModel.getCurrency().toUpperCase()).append(" leftover.");
                            }
                        } else {
                            result.append("'The Country Neighbours Tour' is not possible.");
                        }

                        findMatch = true;
                        break;
                    }
                }
            }
        }
        return result.toString();
    }

    private String checkForJunckSymbol(String country) {
        return country = country.replace("+", " ");
    }

    private int getNumberOfBorders(Map.Entry<String, String[]> entry) {
        String[] countNeighbor = (entry.getValue() + "").split(",");
        return countNeighbor.length;
    }

    public static List<? extends Object> getList() {
        return list;
    }

}
