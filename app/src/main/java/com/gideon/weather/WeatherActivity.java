package com.gideon.weather;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.lifecycle.ViewModelProviders;

import com.gideon.weather.viewmodels.ViewModelProviderFactory;
import com.gideon.weather.viewmodels.WeatherActivityViewModel;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class WeatherActivity extends DaggerAppCompatActivity {

    private WeatherActivityViewModel weatherActivityViewModel;

    String lat;
    String lon;


    //Dependency injection
    @Inject
    ViewModelProviderFactory viewModelProviderFactory;

    @Inject
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        //Bind the View model to this activity
        weatherActivityViewModel = ViewModelProviders.of(this, viewModelProviderFactory).get(WeatherActivityViewModel.class);

        //Check the location preferences
        checkLocationPreferences();
    }

    private void checkLocationPreferences() {
        // NYC Coordinates : 40.7128° N, 74.0060° W

        //If the requested value wasn't present i.e., if lat or lon value wasn't in the
        // shared preferences, "UNKNOWN" will be returned as a value.
        lat = sharedPreferences.getString("lat", "UNKNOWN");
        lon = sharedPreferences.getString("lon", "UNKNOWN");

        //If the location wasn't set, set the location first.
        //Else, observer for any data changes. (Rx)
        if (lat.equals("UNKNOWN") || lon.equals("UNKNOWN")) {
            //The location wasn't set before. Set it.
            setLocation();
        } else {
            //The location was set before
            observeDataChanges();
        }
    }

    private void observeDataChanges() {
    }

    private void setLocation() {
    }
}
