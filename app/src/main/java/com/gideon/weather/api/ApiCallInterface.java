package com.gideon.weather.api;

import com.gideon.weather.models.WeatherData;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiCallInterface {

    /*Add all the APi calls here*/

    @GET("{coordinates}")
    Observable<WeatherData> getWeatherData(@Path("coordinates") String latitudeAndLongitude);

}
