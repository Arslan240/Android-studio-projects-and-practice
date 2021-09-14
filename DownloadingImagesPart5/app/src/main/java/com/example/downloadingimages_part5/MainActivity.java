package com.example.downloadingimages_part5;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.UrlQuerySanitizer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    ImageView downloadedImage;

    public void downloadImage(View view){

        //data:image/jpeg;base64,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

        ImageDownloader task = new ImageDownloader();

        Bitmap myBitmap;

        try {

            Log.i("Inside cry ","Statement");
            myBitmap = task.execute("https://i.guim.co.uk/img/media/7111f90e2b39760de4819be4cb2a7d916e4e5b9a/1730_435_3886_2331/master/3886.jpg?width=1200&height=1200&quality=85&auto=format&fit=crop&s=472e53a31f55ba23cc1b45f8d07f0b0d").get();

            downloadedImage.setImageBitmap(myBitmap); //setting the image to the textView. Notice how we don't use the setImageResource but we use setImageBitmap because the image is in the form of bitmap.
        } catch (Exception e){

            e.printStackTrace();

        }

        Log.i("Downloaded","Image");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        downloadedImage = findViewById(R.id.imageView);
    }

    public class ImageDownloader extends AsyncTask<String,Void, Bitmap>{ //here we are setting the return type to Bitmap because we want to return image and not the webpage contents like previous one.

        @Override
        protected Bitmap doInBackground(String... urls) {

            try {

                URL url = new URL(urls[0]);

                HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection(); //THis can throw an exception probably IOException. This happens if the user doesn't have the permission to use the internet or the internet is connected to the device.

                urlConnection.connect(); //we connect to the connection. Here in images case it is different than downloading webpages.

                InputStream in = urlConnection.getInputStream(); //we get all the data of an image in one go

                Bitmap myBitmap = BitmapFactory.decodeStream(in); //Then we convert that downloaded data to the bitmap image. We are decoding the inputStream. Inside Bitmap we can use bitmapFactory to do multiple functions and things.

                return myBitmap;

            } catch (MalformedURLException e) { //this one occurs if the url is malformed
                e.printStackTrace();

            } catch (IOException e) { //
                e.printStackTrace();
            }

            return null; //if the image undergoes some problems than it will be null.
        }
    }
}