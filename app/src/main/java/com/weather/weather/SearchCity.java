package com.weather.weather;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import org.apache.commons.lang3.text.WordUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SearchCity extends AppCompatActivity {

    public static String EXTRA_MESSAGE = "City inserted";
    public String citySearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        //hide Blue ActionBar from SearchCity
        if (actionBar != null) {
            actionBar.hide();
        }
        setContentView(R.layout.search_city);

        changeEditTextCity();
        changeButtonCity();
        changeImageCity();
    }
    public void changeImageCity(){
        ImageView image= (ImageView)findViewById(R.id.imageCity);
        image.setImageResource(R.drawable.sunny);
    }
    public void changeEditTextCity() {
        EditText editText = (EditText) findViewById(R.id.editText);
        editText.setBackgroundColor(Color.WHITE);
        editText.getBackground().setAlpha(170);
    }
    public void changeButtonCity() {
        Button button = (Button) findViewById(R.id.button);
        button.getBackground().setAlpha(200);
    }

    public void sendMessage(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        //take input from EditText and parse it to next Activity
        EditText editText = (EditText) findViewById(R.id.editText);
        citySearch = editText.getText().toString();
        editText.setText("");

        if(verifyCityName())
        {   try {
            intent.putExtra(EXTRA_MESSAGE, citySearch);
            startActivity(intent);
            }catch(Exception Ex){finish();
            startActivity(getIntent());}
        }
        else
        {

            Toast.makeText(this, "City not found! ", Toast.LENGTH_LONG).show();
        }
      //  this.finish();

    }
    public boolean verifyCityName()
    {
        if (citySearch == null || citySearch.trim().isEmpty()) {
            return false;
        }

        citySearch=citySearch.trim();
        citySearch = citySearch.substring(0, 1).toUpperCase() + citySearch.substring(1).toLowerCase();
        //Toast.makeText(this,citySearch, Toast.LENGTH_LONG).show();
        Pattern stringPattern = Pattern.compile("[^A-Za-z -]");
        Matcher matcher = stringPattern.matcher(citySearch);
        boolean result = matcher.find();

       citySearch= WordUtils.capitalizeFully(citySearch);
        citySearch=WordUtils.capitalize(citySearch, new char[] { '-' });
        citySearch=citySearch.replaceAll("^ +| +$|( )+", "$1");
        //citySearch=citySearch.replaceAll("\\W","");
        citySearch=citySearch.replaceAll("^-+$", "$1");

        Toast.makeText(this, citySearch , Toast.LENGTH_LONG).show();
        //if result = true that means the string contains special character
        if (result == true) //return true if there's no special character in the string, false otherwise
            return false;
        return true;

    }
    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}
