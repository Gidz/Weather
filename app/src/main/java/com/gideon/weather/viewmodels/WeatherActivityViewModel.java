package com.gideon.weather.viewmodels;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.gideon.weather.models.WeatherData;
import com.gideon.weather.repos.WeatherRepo;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class WeatherActivityViewModel extends ViewModel {

    private static String TAG = "WeatherActivityViewModel";

    private WeatherRepo weatherRepo;
    private Observable<WeatherData> weatherDataObservable;
    private MutableLiveData<WeatherData> weatherDataMutableLiveData;

    @Inject
    public WeatherActivityViewModel(WeatherRepo weatherRepo) {
        this.weatherRepo = weatherRepo;
        weatherDataMutableLiveData = new MutableLiveData<WeatherData>();
    }

    public void downloadWeatherData(String lat, String lon) {
        weatherDataObservable = weatherRepo.downloadWeatherData(lat, lon);
        weatherDataObservable.subscribe(new Observer<WeatherData>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(WeatherData weatherData) {
                weatherDataMutableLiveData.postValue(weatherData);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: "+e.fillInStackTrace().toString());
            }

            @Override
            public void onComplete() {

            }
        });
    }

    //Getters
    public MutableLiveData<WeatherData> getWeatherDataMutableLiveData() {
        return weatherDataMutableLiveData;
    }
}

