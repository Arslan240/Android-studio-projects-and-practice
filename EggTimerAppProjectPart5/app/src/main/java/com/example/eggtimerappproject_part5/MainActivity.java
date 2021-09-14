package com.example.eggtimerappproject_part5;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    CountDownTimer timer;
    SeekBar timerSeekBar;
    TextView timerTextView;

    public void updateTimer(int secondsLeft){

        int minutes = secondsLeft / 60; //progress is coming from seekbar of 600 values or 10 minutes
        int seconds = secondsLeft - (minutes * 60); // seconds are calculated

        String secondsString = Integer.toString(seconds);
        if(seconds == 0){
            secondsString = "00";
        }if(seconds <= 9){
            secondsString = "0" + secondsString;
        }
        timerTextView.setText(Integer.toString(minutes) + ":" + secondsString);



    }

    public void controlTimer(View view){
        CountDownTimer timer = new CountDownTimer(timerSeekBar.getProgress() * 1000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                updateTimer((int)millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                timerTextView.setText("0:00");
                Log.i("Finished"," timer done");
            }
        }.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView timerTextView = findViewById(R.id.textView);

        timerSeekBar = findViewById(R.id.seekBar);
        timerSeekBar.setMax(10 * 60); //10 * 60 sec = 10 mins in ms
        timerSeekBar.setProgress(30); //set at 30 sec initially

        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                updateTimer(progress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                System.out.println("timer is ended");
            }
        });




        //--------------------------------------------- MY CODE DIDN'T WORK TO THE FULL CAPACITY --------------------------------------------------
        /*
        TextView timerShow = findViewById(R.id.textView); //it will show the countdown
        Button startStop = findViewById(R.id.button); //tag tells us the current position. If it is stopped then it needs to be started and it shows start as button text. when we set the button text we also change the tag value to be started.
        startStop.setTag(0);


        SeekBar seekBar = findViewById(R.id.seekBar);
        seekBar.setMax(10);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                if (startStop.getTag().equals(0)) { //button is stopped and needs to be started so
                    System.out.println("tag : "+startStop.getTag());
                    startStop.setTag(1); //it shows that the button is started and needs to be stopped if clicked.

                    timerShow.setText(String.valueOf(progress));
                    timer = new CountDownTimer(progress * 60_000, 1000) {
                        float time = progress;
                        float setTextView = progress;

                        @RequiresApi(api = Build.VERSION_CODES.N)
                        @Override
                        public void onTick(long millisUntilFinished) {

                            setTextView -= 0.01f; //subtracting seconds from minutes
                            timerShow.setText(String.format("%.2f", setTextView));

                        }

                        @Override
                        public void onFinish() {

                        }
                    }.start();
                }
                else if(startStop.getTag().equals(1)){
                    startStop.setTag(0);
                    startStop.setText("Stop");
                    timer.cancel();
                    seekBar.setProgress(0);
                    timerShow.setText(Float.toString(0.00f));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        */











    }


}