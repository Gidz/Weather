package com.gideon.weather;

import android.os.Bundle;

import dagger.android.support.DaggerAppCompatActivity;

public class WeatherActivity extends DaggerAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
    }
}
