package com.gideon.weather.adapters;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gideon.weather.models.DailyData;

import java.util.List;

/**
 * We will need to display a Recyclerview with daily forecast.
 * That's when this adapter comes in handy.
 */
//TODO: Extend RecyclerView.Adapter
public class DailyForecastAdapter extends RecyclerView.Adapter<DailyForecastAdapter.DailyForecastAdapterViewHolder> {

    private List<DailyData> dailyDataList;

    @NonNull
    @Override
    public DailyForecastAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull DailyForecastAdapterViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class DailyForecastAdapterViewHolder extends RecyclerView.ViewHolder{
        public DailyForecastAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public void setDailyDataList(List<DailyData> dailyDataList) {
        this.dailyDataList = dailyDataList;
    }
}
