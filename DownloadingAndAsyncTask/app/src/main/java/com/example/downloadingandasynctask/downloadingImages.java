package com.example.downloadingandasynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class downloadingImages extends AppCompatActivity {

    public void doStuff(View view){

        Log.i("Hahaha","Button is pressed.");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_downloading_images);

    }
}