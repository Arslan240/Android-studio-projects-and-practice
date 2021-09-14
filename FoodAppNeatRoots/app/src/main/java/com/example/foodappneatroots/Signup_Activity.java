package com.example.foodappneatroots;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Signup_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        getSupportActionBar().setTitle("Sign Up");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void gotoSignupPage(View view){

        Toast.makeText(this, "TextView has been clicked", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(Signup_Activity.this,Signin_Activity.class);
        startActivity(intent);

    }

    //Apparently this method is called to make the functionality of  getSupportActionBar().setDisplayHomeAsUpEnabled(true); to work
    //It takes us to the one level up from where we are at right now. It doesn't take us to the most start of the app, just one step backwards.
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}