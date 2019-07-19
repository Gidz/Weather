package com.gideon.weather;

import android.os.Bundle;

import androidx.lifecycle.ViewModelProviders;

import com.gideon.weather.viewmodels.ViewModelProviderFactory;
import com.gideon.weather.viewmodels.WeatherActivityViewModel;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class WeatherActivity extends DaggerAppCompatActivity {

    private WeatherActivityViewModel weatherActivityViewModel;


    //Dependency injection
    @Inject
    ViewModelProviderFactory viewModelProviderFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        //Bind the View model to this activity
        weatherActivityViewModel = ViewModelProviders.of(this, viewModelProviderFactory).get(WeatherActivityViewModel.class);
    }
}
