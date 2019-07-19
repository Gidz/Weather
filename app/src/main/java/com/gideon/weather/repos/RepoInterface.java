package com.gideon.weather.repos;

import com.gideon.weather.models.WeatherData;

import io.reactivex.Observable;

/**
 * There might be lot of ways to retrieve data. For example, data can be fetched from different
 * websites, or can be fetched from a local database or even a file. Whatever that might be,
 * this interface makes sure that all the DATA STORES provide this method which will be used by the
 * view model.
 */
public interface RepoInterface {
    public Observable<WeatherData> downloadWeatherData(String lat, String lon);
}
