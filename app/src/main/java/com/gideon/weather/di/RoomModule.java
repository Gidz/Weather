package com.gideon.weather.di;

import androidx.room.Room;

import com.gideon.weather.api.ApiCallInterface;
import com.gideon.weather.base.App;
import com.gideon.weather.repos.LocalDataStore;
import com.gideon.weather.repos.WebDataStore;
import com.gideon.weather.room.WeatherDatabase;

import dagger.Provides;

import static com.gideon.weather.base.Constants.DB_NAME;

public class RoomModule {
    /*Database*/
    @Provides
    WeatherDatabase provideWeatherDatabase(App app){
        return Room.databaseBuilder(app.getApplicationContext(),
                WeatherDatabase.class,
                DB_NAME).fallbackToDestructiveMigration().build();
    }

    /*Data stores*/
    @Provides
    WebDataStore providesWebDataStore(ApiCallInterface apiCallInterface){
        return new WebDataStore(apiCallInterface);
    }

    @Provides
    LocalDataStore providesLocalDataStore(){
        return new LocalDataStore();
    }
}
