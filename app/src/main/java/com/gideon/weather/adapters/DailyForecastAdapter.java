package com.gideon.weather.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gideon.weather.R;
import com.gideon.weather.models.DailyData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

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
        @BindView(R.id.summaryTextView)
        TextView summaryTextView;

        @BindView(R.id.maxTempTextView)
        TextView maxTempTextView;

        @BindView(R.id.minTempTextView)
        TextView minTempTextView;

        @BindView(R.id.dayTextView)
        TextView dayTextView;

        @BindView(R.id.dailyWeatherIcon)
        ImageView dailyWeatherIcon;

        public DailyForecastAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void setDailyDataList(List<DailyData> dailyDataList) {
        this.dailyDataList = dailyDataList;
    }
}
