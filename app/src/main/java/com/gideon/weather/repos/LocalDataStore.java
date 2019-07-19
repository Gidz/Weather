package com.gideon.weather.repos;

import com.gideon.weather.models.WeatherData;

import io.reactivex.Observable;

public class LocalDataStore implements RepoInterface {
    @Override
    public Observable<WeatherData> downloadWeatherData(String lat, String lon) {
        return null;
    }
}
