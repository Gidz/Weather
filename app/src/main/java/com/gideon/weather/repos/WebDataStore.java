package com.gideon.weather.repos;

import com.gideon.weather.models.WeatherData;

import io.reactivex.Observable;

public class WebDataStore implements RepoInterface{

    public static String TAG = "WebDataStore";
    @Override
    public Observable<WeatherData> downloadWeatherData(String lat, String lon) {
        return null;
    }
}
