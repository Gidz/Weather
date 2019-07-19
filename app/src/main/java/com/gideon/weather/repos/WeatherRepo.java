package com.gideon.weather.repos;

import com.gideon.weather.models.WeatherData;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * This is the entry point for view models and the rest of the project to retrive weather related
 * data. This class makes a decision where to get the data from - web, local etc.,
 */
public class WeatherRepo implements RepoInterface {

    private WebDataStore webDataStore;
    private LocalDataStore localDataStore;

    @Inject
    public WeatherRepo(WebDataStore webDataStore, LocalDataStore localDataStore) {
        this.webDataStore = webDataStore;
        this.localDataStore = localDataStore;
    }

    @Override
    public Observable<WeatherData> downloadWeatherData(String lat, String lon) {
        return webDataStore.downloadWeatherData(lat,lon);
    }
}
