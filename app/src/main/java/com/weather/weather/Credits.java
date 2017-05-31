package com.weather.weather;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Credits extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        //hide Blue ActionBar from Credits
        if (actionBar != null) {
            actionBar.hide();
        }
        setContentView(R.layout.activity_credits);
        changeButtonCredits();
    }
    public void changeButtonCredits() {
        Button button = (Button) findViewById(R.id.buttonCredits);
        button.getBackground().setAlpha(200);
    }

    public void toSearchCity(View view)
    {
        Intent intent = new Intent(Credits.this, SearchCity.class);
        startActivity(intent);
        finish();//destroy this activity
    }
}
