package com.gideon.weather.di;

import com.gideon.weather.WeatherActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilderModule {
    //Append all new Activities here. Use the same format as activities which are already here
    @ContributesAndroidInjector
    abstract WeatherActivity getWeatherActivity();
}
