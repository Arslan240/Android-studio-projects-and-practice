package com.example.foodappneatroots;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Signin_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        getSupportActionBar().setTitle("Sign In"); //it sets the title in top action bar.
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //It shows the back button on the top bar of the app


    }

    public void gotoSigninPage(View view){

        Toast.makeText(this, "TextView has been clicked", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(Signin_Activity.this,Signup_Activity.class);
        startActivity(intent);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}