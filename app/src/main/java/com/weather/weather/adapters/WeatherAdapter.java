package com.weather.weather.adapters;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.weather.weather.R;
import com.weather.weather.ShowDetailedActivityInfo;
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
        holder.myLayout.setBackgroundColor(Color.BLACK);
        holder.myLayout.getBackground().setAlpha(180);
        holder.setMin(weather.getMin());
        holder.setMax(weather.getMax());
        holder.setDay(weather.getDay());
        holder.setName(weather.getName());
        holder.setDescription(weather.getDescription());
        holder.setPressure(weather.getPressure());
        holder.setHumidity(weather.getHumidity());

        //pana aici e ok
        holder.setId(weather.getId());

        //  holder.setMain(weather.getMainWeather());

       holder.setWindSpeed(weather.getSpeed());
        //Picasso il folosim pentru a incarca poza in ImageView
        Picasso.with(mActiviy).load(weather.getIdIcon()).into(holder.weatherImage);
    }

    @Override
    public int getItemCount() {
        if (mData == null)
            return 0;
        return mData.size();
    }

    public class WeatherHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

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
        LinearLayout myLayout;

        String myPressure;
        String myHumidity;
        //String myMain;
        String myWindSpeed;
        String myId;
        String myMin;


        public WeatherHolder(View itemView) {
            super(itemView);

            myLayout = (LinearLayout) itemView.findViewById(R.id.layout_recycler);
            weatherImage = (ImageView) itemView.findViewById(R.id.image);
            weatherMaxTemperature = (TextView) itemView.findViewById(R.id.maxTempDay);
            weatherDayTemperature = (TextView) itemView.findViewById(R.id.dayTemp);
            weatherName = (TextView) itemView.findViewById(R.id.name);
            weatherDescription = (TextView) itemView.findViewById(R.id.description);
            // weatherId = (TextView) itemView.findViewById(R.id.id);
            // weatherPressure = (TextView) itemView.findViewById(R.id.pressure);
            //  weatherHumidity = (TextView) itemView.findViewById(R.id.humidity);
            // weatherMain = (TextView) itemView.findViewById(R.id.main);
            // weatherWindSpeed = (TextView) itemView.findViewById(R.id.windSpeed);


            // set click event
            itemView.setOnClickListener(this);
            myLayout.setOnClickListener(this);

        }


        public void setId(String weatherId) {
            myId=weatherId;
        }
        public void setMin(String weatherMinTemperature){
            this.myMin=weatherMinTemperature;
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
            this.myPressure = weatherPressure;
        }

        public void setHumidity(String weatherHumidity) {
            this.myHumidity = weatherHumidity;
        }

        public void setMain(String weatherMain) {
            this.weatherMain.setText(weatherMain);
        }

        public void setDescription(String weatherDescription) {
            this.weatherDescription.setText(weatherDescription);
        }

        public void setWindSpeed(String weatherWindSpeed) {
            this.myWindSpeed = weatherWindSpeed;
        }

        @Override
        public void onClick(View v) {

            if (v.getId() == myLayout.getId()) {
                Intent intent = new Intent(mActiviy, ShowDetailedActivityInfo.class);
                Bundle extras = new Bundle();
                extras.putString("name", weatherName.getText().toString());

                extras.putString("maxTemperature", weatherMaxTemperature.getText().toString());
                extras.putString("dayTemperature", weatherDayTemperature.getText().toString());
                extras.putString("weatherName", weatherName.getText().toString());
                extras.putString("pressure", myPressure);
                extras.putString("humidity", myHumidity);
                extras.putString("description",weatherDescription.getText().toString());
                extras.putString("speed",myWindSpeed);
                extras.putString("minTemperature",myMin);
                //pana aici e ok
                extras.putString("id",myId);
                //intent.putExtra("main",weatherMain.getText());

                // extras.putExtra("image",weatherImage.getTextAlig
                // ImageView weatherImage;
                //extras.putString("id",weatherId.getText());

                intent.putExtras(extras);
                mActiviy.startActivity(intent);
                // mActiviy.startActivity(new Intent(mActiviy, ShowDetailedActivityInfo.class));
            } else {
                Toast.makeText(v.getContext(), "ROW PRESSED = " + String.valueOf(getAdapterPosition()), Toast.LENGTH_SHORT).show();
            }
        }

    }
}
