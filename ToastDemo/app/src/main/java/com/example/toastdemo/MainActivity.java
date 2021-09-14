package com.example.toastdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showIt(View view){
        EditText textfield = findViewById(R.id.editTextTextPersonName);
        Toast.makeText(MainActivity.this, "Hi there, "+textfield.getText()+"!", Toast.LENGTH_SHORT).show();
    }
}