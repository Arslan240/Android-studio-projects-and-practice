package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); //savedInstanceState = this will restore the previous state of the app if any present.
        setContentView(R.layout.activity_main); //then it will open activity_main.xml from res source.
    }

    public void clickFunction(View view){ //here we are passing the button. We can use Button button but i guess here we are using
                                          // generalization technique from the inheritance topic. Basically we prefer to use parent class variables
                                          // instead of child classes where possible to make the methods and code more maintainable and reusable.
        System.out.println("LALALALALa");
        Log.i("Info","Button Pressed!");
    }

}