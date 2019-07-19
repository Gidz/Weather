package com.gideon.weather.repos;

import android.content.SharedPreferences;
import android.util.Log;

import com.gideon.weather.models.WeatherData;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * This is the entry point for view models and the rest of the project to retrive weather related
 * data. This class makes a decision where to get the data from - web, local etc.,
 */
public class WeatherRepo implements RepoInterface {

    private String TAG = "WeatherRepo";

    private WebDataStore webDataStore;
    private LocalDataStore localDataStore;
    private SharedPreferences sharedPreferences;

    @Inject
    public WeatherRepo(WebDataStore webDataStore, LocalDataStore localDataStore, SharedPreferences sharedPreferences) {
        this.webDataStore = webDataStore;
        this.localDataStore = localDataStore;
        this.sharedPreferences = sharedPreferences;
    }

    @Override
    public Observable<WeatherData> downloadWeatherData(String lat, String lon, boolean getLatest) {

        boolean update = false;

        if(needsUpdate()) {
            update = true;
        }

        return localDataStore.downloadWeatherData(lat, lon, update);
    }

    private boolean needsUpdate(){
        int currentTime = (int) (System.currentTimeMillis() / 1000L);
        int lastUpdated;
        lastUpdated = sharedPreferences.getInt("last_updated", currentTime);

        if(lastUpdated == currentTime){
            //Need an update
            return true;
        }
        else if (currentTime - lastUpdated >= 3600){

            Log.e(TAG, "needsUpdate: Last updated "+String.valueOf(currentTime - lastUpdated));
            //Need an update
            return true;
        }
        else{
            //No need to update
            return false;
        }
    }
}
