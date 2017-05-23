package com.weather.weather;


import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.weather.weather.model.ImageCode;

public class ShowDetailedActivityInfo extends AppCompatActivity {
    Bundle extrasCity;
    ImageView weatherImage;
    ImageView detailedWeatherImage;
    //TextView weatherId;
    TextView weatherMaxTemperature;
    TextView weatherDayTemperature;
    TextView weatherName;
    TextView weatherPressure;
    TextView weatherHumidity;
    //TextView weatherMain;
    TextView weatherDescription;
    TextView weatherWindSpeed;
    TextView weatherMinTemperature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_detailed_activity);
        // Get the Intent that started this activity and extract the string
        changeLayoutDetailed();
        extrasCity = getIntent().getExtras();
        findAttributesId();
        weatherMinTemperature.setText("Min: "+ extrasCity.getString("minTemperature"));
        weatherMaxTemperature.setText("Max: "+ extrasCity.getString("maxTemperature"));
        weatherDayTemperature.setText("Day: "+extrasCity.getString("dayTemperature"));
        weatherName.setText(extrasCity.getString("name"));
        weatherPressure.setText("Pressure: "+extrasCity.getString("pressure"));
        weatherHumidity.setText("Humidity: "+extrasCity.getString("humidity"));
        weatherDescription.setText(extrasCity.getString("description"));
        weatherWindSpeed.setText(extrasCity.getString("speed"));
        ImageCode image = new ImageCode();
        weatherImage.setImageResource(image.searchBackgroundValue(extrasCity.getInt("id")));
      //  int  idIcon= extrasCity.getInt("id");
      //  idIcon = image.searchIconValue(idIcon);
       detailedWeatherImage.setImageResource(image.searchIconValue(extrasCity.getInt("id")));



    }

    public void changeLayoutDetailed() {
        ConstraintLayout editLayoutToday = (ConstraintLayout) findViewById(R.id.layoutDetailed);
        editLayoutToday.setBackgroundColor(Color.BLACK);
        editLayoutToday.getBackground().setAlpha(180);
    }

    public void findAttributesId() {
        weatherMinTemperature = (TextView) findViewById(R.id.detailedMin);
        weatherMaxTemperature = (TextView) findViewById(R.id.detailedMax);
        weatherDayTemperature = (TextView) findViewById(R.id.detailedDay);
        weatherName = (TextView) findViewById(R.id.detailedCityName);
        weatherPressure = (TextView) findViewById(R.id.detailedPressure);
        weatherHumidity = (TextView) findViewById(R.id.detailedHumidity);
        weatherDescription = (TextView) findViewById(R.id.detailedDescription);
        weatherWindSpeed = (TextView) findViewById(R.id.detailedSpeed);
        weatherImage = (ImageView) findViewById(R.id.backgroundDetail);
        detailedWeatherImage = (ImageView) findViewById(R.id.detailedImage);
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

    public boolean onOptionsItemSelected(MenuItem item) {
        int menuItemThatWasSelected = item.getItemId();
        //<!--TODO am de revizuit cum e scris un URI si cum sa fac cu geolocatia + de verificat daca linia 62-63 e ok -->

        switch (menuItemThatWasSelected) {
            case android.R.id.home: {
                onBackPressed();
                break;
            }

            case R.id.action_location: {
                Context context = ShowDetailedActivityInfo.this;
                String message = "Search clicked";
                Toast.makeText(context, message, Toast.LENGTH_LONG).show();
            }

        }
        return true;
    }
*/
}
