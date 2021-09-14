package com.example.timers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;

import java.sql.Time;

public class MainActivity extends AppCompatActivity {
    int i = 0;
    final int MS = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Timer using CountDownTimer
        new CountDownTimer(10 * MS,1 * MS){ //(total time , time of every tick) . All the time is in milliseconds

            @Override
            public void onTick(long millisUntilFinished) {

                //it is executed on every tick down
                System.out.println("Seconds left : " + millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                //it is executed when the count down timer time is finished.
                System.out.println("The CountDownTimer is finished after 10 secs.");

            }
        }.start();


        //Timer using Handler and Runnable.
       /* //We create a handler and then we create Runnable method and run method is called after a certain time.
        Handler handler = new Handler();
        Runnable run = new Runnable() {
            @Override
            public void run() {
                doStuff();
                handler.postDelayed(this,250); //500 milliseconds = half a second
            }
        };
        handler.post(run); //we attatch handler to the runnable so that it will be run.*/
    }

    private void doStuff() {
//        Time time = ;
//        Log.i()
        System.out.println(i++);
    }
}