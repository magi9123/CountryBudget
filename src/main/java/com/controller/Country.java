package com.controller;

import com.SpringBootDemoApplication;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.LinkedList;
import java.util.List;

@RestController
//@RequestMapping("api/countries")
public class Country {

    @RequestMapping("/")
    public String getCountries(Model model) throws IOException, InterruptedException {
        model.addAttribute ( "msg", "test @GetMapping Notes");

//        ObjectMapper mapper = new ObjectMapper();
//        try (CloseableHttpClient client = HttpClients.createDefault()) {
//            HttpGet request = new HttpGet("https://restcountries.eu/rest/v2/all");
//            SpringBootDemoApplication.APOD response = client.execute(request,
//                    httpResponse -> mapper.readValue(httpResponse.getEntity().getContent(), SpringBootDemoApplication.APOD.class));
//            System.out.println(response);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://restcountries.eu/rest/v2/all?fields=name;borders"))
                .header("x-rapidapi-key", "0dc0ec0a8cmsh64e7423f3a7a16ep1cb06ejsn41ccb9fb0748")
                .header("x-rapidapi-host", "andruxnet-world-cities-v1.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        return "success";

        //  return new LinkedList<>();
    }

    @GetMapping("/{country}")
    public void getCountry(@PathVariable(name = "country") String coutry, @PathVariable(name = "money") int money) {

    }
}
