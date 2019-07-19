package com.gideon.weather.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gideon.weather.R;
import com.gideon.weather.models.DailyData;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.gideon.weather.ui.Icons.getWeatherIconName;

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
        //Get the layout inflater
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        //Inflate the user item view
        View view = layoutInflater.inflate(R.layout.daily_data_list_item, parent, false);

        //Return this view
        return new DailyForecastAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DailyForecastAdapterViewHolder holder, int position) {
        DailyData dailyData = dailyDataList.get(position);

        //Set the data
        holder.summaryTextView.setText(String.valueOf(dailyData.getSummary()));

        //Set the min and max temperatures for that particular day. \u00B0 is unicode for degrees symbol
        holder.maxTempTextView.setText("Max : "+String.valueOf((int)dailyData.getTemperatureMax())+"\u00B0");
        holder.minTempTextView.setText("Min : "+String.valueOf((int) dailyData.getTemperatureMin())+"\u00B0");


        String day = getDayOfWeek(dailyData.getTime());

        //The first 2 items of the list will be for today and tomorrow.
        if(position == 0) { day = "Today"; }
        if(position == 1){ day = "Tomorrow"; }

        //Set the day
        holder.dayTextView.setText(day);

        //Set the icon
        holder.dailyWeatherIcon.setImageResource(getWeatherIconName(dailyData.getIcon()));

    }

    @Override
    public int getItemCount() {
        return dailyDataList.size();
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


    //Helper methods
    String getDayOfWeek(int unix_date){

        Date date = new Date((long) unix_date * 1000);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DAY_OF_WEEK);

        switch (day) {
            case 1:
                return "Sunday";
            case 2:
                return "Monday";
            case 3:
                return "Tuesday";
            case 4:
                return "Wednesday";
            case 5:
                return "Thursday";
            case 6:
                return "Friday";
            case 7:
                return "Saturday";
            default:
                return "ERROR";
        }
    }

    //Setters
    public void setDailyDataList(List<DailyData> dailyDataList) {
        this.dailyDataList = dailyDataList;
    }
}
