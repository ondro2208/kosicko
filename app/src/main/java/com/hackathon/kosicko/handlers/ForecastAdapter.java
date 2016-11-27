package com.hackathon.kosicko.handlers;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hackathon.kosicko.R;
import com.hackathon.kosicko.activities.WeatherActivity;
import com.hackathon.kosicko.classes.models.shared.Forecast;
import com.hackathon.kosicko.classes.models.shared.Forecastday;
import com.hackathon.kosicko.classes.models.shared.Forecastday_;

import java.util.List;

import static java.security.AccessController.getContext;

/**
 * Created by developer on 27.11.2016.
 */

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView dayOfWeek, date, dtTemp, ntTemp;
        ImageView img;

        public ViewHolder(View itemView) {
            super(itemView);
            dayOfWeek = (TextView)itemView.findViewById(R.id.day_of_week);
            date = (TextView)itemView.findViewById(R.id.date);
            dtTemp = (TextView)itemView.findViewById(R.id.dt_temp);
            ntTemp = (TextView)itemView.findViewById(R.id.nt_temp);
            img = (ImageView)itemView.findViewById(R.id.weather_desc_icon);
        }
    }

    private Forecast multiDayForecast;

    public ForecastAdapter(Forecast multiDayForecast) {
        this.multiDayForecast = multiDayForecast;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.weather_column, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Forecastday forecastdayDesc = multiDayForecast.txtForecast.forecastday.get(position);
        Forecastday_ forecastDetails = multiDayForecast.simpleforecast.forecastday.get(position);

        holder.dayOfWeek.setText(forecastDetails.date.weekdayShort);
        holder.date.setText(forecastDetails.date.day + "/" + forecastDetails.date.month);
        holder.dtTemp.setText(String.format("%s °C", forecastDetails.high.celsius));
        holder.ntTemp.setText(String.format("%s °C", forecastDetails.low.celsius));
        String currentIcon = WeatherActivity.descImgMap.get(forecastDetails.icon);
        if (forecastDetails.icon.startsWith("nt_")){
            currentIcon = String.format("nt_%s", WeatherActivity.descImgMap.get(forecastDetails.icon));
        }
        int resId =  holder.img.getResources().getIdentifier(
                currentIcon,
                "drawable",
                holder.img.getResources().getResourcePackageName(R.drawable.weather)
        );
        holder.img.setImageDrawable(holder.img.getResources().getDrawable(resId));
    }

    @Override
    public int getItemCount() {
        return multiDayForecast.simpleforecast.forecastday.size();
    }
}
