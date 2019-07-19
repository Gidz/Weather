package com.gideon.weather.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WeatherData {
    @SerializedName("latitude")
    @Expose
    private float latitude;

    @SerializedName("longitude")
    @Expose
    private float longitude;

    @SerializedName("currently")
    @Expose
    private CurrentData currentData;

    @SerializedName("daily")
    @Expose
    private Daily daily;

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public CurrentData getCurrentData() {
        return currentData;
    }

    public void setCurrentData(CurrentData currentData) {
        this.currentData = currentData;
    }

    public Daily getDaily() {
        return daily;
    }

    public void setDaily(Daily daily) {
        this.daily = daily;
    }
}
