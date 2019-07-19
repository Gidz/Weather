package com.gideon.weather.di;

import com.gideon.weather.api.ApiCallInterface;
import com.gideon.weather.repos.LocalDataStore;
import com.gideon.weather.repos.WeatherRepo;
import com.gideon.weather.repos.WebDataStore;
import com.gideon.weather.room.WeatherDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepoModule {
    /*Repo*/
    @Singleton
    @Provides
    WeatherRepo provideWeatherRepo(WebDataStore webDataStore, LocalDataStore localDataStore){
        return new WeatherRepo(webDataStore, localDataStore);
    }

    /*Data stores*/
    @Provides
    WebDataStore providesWebDataStore(ApiCallInterface apiCallInterface, WeatherDatabase weatherDatabase){
        return new WebDataStore(apiCallInterface, weatherDatabase);
    }

    @Provides
    LocalDataStore providesLocalDataStore(){
        return new LocalDataStore();
    }
}
