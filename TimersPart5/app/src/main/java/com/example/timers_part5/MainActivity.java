package com.example.timers_part5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //CountDownTimer
        int duration = 10_000;
        CountDownTimer timer = new CountDownTimer(duration,1000) { //(duration,interval) duration is the total duration for which the timer will run and the interval is ticktime like we see in clock every tick time is 1s or 1000ms. It is similar here we count down after every interval.
            @Override
            public void onTick(long millisUntilFinished) { //onTick is called every countDownInterval or 1000ms as provided above in this case.
                //when count down is counting down
                Log.i("Seconds left : ",String.valueOf(millisUntilFinished/1000));

            }

            @Override
            public void onFinish() {
                //when count down is finished.
                Log.i("Count down ","timer has finished");
            }
        }.start(); //we start the countDownTimer like this here by using start method.


        /*//Runnable
        final Handler handler = new Handler(); //Handler is used to handle the run methods and call them.
        Runnable run = new Runnable() { //Runnable has a method named run. Which will run at repititions according to the specifications provided by the user. It will run after every second, or every 2 seconds and like this.
            @Override
            public void run() {
                Log.i("Runnable is running."," A second must have passed.");

                handler.postDelayed(this,100); //"this" points to the run method and "1000" is delay between every function call in milliseconds.
            }
        };
        handler.post(run); //this is method call to run. Here it goes to run method, executes it, and then calls itself after the delay and it goes on and on forever.
        */
    }

}