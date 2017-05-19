package com.weather.weather;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

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
    final static String APPID="appid";
    private String cityName;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Intent de la SearchCity
        Intent intent = getIntent();
        cityName = intent.getStringExtra(com.weather.weather.SearchCity.EXTRA_MESSAGE);
        //selectare text view si plasare string
        TextView textView = (TextView) findViewById(R.id.dateDay);
        textView.setText(cityName);

        changeBackgroundColor(R.color.colorAccent);
        init();
        new FetchDataTask().execute();
    }
    private void changeBackgroundColor(int color){
        getWindow().getDecorView().setBackgroundColor(ContextCompat.getColor(MainActivity.this, color));
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
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        int menuItemThatWasSelected = item.getItemId();
        if(menuItemThatWasSelected == R.id.action_search){
            Context context = MainActivity.this;
            String message = "Search clicked";
            Toast.makeText(context,message, Toast.LENGTH_LONG).show();
        }
        return true;
    }

    public class FetchDataTask extends AsyncTask<Void, Void, Void> {

        private String mWeatherString;

        @Override
        protected Void doInBackground(Void... params) {
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
                //  Log.v("Response", jsonObject.toString());
                //revenim aici


                JSONObject jsonObj = new JSONObject(mWeatherString);

                String cod = jsonObj.getString("cod");

                if(!cod.equals("404")) {
                    JSONObject city = jsonObj.getJSONObject("city");
                    String name = city.getString("name");

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
                        String speed = String.valueOf(objList.getDouble("speed"));

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
                    }
                }
                else
                {

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
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            mAdapter.notifyDataSetChanged();
        }
    }
}
