package com.example.deliveryservice.utils;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class Geocoding {
    public GeoApiContext context;
    public Geocoding(){
        context = new GeoApiContext.Builder().apiKey("AIzaSyBtsEgWHpfub3h6pMRrcRoS0h8631JAT8c").build();
    }
    public List<Double> geocoding(String address) throws IOException, InterruptedException, ApiException {
        List<Double> coordinates = new ArrayList<>();
        System.out.println("envoking geocoding...");
        GeocodingResult[] results =  GeocodingApi.geocode(context,
                address).await();
        //geocoding is returning gson which is a java object in form of json. It can be converted back to json using gson.toJson()
        //Gson gson = new GsonBuilder().setPrettyPrinting().create();
        //System.out.println(gson.toJson(results[0].addressComponents));
        Double latitude = results[0].geometry.location.lat;
        Double longitude = results[0].geometry.location.lng;
        System.out.println("Got your address:");
        System.out.println(latitude);
        System.out.println(longitude);
        coordinates.add(longitude);
        coordinates.add(latitude);
        System.out.println(coordinates);

        return coordinates;
    }
    // Invoke .shutdown() after application is done making requests
    @PreDestroy
    private void closeConnection(){
        context.shutdown();
    }
}
