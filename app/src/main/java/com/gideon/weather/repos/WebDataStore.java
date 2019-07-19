package com.gideon.weather.repos;

import com.gideon.weather.api.ApiCallInterface;
import com.gideon.weather.models.WeatherData;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * This class is responsible to retrieve the weather data from online source.
 * 
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
        return null;
    }
}
