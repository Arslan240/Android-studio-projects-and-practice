package com.example.hidinguielements;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void doThing(View view){
        Button hideShow = findViewById(R.id.button);
        TextView thing = findViewById(R.id.textView);
        System.out.println("Button is pressed");

        if(((String)hideShow.getTag()).equals("1")){
            System.out.println("Inside if condition");
            thing.setVisibility(View.INVISIBLE);
            hideShow.setText("Show");
            hideShow.setTag("0");
        }else if(((String)hideShow.getTag()).equals("0")){ //Convert getTag to fucking string and also store strings in that shit.
            System.out.println("Inside else condition");
            thing.setVisibility(View.VISIBLE);
            hideShow.setText("Hide");
            hideShow.setTag("1");
        }
    }
}