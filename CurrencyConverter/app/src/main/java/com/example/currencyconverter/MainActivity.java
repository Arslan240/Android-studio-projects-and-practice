package com.example.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.function.DoublePredicate;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void poundsToDollars(View view){
        double priceOfDollarInPounds = 1.38; //0.72 pounds = 1 dollar

        ImageView imageView = findViewById(R.id.imageView);
        EditText amount = findViewById(R.id.editTextNumber);
        double pounds = Double.parseDouble(amount.getText().toString()); //trying to convert string to int to get pounds.
        double dollars = pounds * priceOfDollarInPounds;

        Toast.makeText(MainActivity.this, "Dollars: $"+String.format( "%.2f",dollars ), Toast.LENGTH_SHORT).show();
        imageView.setImageResource(R.drawable.banknote); //dollar note
    }

    public void dollarsToPounds(View view){
        double priceOfPoundInDollars = 0.72; //1.48 dollars = 1 pound

        ImageView imageView = findViewById(R.id.imageView);
        EditText amount = findViewById(R.id.editTextNumber);
        double dollars = Double.parseDouble(amount.getText().toString()); //trying to convert string to int to get dollars.
        double pounds = dollars * priceOfPoundInDollars;

        Toast.makeText(MainActivity.this, "Pounds: "+ String.format("%.2f",pounds ), Toast.LENGTH_SHORT).show();
        imageView.setImageResource(R.drawable.bankofengland50obverse); //pound note

    }
}