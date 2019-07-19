package com.gideon.weather.api;

import com.gideon.weather.models.WeatherData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiCallInterface {

    /*Add all the APi calls here*/

    @GET("{coordinates}")
    Call<WeatherData> getWeatherData(@Path("coordinates") String latitudeAndLongitude);

}
