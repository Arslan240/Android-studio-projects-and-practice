package com.example.textfieldsandroiddevelopern;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class SecondaryActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secondary_activity); //gotta set the name of the xml file here also to set corresponding methods in visual builder.
    }

    public void showPassword(View view){
        EditText myTextUername = findViewById(R.id.editTextName);
        EditText myPassword = findViewById(R.id.editTextPassword);

        Log.i("Username",myTextUername.getText().toString());
        Log.i("Password",myPassword.getText().toString());

    }
}


