package com.gideon.weather.repos;

import com.gideon.weather.api.ApiCallInterface;
import com.gideon.weather.models.WeatherData;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * This class is responsible to retrieve the weather data from online source.
 * In this case, we will be retrieving data from Dark Sky API
 */
public class WebDataStore implements RepoInterface{

    public static String TAG = "WebDataStore";

    private ApiCallInterface apiCallInterface;

    @Inject
    public WebDataStore(ApiCallInterface apiCallInterface){
        this.apiCallInterface = apiCallInterface;
    }

    @Override
    public Observable<WeatherData> downloadWeatherData(String lat, String lon) {
        Observable<WeatherData> weatherDataObservable = apiCallInterface.getWeatherData(lat+","+lon).subscribeOn(Schedulers.io());
        return weatherDataObservable;
    }
}
