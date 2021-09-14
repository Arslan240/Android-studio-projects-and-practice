package com.example.appphrasesproject_part4;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

//    ArrayList<MediaPlayer> audios = new ArrayList<MediaPlayer>(
//        MediaPlayer.create(this, Uri.parse("https://www.youtube.com/watch?v=L7fLvmi1hFs"))
//    );


    MediaPlayer[] audio = {
            MediaPlayer.create(this,Uri.parse("https://www.youtube.com/watch?v=L7fLvmi1hFs")),
            MediaPlayer.create(this, Uri.parse("https://www.youtube.com/watch?v=AwFSXUlwNKU")),
            MediaPlayer.create(this, Uri.parse("https://www.youtube.com/watch?v=PpQuRmmZSOM")),
            MediaPlayer.create(this, Uri.parse("https://www.youtube.com/watch?v=rv9BojFzjPU")),
            MediaPlayer.create(this, Uri.parse("https://www.youtube.com/watch?v=_9PvsXboqiM")),
            MediaPlayer.create(this, Uri.parse("https://www.youtube.com/watch?v=D1syyLUGNYc")) //nyoh nyoh

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void playShit(View view){
        Button button = (Button)view;
        int tag = Integer.parseInt(button.getTag().toString()); //getting tag and that file will be played from the links.
    }

    private void play(int tag){
        for(MediaPlayer player: audio){ //to stop if anyother audio is playing or not.
            if(player.isPlaying()){
                player.stop();
            }
        }
        audio[tag].start();
    }
}