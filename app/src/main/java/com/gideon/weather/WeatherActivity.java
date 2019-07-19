package com.gideon.weather;

import android.os.Bundle;

import com.gideon.weather.viewmodels.WeatherActivityViewModel;

import dagger.android.support.DaggerAppCompatActivity;

public class WeatherActivity extends DaggerAppCompatActivity {

    private WeatherActivityViewModel weatherActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
    }
}
