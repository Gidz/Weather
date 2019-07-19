package com.gideon.weather.di.viewmodels;

import androidx.lifecycle.ViewModel;

import com.gideon.weather.viewmodels.WeatherActivityViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

//Contains methods which help in injecting view models
@Module
public abstract class ViewModelsModule {
    //Append all the View models below. Follow the same pattern as the view models already here
    @Binds
    @IntoMap
    @ViewModelKey(WeatherActivityViewModel.class)
    public abstract ViewModel bindWeatherActivityViewModel(WeatherActivityViewModel viewModel);
}
