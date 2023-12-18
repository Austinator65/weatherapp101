package com.example.weatherapp.Service;

import java.io.IOException;

import lombok.Data;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;


import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.web.bind.annotation.GetMapping;

@Service
@Data

public class WeatherService {
    private String unit;

    public JSONObject getWeatherObject(String name) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.openweathermap.org/data/2.5/weather?q=" + name
                        + "&units="+getUnit()+"&appid=6bed260650ab58e2e87160501c177ba8")
                .build();

        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                assert response.body() != null;
                return new JSONObject(response.body().string());
            }
        } catch (IOException | JSONException e) {
            // handle exceptions
            e.printStackTrace();
        }

        return null;

    }

    public JSONArray getWeatherArray(String name) throws JSONException {

        return getWeatherObject(name).getJSONArray("weather");
    }

    public JSONObject getMainObject(String name) throws JSONException {
        return getWeatherObject(name).getJSONObject("main");
    }

    public JSONObject getWindObjewct(String name) throws JSONException {
        return getWeatherObject(name).getJSONObject("wind");
    }

    public JSONObject getCloudObject(String name) throws JSONException {
        return getWeatherObject(name).getJSONObject("cloud");
    }

    public JSONObject getSun(String name) throws JSONException {
        return getWeatherObject(name).getJSONObject("sys");
    }

}
