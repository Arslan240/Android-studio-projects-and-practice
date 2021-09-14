package com.example.a084processingjsondata;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    //openweather map api key = a51b2d7605e381417dd60dc7e2ededdf
    //successful api call for london = http://api.openweathermap.org/data/2.5/weather?q=London&appid=a51b2d7605e381417dd60dc7e2ededdf

    String api_Key = "a51b2d7605e381417dd60dc7e2ededdf";
    String api_call_link = "https://api.openweathermap.org/data/2.5/weather?q=london&appid=a51b2d7605e381417dd60dc7e2ededdf";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Downloaders task = new Downloaders();
        String result = null;


        task.execute(api_call_link);


    }

    public class Downloaders extends AsyncTask<String,Void,String>{
            String result = "";
        @Override
        protected String doInBackground(String... urls) {
            URL url;
            HttpURLConnection urlConnection;

            try {
                url = new URL(urls[0]);

                urlConnection = (HttpURLConnection) url.openConnection();

                InputStream in = urlConnection.getInputStream();

                InputStreamReader reader = new InputStreamReader(in);

                int data = reader.read();

                while (data != -1) {
                    char sequence = (char) data;
                    result += sequence;
                    data = reader.read();
                }

                return result;
            } catch (IOException e) {
                e.printStackTrace();
                return "Failed Downloading";
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            //here we are creating jsonobject to convert our string into a json object because mostly apis work on the basis of json.
            try {

                JSONObject weather = new JSONObject(result);

                Log.i("Weather report",weather.getString("weather")); //this method will return the values where the key is weather for data in json file.

            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.i("Result",result);
        }
    }
}