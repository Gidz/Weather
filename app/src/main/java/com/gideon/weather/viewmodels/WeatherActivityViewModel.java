package com.gideon.weather.viewmodels;

import androidx.lifecycle.ViewModel;

import com.gideon.weather.models.WeatherData;
import com.gideon.weather.repos.WeatherRepo;

import javax.inject.Inject;

import io.reactivex.Observable;

public class WeatherActivityViewModel extends ViewModel {

    private static String TAG = "WeatherActivityViewModel";

    private WeatherRepo weatherRepo;
    private Observable<WeatherData> weatherDataObservable;

    @Inject
    public WeatherActivityViewModel(WeatherRepo weatherRepo) {
        this.weatherRepo = weatherRepo;
    }
    
    public void downloadWeatherData(String lat, String lon) {
        weatherDataObservable = weatherRepo.downloadWeatherData(lat, lon);
    }
}
