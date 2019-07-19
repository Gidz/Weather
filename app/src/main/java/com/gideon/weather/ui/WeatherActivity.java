package com.gideon.weather.ui;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gideon.weather.R;
import com.gideon.weather.adapters.DailyForecastAdapter;
import com.gideon.weather.models.DailyData;
import com.gideon.weather.models.WeatherData;
import com.gideon.weather.viewmodels.ViewModelProviderFactory;
import com.gideon.weather.viewmodels.WeatherActivityViewModel;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;

import static com.gideon.weather.ui.Icons.getWeatherIconName;

public class WeatherActivity extends DaggerAppCompatActivity {

    public static String TAG = "WeatherActivity";

    private static final int LOCATION_PERMISSION_CODE = 101;

    private WeatherActivityViewModel weatherActivityViewModel;
    private MutableLiveData<WeatherData> weatherDataMutableLiveData;

    //Latitude and longitude
    String lat;
    String lon;

    //Dependency injection
    @Inject
    ViewModelProviderFactory viewModelProviderFactory;

    @Inject
    SharedPreferences sharedPreferences;

    @Inject
    DailyForecastAdapter dailyForecastAdapter;

    //View Binding
    @BindView(R.id.currentTemperatureTextView)
    TextView currentTemperatureTextView;

    @BindView(R.id.weatherIcon)
    ImageView weatherIcon;

    @BindView(R.id.dailyForecastRecyclerList)
    RecyclerView dailyForecastRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        //Bind the View model to this activity
        weatherActivityViewModel = ViewModelProviders.of(this, viewModelProviderFactory).get(WeatherActivityViewModel.class);

        //Get the mutable live data object to observe
        weatherDataMutableLiveData = weatherActivityViewModel.getWeatherDataMutableLiveData();

        //Bind all the views and such
        ButterKnife.bind(this);

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

    private void displayRecyclerView(List<DailyData> data) {
        //Set the data
        dailyForecastAdapter.setDailyDataList(data);

        //RecyclerView for daily forecast
        dailyForecastRecyclerView.setAdapter(dailyForecastAdapter);

        //Set the layout manager
        dailyForecastRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void observeDataChanges() {
        //Download data
        weatherActivityViewModel.downloadWeatherData(lat, lon);
        weatherDataMutableLiveData.observe(this, new Observer<WeatherData>() {
            @Override
            public void onChanged(WeatherData weatherData) {
                currentTemperatureTextView.setText(String.valueOf((int) weatherData.getCurrentData().getTemperature()) + "\u00B0");
                weatherIcon.setImageResource(getWeatherIconName(weatherData.getCurrentData().getIcon()));

                displayRecyclerView(weatherData.getDaily().getData());
                Log.e(TAG, "Downloaded the data : "+weatherData.getCurrentData().getTemperature());
            }
        });
    }

    private void setLocation() {
        /*To set the location, we first need to check if the Location permission were enabled.
        * If they weren't enabled, ask for permissions. Else, go ahead and set the location*/
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            askLocationPermission();
        } else {
            final LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
            LocationListener locationListener = new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {

                    //Save the new location in Shared preferences
                    sharedPreferences.edit().putString("lat", String.valueOf(location.getLatitude())).commit();
                    sharedPreferences.edit().putString("lon", String.valueOf(location.getLongitude())).commit();

                    Log.d(TAG, "Just updated the location to : " + location.getLatitude() + "," + location.getLongitude());

                    //Update the local values as well. Since these are the values which will be
                    //used to query the View model
                    lat = sharedPreferences.getString("lat", "UNKNOWN");
                    lon = sharedPreferences.getString("lon", "UNKNOWN");

                    //Now observe for data changes with the new location
                    observeDataChanges();

                    //It is no longer necessary to keep listening for location updates.
                    //Stop observing to save battery.
                    locationManager.removeUpdates(this);
                }

                @Override
                public void onStatusChanged(String s, int i, Bundle bundle) {

                }

                @Override
                public void onProviderEnabled(String s) {

                }

                @Override
                public void onProviderDisabled(String s) {

                }
            };

            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10, locationListener);
        }
    }

    private void askLocationPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                //Show explanation. Maybe code this part later.
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        LOCATION_PERMISSION_CODE);
            }
        }
    }
}
