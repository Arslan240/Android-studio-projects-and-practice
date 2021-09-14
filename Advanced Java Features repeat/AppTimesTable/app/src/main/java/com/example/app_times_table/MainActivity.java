package com.example.app_times_table;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    SeekBar seekBar;
    final int MULTIPLIER = 10;
    ListView myListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        myListView = findViewById(R.id.timesTable);



        seekBar = findViewById(R.id.seekBar);
        seekBar.setMax(20); //setting limit to the seekBar to the max 20.
        seekBar.setProgress(1);//starting the seekbar from 1

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                if(progress == 0){
                    seekBar.setProgress(1); //we don't want to let the seekbar to the 0
                }
                else{

                    updateListView(progress);

                    //TODO
                    // idk why But I could not write following code(even when I had ArrayList code here but still I could not do it) here inside else loop.
                    //ArrayAdapter<String> tableValuesArrayAdapter =  new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,tableValues);
                    //myListView.setAdapter(tableValuesArrayAdapter);

                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    private void updateListView(int progress){
        ArrayList<String> tableValues = new ArrayList<>();

        for(int i = 1; i <= MULTIPLIER; i++){
            tableValues.add(String.valueOf(progress * i)); //calculating the tables.
        }
        ArrayAdapter<String> tableValuesArrayAdapter =  new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,tableValues);
        myListView.setAdapter(tableValuesArrayAdapter);
    }


}