package com.example.egg_timer_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.material.transition.platform.MaterialSharedAxis;

enum State{
    STOPPED, GOING
}

public class MainActivity extends AppCompatActivity {
    TextView textView;
    Button button;

    public void controlTimer(View view){


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);

        SeekBar seekBar = findViewById(R.id.seekBar);
        seekBar.setMax(600); //one min = 60 secs so 10 mins = 600 secs
        seekBar.setProgress(30); //30 secs


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                int min = progress / 60; //this will return minutes
                int secs = progress - (min * 60); //progress => total seconds, min * 60 => minutes of the total seconds. We subtract minutes from seconds. This will return seconds if they are not enough to make a new minute. So we have minutes and also the remaining seconds.

                String seconds = Integer.toString(secs);
                if (secs == 0){
                    seconds = "00";
                }

                textView.setText(min+":"+seconds);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }














// This is what I tried to do

    /*final int MIN1 = 60000; //60_000 ms = 60 sec = 1 min
    final int SEC1 = 1000; //1 sec in ms
    TextView textView;
    Button stopButton;

    final int  MAX = 120;
    int x = MAX;
    SeekBar seekBar;
    State state;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        stopButton = findViewById(R.id.button);
        seekBar = findViewById(R.id.seekBar);
        seekBar.setMax(MAX);


        CountDownTimer timer = new CountDownTimer(MAX * SEC1, 1000){ //total

            @Override
            public void onTick(long millisUntilFinished) {

                state = State.GOING;
                setSeekBarProgress();

                int min = (int)millisUntilFinished / MIN1; //getting minutes value by dividing total milliseconds by 1 sec in milliseconds.
                int secs = (int) millisUntilFinished % MIN1; //this will give us remainig seconds after getting the minutes values. If the value divides completely then this will contain 0 so it means that we have complete round values with no decimal values.

                if(min == 0 && secs == 0){
                    System.out.println("min and secs are 0");
                    textView.setText("0 : 00");
                }else if(min != 0 && secs == 0){
                    System.out.println("2nd condition, min = "+min+"\n");
                    textView.setText(String.format("%d : 00", min));
                }else{
                    System.out.printf("3rd condition, min = %d\nsecs = %2d\n",min,secs);
                    textView.setText(String.format("%d : %d",min, secs));
                }

            }

            @Override
            public void onFinish() {

            }
        }.start();

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(state == State.GOING){
                    timer.cancel();
                    state = State.STOPPED;
                }else if(state == State.STOPPED){
                    timer.start();
                    seekBar.setProgress(MAX);
                    state = State.GOING;
                }
            }
        });

    }

    private void setSeekBarProgress() {
//        x -= 1;
        seekBar.setProgress(--x); //it subtracts one from the MAX value to reduce one from the value.
    }*/


}