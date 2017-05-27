package com.weather.weather;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

/**
 * Created by Cosmin-Marian on 5/24/2017.
 */

public class UserSettings extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

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
}
