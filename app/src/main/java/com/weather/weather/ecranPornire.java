package com.weather.weather;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ecranPornire extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecran_pornire);


    }
    public void toSearchCity(View view)
    {
        Intent intent = new Intent(ecranPornire.this, SearchCity.class);
        startActivity(intent);
    }

    public void toCredits(View view)
    {
        Intent intent = new Intent(ecranPornire.this, Credits.class);
        startActivity(intent);
    }

}
