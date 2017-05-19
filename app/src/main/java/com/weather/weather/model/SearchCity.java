package com.weather.weather;

import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;


public class SearchCity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "merge acest rahat?";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        //hide Blue ActionBar from SearchCity
        actionBar.hide();
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
        button.getBackground().setAlpha(180);
    }

    public void sendMessage(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
      //  this.finish();

    }

}
