package com.example.imageview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void changeImage(View view){
        ImageView myImageView = findViewById(R.id.imageView);
        myImageView.setImageResource(R.drawable.rrswbphev006); //here we are trying to pass the image idk why R.drawable is used may be it is to set paths. to resources/drawable/imagename
        //setBackgroundResource() --> this method will set the background of our Imageview and our main image wil be on top of this.
        //if we want to change the background image then we may use this but to change main source image we should use setImageResource() as used above
    }
}