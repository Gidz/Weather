package com.gideon.weather.di;

import com.gideon.weather.api.ApiCallInterface;
import com.gideon.weather.repos.WeatherRepo;
import com.gideon.weather.repos.WebDataStore;

import javax.inject.Singleton;

import dagger.Provides;

public class RepoModule {
    /*Repo*/
    @Singleton
    @Provides
    WeatherRepo provideWeatherRepo(WebDataStore webDataStore){
        return new WeatherRepo(webDataStore);
    }

    /*Data stores*/
    @Provides
    WebDataStore providesWebDataStore(ApiCallInterface apiCallInterface){
        return new WebDataStore(apiCallInterface);
    }
}
