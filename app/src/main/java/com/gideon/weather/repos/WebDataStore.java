package com.gideon.weather.repos;

import com.gideon.weather.api.ApiCallInterface;
import com.gideon.weather.models.CurrentData;
import com.gideon.weather.models.DailyData;
import com.gideon.weather.models.WeatherData;
import com.gideon.weather.room.WeatherDatabase;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * This class is responsible to retrieve the weather data from online source.
 * In this case, we will be retrieving data from Dark Sky API
 */
public class WebDataStore implements RepoInterface{

    public static String TAG = "WebDataStore";

    private ApiCallInterface apiCallInterface;
    private WeatherDatabase weatherDatabase;

    @Inject
    public WebDataStore(ApiCallInterface apiCallInterface, WeatherDatabase weatherDatabase){
        this.apiCallInterface = apiCallInterface;
        this.weatherDatabase = weatherDatabase;
    }

    @Override
    public Observable<WeatherData> downloadWeatherData(String lat, String lon) {
        Observable<WeatherData> weatherDataObservable = apiCallInterface.getWeatherData(lat+","+lon).subscribeOn(Schedulers.io());

        /*Before returning the observable, store it in the database*/
        weatherDataObservable.subscribe(new Observer<WeatherData>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(WeatherData weatherData) {
                //Delete all data
                weatherDatabase.weatherDao().nukeCurrentDataTable();
                weatherDatabase.weatherDao().nukeDailyDataTable();

                /*Get the data to be stored in the table*/
                List<DailyData> dailyDataList = weatherData.getDaily().getData() ;
                CurrentData currentData = weatherData.getCurrentData();

                /*Store in the table*/
                for( DailyData dailyData : dailyDataList){
                    weatherDatabase.weatherDao().insertDailyData(dailyData);
                }

                weatherDatabase.weatherDao().insertCurrentData(currentData);
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
}
