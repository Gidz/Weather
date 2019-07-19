package com.gideon.weather.repos;

import com.gideon.weather.models.CurrentData;
import com.gideon.weather.models.Daily;
import com.gideon.weather.models.DailyData;
import com.gideon.weather.models.WeatherData;
import com.gideon.weather.room.WeatherDatabase;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class LocalDataStore implements RepoInterface {

    private WeatherDatabase weatherDatabase;
    private WebDataStore webDataStore;

    public LocalDataStore(WeatherDatabase weatherDatabase, WebDataStore webDataStore){
        this.weatherDatabase = weatherDatabase;
        this.webDataStore = webDataStore;
    }

    @Override
    public Observable<WeatherData> downloadWeatherData(String lat, String lon, boolean getLatest) {

        if (getLatest){
            return webDataStore.downloadWeatherData(lat, lon, true);
        }

        /*View model only accepts weather data observables. So create a new WeatherData object,
         * set all the necessary data into it. Make an Observable from that and return.
         * This is a QUICK FIX to avoid major restructuring of ViewModel code.
         * */

        WeatherData weatherData = getDummyWeatherDataObject();
        Observable<WeatherData> weatherDataObservable = Observable.just(weatherData);

        Observable<List<DailyData>> dailyDataListObservable;
        Observable<CurrentData> currentDataObservable;

        dailyDataListObservable = weatherDatabase.weatherDao().getDailyDataObservable();
        currentDataObservable = weatherDatabase.weatherDao().getCurrentDataObservable();

        dailyDataListObservable.subscribe(new Observer<List<DailyData>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<DailyData> dailyDataList) {
                //First create a Daily object
                Daily daily = new Daily();
                daily.setData(dailyDataList);

                //Set the Daily object in the WeatherData
                weatherData.setDaily(daily);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

        currentDataObservable.subscribe(new Observer<CurrentData>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(CurrentData currentData) {

                //Set the CurrentData object in the WeatherData
                weatherData.setCurrentData(currentData);

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

        return weatherDataObservable;
    }

    WeatherData getDummyWeatherDataObject(){
        WeatherData weatherData = new WeatherData();

        //First create a Daily object
        Daily daily = new Daily();
        daily.setData(new ArrayList<DailyData>());

        //Set the Daily object in the WeatherData
        weatherData.setDaily(daily);

        //Set the CurrentData object in the WeatherData
        CurrentData currentData = new CurrentData();
        currentData.setSummary("Something went wrong");
//        currentData.setIcon();
        currentData.setTemperature(0);
        currentData.setTime((int) (System.currentTimeMillis() / 1000L));

        weatherData.setCurrentData(currentData);

        return weatherData;

    }
}
