package com.gideon.weather.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class DailyData {

    @SerializedName("time")
    @Expose
    private int time;

    @SerializedName("summary")
    @Expose
    private String summary;

    @SerializedName("icon")
    @Expose
    private String icon;

    @SerializedName("temperatureMin")
    @Expose
    private float temperatureMin;

    @SerializedName("temperatureMax")
    @Expose
    private float temperatureMax;

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public float getTemperatureMin() {
        return temperatureMin;
    }

    public void setTemperatureMin(float temperatureMin) {
        this.temperatureMin = temperatureMin;
    }

    public float getTemperatureMax() {
        return temperatureMax;
    }

    public void setTemperatureMax(float temperatureMax) {
        this.temperatureMax = temperatureMax;
    }
}
