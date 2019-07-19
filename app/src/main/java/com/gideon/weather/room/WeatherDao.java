package com.gideon.weather.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.gideon.weather.models.CurrentData;
import com.gideon.weather.models.DailyData;

import java.util.List;

import io.reactivex.Observable;

@Dao
public interface WeatherDao {
    @Query("SELECT * FROM daily_data")
    List<DailyData> getDailyData();

    @Query("SELECT * FROM current_data")
    List<CurrentData> getCurrentData();


    @Query("SELECT * FROM daily_data")
    Observable<List<DailyData>> getDailyDataObservable();

    @Query("SELECT * FROM current_data")
    Observable<CurrentData> getCurrentDataObservable();

    @Insert
    void insertCurrentData(CurrentData currentData);

    @Insert
    void insertDailyData(DailyData dailyDataList);

    //Deletes the contents of entire table
    @Query("DELETE FROM daily_data")
    public void nukeDailyDataTable();


    //Deletes the contents of entire table
    @Query("DELETE FROM current_data")
    public void nukeCurrentDataTable();
}
