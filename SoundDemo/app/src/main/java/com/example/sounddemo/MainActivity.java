package com.example.sounddemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    int milliseconds=0;
    AudioManager audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = MediaPlayer.create(this,R.raw.audio); //we can create it by passing the file to the kind of constructor
        mediaPlayer.start();

        //we want to control the volume levels of our audio so we do it by using audio manager. This manager can be used to achieve a lot of services related to the audio and stuff.
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE); //audio service is now accessible.
        //now using the audio manger which is referring to or accessing the audio services. We get maximum volume of the device and  current volume to control it.
        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC); //this returns genera; volume of the media like videos and audios. not like alarms and notifications.
        int curVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC); //returns current value of the volume

        SeekBar volumeControl = findViewById(R.id.seekBar);
        volumeControl.setMax(maxVolume);
        volumeControl.setProgress(curVolume); //this will set our seek on the bar according to %age of the current volume to max volume.


        volumeControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { //We use seekbar method setOnseekBarChangeListener() in it we pass some kind of thing idk if it's interface or what

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                Log.i("Seekbar Value", Integer.toString(progress)); //This method will tell us the values of the seekbar as it is changed. We are just logging those values on the console.
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,progress,0); //here we sets the actual volume according to the values changed by the user. we pass in type of Stream, volume, and any flags if other wise 0
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

        });

        SeekBar pointOfPlay = findViewById(R.id.pointOfPlay);
        pointOfPlay.setMax(mediaPlayer.getDuration()); //finding the length of audio file

        //this piece is responsible for successive calls to the methods inside it and we can define our own logic for doing whatever we want to do.
        //delay is delay between every call. period tells us calls per every second or period defined in here. period = 1000 ms means every second.
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                pointOfPlay.setProgress(mediaPlayer.getCurrentPosition()); //TODO it is not right because we set the progress here and then down line 78 we set the change to the audio which is setting a loop and it calls itself again and again and it doesn't move forward
                //set the seeker every period/1000 seconds
            }
        },0,1000);

        pointOfPlay.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.i("Point of Play Value", Integer.toString(progress));
                mediaPlayer.seekTo(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }

    public void pause(View view){
        mediaPlayer.pause();
        milliseconds = mediaPlayer.getCurrentPosition();
    }

    public void play(View view){
        mediaPlayer.start();
        //mediaPlayer.seekTo(milliseconds);
    }

    public void startOver(View view){ //this method is not working.
        mediaPlayer.stop();
        start();
    }
    public void start(){
        mediaPlayer.start();
    }
}