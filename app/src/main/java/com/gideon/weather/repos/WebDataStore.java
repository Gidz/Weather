package com.gideon.weather.repos;

import android.util.Log;

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
    public Observable<WeatherData> downloadWeatherData(String lat, String lon, boolean getLatest) {

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
                for( DailyData dailyData : dailyDataList) {
                    weatherDatabase.weatherDao().insertDailyData(dailyData);
                }

                weatherDatabase.weatherDao().insertCurrentData(currentData);

                /*Test if the data is porperly inserted or not*/
                Log.e(TAG, "onNext: Tried to insert the data into database");
                Log.e(TAG, "onNext: The daily database list is of size : "+dailyDataList.size());

                List<DailyData> retrievedDailyData = weatherDatabase.weatherDao().getDailyData();
                Log.e(TAG, "onNext: Size of the retrieved daily data is "+retrievedDailyData.size());

                List<CurrentData> retrievedCurrentData = weatherDatabase.weatherDao().getCurrentData();
                Log.e(TAG, "onNext: Size of the retrieved current data is "+retrievedDailyData.size());
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
