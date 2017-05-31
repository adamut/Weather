package com.weather.weather;


import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.weather.weather.model.ImageCode;

public class ShowDetailedActivityInfo extends AppCompatActivity {
    private Bundle extrasCity;
    private ImageView weatherImage;
    private ImageView detailedWeatherImage;
    //TextView weatherId;
    private TextView weatherMaxTemperature;
    private TextView weatherDayTemperature;
    private TextView weatherName;
    private TextView weatherPressure;
    private TextView weatherHumidity;
    //TextView weatherMain;
    private TextView weatherDescription;
    private TextView weatherWindSpeed;
    private TextView weatherMinTemperature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_detailed_activity);
        // Get the Intent that started this activity and extract the string
        changeLayoutDetailed();
        setExtrasCity(getIntent().getExtras());
        findAttributesId();
        getWeatherMinTemperature().setText("Min: "+ getExtrasCity().getString("minTemperature"));
        getWeatherMaxTemperature().setText("Max: "+ getExtrasCity().getString("maxTemperature"));
        getWeatherDayTemperature().setText("Day: "+ getExtrasCity().getString("dayTemperature"));
        getWeatherName().setText(getExtrasCity().getString("name"));
        getWeatherPressure().setText("Pressure: "+ getExtrasCity().getString("pressure"));
        getWeatherHumidity().setText("Humidity: "+ getExtrasCity().getString("humidity"));
        getWeatherDescription().setText(getExtrasCity().getString("description"));
        getWeatherWindSpeed().setText(getExtrasCity().getString("speed"));
        ImageCode image = new ImageCode();

        String test= getExtrasCity().getString("id");
        int id = Integer.valueOf(test);
        int idIcon= image.searchBackgroundValue(id);
        //int idIcon=idBackground;
        //Toast.makeText(this,idBackground,Toast.LENGTH_LONG).show();
        getWeatherImage().setImageResource(image.searchBackgroundValue(id));
       getDetailedWeatherImage().setImageResource(image.searchIconValue(id));



    }

    public void changeLayoutDetailed() {
        ConstraintLayout editLayoutToday = (ConstraintLayout) findViewById(R.id.layoutDetailed);
        editLayoutToday.setBackgroundColor(Color.BLACK);
        editLayoutToday.getBackground().setAlpha(180);
    }

    public void findAttributesId() {
        setWeatherMinTemperature((TextView) findViewById(R.id.detailedMin));
        setWeatherMaxTemperature((TextView) findViewById(R.id.detailedMax));
        setWeatherDayTemperature((TextView) findViewById(R.id.detailedDay));
        setWeatherName((TextView) findViewById(R.id.detailedCityName));
        setWeatherPressure((TextView) findViewById(R.id.detailedPressure));
        setWeatherHumidity((TextView) findViewById(R.id.detailedHumidity));
        setWeatherDescription((TextView) findViewById(R.id.detailedDescription));
        setWeatherWindSpeed((TextView) findViewById(R.id.detailedSpeed));
        setWeatherImage((ImageView) findViewById(R.id.backgroundDetail));
        setDetailedWeatherImage((ImageView) findViewById(R.id.detailedImage));
    }

    // Intent intent = getIntent();
    // String firstKeyName = intent.getStringExtra("name");
    // Toast.makeText(this, firstKeyName, Toast.LENGTH_SHORT).show();
    //selectare text view si plasare string
    //Toast.makeText(this, cityName, Toast.LENGTH_LONG).show();

   /* public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
*/
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuItemThatWasSelected = item.getItemId();
        //<!--TODO am de revizuit cum e scris un URI si cum sa fac cu geolocatia + de verificat daca linia 62-63 e ok -->

        switch (menuItemThatWasSelected) {
            case android.R.id.home: {
                onBackPressed();
                break;
            }
        }
        return true;
    }

    public Bundle getExtrasCity() {
        return extrasCity;
    }

    public void setExtrasCity(Bundle extrasCity) {
        this.extrasCity = extrasCity;
    }

    public ImageView getWeatherImage() {
        return weatherImage;
    }

    public void setWeatherImage(ImageView weatherImage) {
        this.weatherImage = weatherImage;
    }

    public ImageView getDetailedWeatherImage() {
        return detailedWeatherImage;
    }

    public void setDetailedWeatherImage(ImageView detailedWeatherImage) {
        this.detailedWeatherImage = detailedWeatherImage;
    }

    public TextView getWeatherMaxTemperature() {
        return weatherMaxTemperature;
    }

    public void setWeatherMaxTemperature(TextView weatherMaxTemperature) {
        this.weatherMaxTemperature = weatherMaxTemperature;
    }

    public TextView getWeatherDayTemperature() {
        return weatherDayTemperature;
    }

    public void setWeatherDayTemperature(TextView weatherDayTemperature) {
        this.weatherDayTemperature = weatherDayTemperature;
    }

    public TextView getWeatherName() {
        return weatherName;
    }

    public void setWeatherName(TextView weatherName) {
        this.weatherName = weatherName;
    }

    public TextView getWeatherPressure() {
        return weatherPressure;
    }

    public void setWeatherPressure(TextView weatherPressure) {
        this.weatherPressure = weatherPressure;
    }

    public TextView getWeatherHumidity() {
        return weatherHumidity;
    }

    public void setWeatherHumidity(TextView weatherHumidity) {
        this.weatherHumidity = weatherHumidity;
    }

    public TextView getWeatherDescription() {
        return weatherDescription;
    }

    public void setWeatherDescription(TextView weatherDescription) {
        this.weatherDescription = weatherDescription;
    }

    public TextView getWeatherWindSpeed() {
        return weatherWindSpeed;
    }

    public void setWeatherWindSpeed(TextView weatherWindSpeed) {
        this.weatherWindSpeed = weatherWindSpeed;
    }

    public TextView getWeatherMinTemperature() {
        return weatherMinTemperature;
    }

    public void setWeatherMinTemperature(TextView weatherMinTemperature) {
        this.weatherMinTemperature = weatherMinTemperature;
    }
}
