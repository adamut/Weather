package com.weather.weather;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ecranPornire extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        //hide Blue ActionBar from ecranPornire
        actionBar.hide();
        setContentView(R.layout.activity_ecran_pornire);

        changeButtonStart();
    }

    public void changeButtonStart() {
        Button button = (Button) findViewById(R.id.button);
        button.getBackground().setAlpha(200);
    }

    public void toSearchCity(View view)
    {
        Intent intent = new Intent(ecranPornire.this, SearchCity.class);
        startActivity(intent);
        finish();//destroy this activity
    }

    public void toCredits(View view)
    {
        Intent intent = new Intent(ecranPornire.this, Credits.class);
        startActivity(intent);
        finish();//distrugem activitatea
    }
}
