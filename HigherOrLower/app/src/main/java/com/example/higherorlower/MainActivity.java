package com.example.higherorlower;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private static Random rand = new Random(); //it hopefully will select the numbers between 1-20
    private static int randnum = rand.nextInt(20);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void checkNumber(View view){
        EditText userGuess = findViewById(R.id.editTextNumber);
        int userNum = Integer.parseInt(String.valueOf(userGuess.getText())); //so thats why tried this way and worked.
//        int userNum = Integer.parseInt(userGuess.getText().toString()); //also a method shown in tutorial.
//        int userNum = Integer.parseInt(userGuess.toString()); //tried to do it like this but it didn't work.

        if(userNum > randnum){
            makeToast("You are thinking higher");
        }else if(userNum < randnum){
            makeToast("You are thinking lower than the number");
        }else{
            makeToast("That's right. the number is RIGHT.");
            randnum = rand.nextInt(20);
        }
    }

    public void makeToast(String string){
        Toast.makeText(MainActivity.this, string, Toast.LENGTH_SHORT).show();
    }
}