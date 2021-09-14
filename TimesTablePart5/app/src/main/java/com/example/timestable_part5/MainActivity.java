package com.example.timestable_part5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity { //TODO : THIS HAS GOT A PROBLEM. THE LISTVIEW IS A BIT MESSED UP. INSTEAD OF JUST DISPLAYING THE TABLE ONCE IT SHOWS US LIKE FOR IDK LIKE 10 TIMES. AND ALL THE NUMBERS COMING AFTER ARE SHOWED ONCE BUT ALL THE PREVIOUS TABLES ARE PRESENT. IDK HOW TO GET RID OF THAT.

    private static final int UPTO = 10;
    private static final int TABLE_LIMIT = 20;
    private int table[] = {0,0,0,0,0,0,0,0,0,0};
    ArrayList<Integer> arrayList = new ArrayList<Integer>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView table = findViewById(R.id.listview);//getting the list view

        TextView number = findViewById(R.id.textView);
        SeekBar seekBar = findViewById(R.id.seekBar);
        seekBar.setMax(TABLE_LIMIT);
        seekBar.setProgress(1);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int min = 1;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(progress < min){
                    seekBar.setProgress(1); //using this method we set the seekbar back to 1 when the user drags it to 0. //from Instructors video
                }else{
                    System.out.println("Progress" + progress);
                    number.setText(String.valueOf(progress));
                    calculateTable(Integer.parseInt(String.valueOf(progress))); //calculate tables and store in int array
                    setArrayList(); //assigning tables values from array to the arraylist
                    table.setAdapter(null);
                    ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1,arrayList);
                    table.setAdapter(arrayAdapter);
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

    public void calculateTable(int number){
        for(int i = 0,j = 1; i < UPTO;i++,j++){
            table[i] = number * j;
        }
    }

    public void setArrayList(){
        for(int i : table){
            arrayList.add(i); //here we are adding the numbers to the arraylist so that we can use them to add it later to the arrayadapter and then list view
        }

    }

}