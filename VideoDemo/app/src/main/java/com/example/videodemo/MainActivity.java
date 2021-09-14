package com.example.videodemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        VideoView videoView = findViewById(R.id.videoView);

        //"android.resource://" this bit comes at the start of every path + getPackageName() = package name. We use this method because if we change the name of package in future we don't have to change packageName in every line + "/" this is also common + here file name with the path inside the project. e.g. R.raw.demo.
        videoView.setVideoPath("android.resource://"+ getPackageName() + "/"+R.raw.demo);

        //setting the media controller for our video.
        MediaController mediaController = new MediaController(this); //here we have to pass context and session and it is done by using keyword this. Idk what does it means.

        //connecting the controller to actual videoView to control
        mediaController.setAnchorView(videoView);

        //Here we attatch our VideoView to a controller i.e. we have defined it before. It goes like vice versa. We set videoview for media controller, and media controller for our video view
        videoView.setMediaController(mediaController);

        videoView.start();
    }
}