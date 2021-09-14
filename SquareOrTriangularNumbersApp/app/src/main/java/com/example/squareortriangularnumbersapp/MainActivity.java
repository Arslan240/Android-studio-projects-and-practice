package com.example.squareortriangularnumbersapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.midi.MidiDeviceService;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Number num = new Number();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void checkNumber(View view){
        EditText number = findViewById(R.id.editTextTextPersonName);

        if(number.getText().toString().equals("")) {
            Toast.makeText(MainActivity.this, "Please enter a number", Toast.LENGTH_SHORT).show();
        }else{
            num.number = Integer.parseInt(number.getText().toString());
            Toast.makeText(MainActivity.this, num.typeOfNumber(), Toast.LENGTH_SHORT).show();
        }
    }
}