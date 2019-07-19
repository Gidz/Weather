package com.gideon.weather.viewmodels;

import androidx.lifecycle.ViewModel;

import com.gideon.weather.repos.WeatherRepo;

import javax.inject.Inject;

public class WeatherActivityViewModel extends ViewModel {

    private static String TAG = "WeatherActivityViewModel";

    private WeatherRepo weatherRepo;

    @Inject
    public WeatherActivityViewModel(WeatherRepo weatherRepo) {
        this.weatherRepo = weatherRepo;
    }
}
