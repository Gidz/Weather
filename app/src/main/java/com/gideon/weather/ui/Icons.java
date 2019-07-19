package com.gideon.weather.ui;

import com.gideon.weather.R;

public class Icons {
    public static int getWeatherIconName(String icon_description){
        switch(icon_description){

            case "clear-day":
                return R.drawable.weather_sun;

            case "clear-night":
                return R.drawable.weather_clear_night;

            case "rain":
                return R.drawable.weather_rain;

            case "snow":
                return R.drawable.weather_snow;

            case "sleet":
                return R.drawable.weather_sleet;

            case "wind":
                return R.drawable.weather_wind;

            case "fog":
                return R.drawable.weather_fog;

            case "cloudy":
                return R.drawable.weather_cloud;

            case "partly-cloudy-day":
                return R.drawable.weather_cloud;

            // Darksky Documentation specified that this can be treated as Clear day
            case "partly-cloudy-night":
                return R.drawable.weather_sun;

            default:
                //Display this by default
                return R.drawable.weather_sun;
        }
    }
}

