package com.weather.weather.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.weather.weather.R;
import com.weather.weather.model.WeatherJSON;

import java.util.ArrayList;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.WeatherHolder> {


    private ArrayList<WeatherJSON> mData;
    private Activity mActiviy;

    public WeatherAdapter(ArrayList<WeatherJSON> data, Activity activiy) {
        this.mData = data;
        this.mActiviy = activiy;
    }


    @Override
    public WeatherHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_weather, parent, false);
        return new WeatherHolder(view);

    }

    @Override
    public void onBindViewHolder(WeatherHolder holder, int position) {
        WeatherJSON weather = mData.get(position);

       // holder.setId(weather.getId());
        holder.setMax(weather.getMax());
        holder.setDay(weather.getDay());
        holder.setName(weather.getName());
        //holder.setPressure(weather.getPressure());
        //holder.setHumidity(weather.getHumidity());
       // holder.setMain(weather.getMainWeather());
        holder.setDescription(weather.getDescription());
       // holder.setWindSpeed(weather.getSpeed());
        //Picasso il folosim pentru a incarca poza in ImageView
        Picasso.with(mActiviy).load(weather.getIdIcon()).into(holder.weatherImage);

    }

    @Override
    public int getItemCount() {
        if (mData == null)
            return 0;
        return mData.size();
    }

    public class WeatherHolder extends RecyclerView.ViewHolder {

        ImageView weatherImage;
        TextView weatherId;
        TextView weatherMaxTemperature;
        TextView weatherDayTemperature;
        TextView weatherName;
        TextView weatherPressure;
        TextView weatherHumidity;
        TextView weatherMain;
        TextView weatherDescription;
        TextView weatherWindSpeed;


        public WeatherHolder(View itemView) {
            super(itemView);

            weatherImage = (ImageView) itemView.findViewById(R.id.image);
           // weatherId = (TextView) itemView.findViewById(R.id.id);
            weatherMaxTemperature = (TextView) itemView.findViewById(R.id.maxTempDay);
            weatherDayTemperature = (TextView) itemView.findViewById(R.id.dayTemp);
            weatherName = (TextView) itemView.findViewById(R.id.name);
           // weatherPressure = (TextView) itemView.findViewById(R.id.pressure);
          //  weatherHumidity = (TextView) itemView.findViewById(R.id.humidity);
           // weatherMain = (TextView) itemView.findViewById(R.id.main);
            weatherDescription = (TextView) itemView.findViewById(R.id.description);
           // weatherWindSpeed = (TextView) itemView.findViewById(R.id.windSpeed);


        }


        public void setId(String weatherId) {
            this.weatherId.setText(weatherId);
        }

        public void setMax(String weatherMaxTemperature) {
            this.weatherMaxTemperature.setText(weatherMaxTemperature);
        }



        public void setDay(String weatherDayTemperature) {
            this.weatherDayTemperature.setText(weatherDayTemperature);
        }

        public void setName(String weatherName) {
            this.weatherName.setText(weatherName);
        }

        public void setPressure(String weatherPressure) {
            this.weatherPressure.setText(weatherPressure);
        }

        public void setHumidity(String weatherHumidity) {
            this.weatherHumidity.setText(weatherHumidity);
        }

        public void setMain(String weatherMain) {
            this.weatherMain.setText(weatherMain);
        }

        public void setDescription(String weatherDescription) {
            this.weatherDescription.setText(weatherDescription);
        }

        public void setWindSpeed(String weatherWindSpeed) {
            this.weatherWindSpeed.setText(weatherWindSpeed);
        }


    }
}
