package com.gideon.weather.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.gideon.weather.models.CurrentData;
import com.gideon.weather.models.DailyData;

@Database(entities = {CurrentData.class, DailyData.class}, version = 1, exportSchema = false)
public abstract class WeatherDatabase extends RoomDatabase {
    public abstract WeatherDao weatherDao();
}
