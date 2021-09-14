package com.example.downloadingwebcontentsandmultithreading_part5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    public class DownloadTask extends AsyncTask<String, String, String>{ //first param is the action that will be performed on parallel thread. In this case it will be url of the page or anything that needs to be downloaded. Second param is for the progress of the parallel thread running Instructor actually made it void but in my case it is saying that void is not allowed here. 3rd param is about


        @Override
        protected String doInBackground(String... urls) { //String... strings = var args[] i guess like that. we can pass it many arguments as we like.

            String result= "";
            URL url; //url is basically a string but it has to be in a certain format and a type of url to work as a url in java. URL class might has methods to check the formats and stuff.
            HttpURLConnection urlConnection = null; //this httpurl connection is a kind of a browser for our url

            try {
                Log.i("Inside try"," statement");

                url = new URL(urls[0]); //converting the strings passed in to the urls

                urlConnection = (HttpURLConnection) url.openConnection(); //we open the connection for the url we just created above. This is kind of like the browser from where we can view and download stuff.

                InputStream in = urlConnection.getInputStream(); //creating the input stream. It is kind of a channel which reads the input from the url using urlConnection.

                InputStreamReader reader = new InputStreamReader(in); //This is reader which reads the contents through the inputStream in.

                int data = reader.read(); //we are storing data temporarily in the int. idk why

                while (data != -1){
                    char current = (char) data; //converting data to char because reader reads data char by char.
                    Log.i("Inside data fetching"," loop");
                    result += current; //Our data or whole page is stored in the form of a long String. We add every next read char to the string.
                    data = reader.read();
                }

                return result; //this returns to the result string in the onCreate method where task.execute method is called.

            }
            catch (Exception e){
                e.printStackTrace();
                return "Failed";
            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("haha","");

        DownloadTask task = new DownloadTask();
        String result = null;

        try {
            result = task.execute("https://www.journaldev.com/").get(); //a url is passed
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Log.i("Result : ",result);
        Log.i("Task ","Finished");
    }
}