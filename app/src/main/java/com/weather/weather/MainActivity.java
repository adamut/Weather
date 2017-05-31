package com.weather.weather;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.instabug.library.Instabug;
import com.instabug.library.invocation.InstabugInvocationEvent;
import com.weather.weather.adapters.WeatherAdapter;
import com.weather.weather.model.ImageCode;
import com.weather.weather.model.WeatherJSON;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private RecyclerView mWeatherRecyclerView;
    private WeatherAdapter mAdapter;
    private ArrayList<WeatherJSON> mWeatherCollection;
    final static String QUERY_PARAM = "q";
    final static String APPID = "appid";
    private String cityName;
    private WeatherJSON todayValues;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Instabug.Builder(getApplication(), "6d9cf5bf8b38973f91d4d4476c9339b1")
                .setInvocationEvent(InstabugInvocationEvent.SHAKE)
                .build();
        //Intent de la SearchCity
        Intent intent = getIntent();
        cityName = intent.getStringExtra(com.weather.weather.SearchCity.EXTRA_MESSAGE);
        //selectare text view si plasare string
        //Toast.makeText(this, cityName, Toast.LENGTH_LONG).show();

        // changeBackgroundColor(R.color.colorAccent);
        init();
        new FetchDataTask().execute();
        //setTodayWeather();

    }

    //private void changeBackgroundColor(int color) {
    //  getWindow().getDecorView().setBackgroundColor(ContextCompat.getColor(MainActivity.this, color));
    // }

    public void changeLayoutToday() {
        ConstraintLayout editLayoutToday = (ConstraintLayout) findViewById(R.id.layoutToday);
        editLayoutToday.setBackgroundColor(Color.BLACK);
        editLayoutToday.getBackground().setAlpha(180);
    }

    public void changeRecyclerBackground() {
        RecyclerView layoutCardView = (RecyclerView) findViewById(R.id.weather_recycler);
        layoutCardView.setBackgroundColor(Color.BLACK);
        layoutCardView.getBackground().setAlpha(180);

    }

    private void init() {
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mWeatherRecyclerView = (RecyclerView) findViewById(R.id.weather_recycler);
        mWeatherRecyclerView.setLayoutManager(layoutManager);
        mWeatherRecyclerView.setHasFixedSize(true);
        mWeatherCollection = new ArrayList<>();
        mAdapter = new WeatherAdapter(mWeatherCollection, this);
        mWeatherRecyclerView.setAdapter(mAdapter);
        // changeRecyclerBackground();
        todayValues = new WeatherJSON();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
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

            case R.id.action_search: {
                onBackPressed();
                break;
            }

            case R.id.action_share: {
                sharingData();
                break;
            }
            case R.id.action_location: {
                openLocationInMap();
                break;
            }
            case R.id.action_settings:{
                createSettingsIntent();
            }
        }
        return true;
    }

    public class FetchDataTask extends AsyncTask<Void, Void, WeatherJSON> {

        private String mWeatherString;

        @Override
        protected WeatherJSON doInBackground(Void... params) {
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            Uri builtUri = Uri.parse(getString(R.string.weather_api1)).buildUpon()
                    .appendQueryParameter(QUERY_PARAM, cityName)
                    .appendQueryParameter(APPID, getString(R.string.appid))
                    .build();
            // Uri builtUri = Uri.parse(getString(R.string.weather_api));
            URL url;
            try {

                url = new URL(builtUri.toString());
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                // urlConnection.setRequestProperty("appid", "93635fe72bd1c0937079a9a1653fa2db");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    //Nothing to do
                    return null;
                }

                reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0)
                    return null;


                mWeatherString = buffer.toString();
                // JSONObject jsonObject = new JSONObject(mWeatherString);

                //revenim aici


                JSONObject jsonObj = new JSONObject(mWeatherString);

                String cod = jsonObj.getString("cod");

                if (!cod.equals("404")) {
                    JSONObject city = jsonObj.getJSONObject("city");
                    String name = city.getString("name");
                    String[] parts = name.split("-");
                    String nameToCompare = parts[0];
                    if (cityName.equals(name) || nameToCompare.equals(cityName)) {
                        JSONArray list = jsonObj.getJSONArray("list");

                        for (int i = 0; i < list.length(); i++) {
                            JSONObject objList = list.getJSONObject(i);
                            JSONObject temp = objList.getJSONObject("temp");

                            int day = (int) (temp.getDouble("day") - 273.16);
                            String dayTemperature = String.valueOf(day);

                            int min = (int) (temp.getDouble("min") - 273.16);
                            String minTemperature = String.valueOf(min);

                            int max = (int) (temp.getDouble("max") - 273.16);
                            String maxTemperature = String.valueOf(max);


                            String pressure = String.valueOf(objList.getDouble("pressure"));
                            String humidity = String.valueOf(objList.getDouble("humidity"));

                            JSONArray weather = objList.getJSONArray("weather");
                            JSONObject weatherInfo = weather.getJSONObject(0);
                            String mainWeather = weatherInfo.getString("main");
                            String description = weatherInfo.getString("description");
                            String id = weatherInfo.getString("id");
                            int idIcon = weatherInfo.getInt("id");
                            int backgroundIcon = idIcon;
                            String speed = String.valueOf(objList.getDouble("speed"));

                            if (i != 0) {
                                WeatherJSON weatherObj = new WeatherJSON();
                                weatherObj.setName(name);
                                weatherObj.setId(id);
                                weatherObj.setDay(dayTemperature);
                                weatherObj.setMin(minTemperature);
                                weatherObj.setMax(maxTemperature);
                                weatherObj.setPressure(pressure);
                                weatherObj.setHumidity(humidity);
                                weatherObj.setMainWeather(mainWeather);
                                weatherObj.setDescription(description);

                                //cautam poza in clasa ImageCode si returnam valoarea imaginii
                                ImageCode image = new ImageCode();
                                idIcon = image.searchIconValue(idIcon);
                                weatherObj.setIdIcon(idIcon);

                                weatherObj.setSpeed(speed);
                                mWeatherCollection.add(weatherObj);
                            } else {

                                // setTodayWeather(dayTemperature, maxTemperature, description, idIcon);
                                todayValues.setName(name);
                                todayValues.setMin(minTemperature);
                                todayValues.setDay(dayTemperature);
                                todayValues.setMax(maxTemperature);
                                todayValues.setDescription(description);
                                ImageCode image = new ImageCode();
                                idIcon = image.searchIconValue(idIcon);
                                backgroundIcon = image.searchBackgroundValue(backgroundIcon);
                                todayValues.setIdIcon(idIcon);
                                todayValues.setBackgroundIcon(backgroundIcon);
                                todayValues.setPressure(pressure);
                                todayValues.setHumidity(humidity);
                                todayValues.setSpeed(speed);

                                //pus acum
                                todayValues.setId(id);


                            }

                        }
                    } else {
                        //in case City name != inputCity, closing URL and reader connection, return null
                        if (urlConnection != null) {
                            urlConnection.disconnect();
                            if (reader != null) {
                                try {
                                    reader.close();
                                } catch (IOException e) {
                                    Log.e("MainActivity", "Error closing stream", e);
                                }
                            }
                            return null;
                        }
                    }
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e("MainActivity", "Error closing stream", e);
                    }
                }
            }

            //Closing URL and reader connection in case we want to see other cities
            if (urlConnection != null)
                urlConnection.disconnect();
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    Log.e("MainActivity", "Error closing stream", e);
                }
            }
            return todayValues;
        }

        @Override
        protected void onPostExecute(WeatherJSON obj) {

            mAdapter.notifyDataSetChanged();
            if (obj != null && !obj.getName().equals("")) {
                setTodayWeather();
            } else {
                Intent intent = new Intent(MainActivity.this, SearchCity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);

            }


        }
    }

    public void setTodayWeather() {
        changeLayoutToday();
        TextView todayTemp = (TextView) findViewById(R.id.dayTemp);
        String displayDay = todayValues.getDay() + " °";
        todayTemp.setText(displayDay);

        TextView todayMaxTemp = (TextView) findViewById(R.id.maxTempDay);
        String displayMax = "High " + todayValues.getMax() + " ° ";
        todayMaxTemp.setText(displayMax);

        TextView todayMinTemp = (TextView) findViewById(R.id.minTempDay);
        String displayMin = "Low " + todayValues.getMin() + " ° ";
        todayMinTemp.setText(displayMin);

        TextView todayDescription = (TextView) findViewById(R.id.mainDay);
        todayDescription.setText(todayValues.getDescription());

        ImageView dayImage = (ImageView) findViewById(R.id.imageDay);
        dayImage.setImageResource(todayValues.getIdIcon());

        TextView todayCity = (TextView) findViewById(R.id.todayCity);
        todayCity.setText(todayValues.getName());

        ImageView todayImage = (ImageView) findViewById(R.id.imageBackgroundMain);
        //Picasso.with(MainActivity.this).load(todayValues.getBackgroundIcon()).into(todayImage);
        todayImage.setImageResource(todayValues.getBackgroundIcon());
        //todayImage.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.thuderstorm));


    }


    private void openLocationInMap() {
        String addressString = todayValues.getName() ;
        Uri geoLocation = Uri.parse("geo:0,0?q=" + addressString);

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(geoLocation);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } //else {
        // Log.d(TAG, "Couldn't call " + geoLocation.toString()
        //         + ", no receiving apps installed!");
    }

    private void sharingData(){
        Intent shareIntent = ShareCompat.IntentBuilder.from(this)
                .setType("text/plain")
                .setText("Ana are mere")
                .getIntent();
        if (shareIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(shareIntent);
        }
    }

    private void createSettingsIntent(){
        Intent intent = new Intent(this, UserSettings.class);
        startActivity(intent);

    }
}

