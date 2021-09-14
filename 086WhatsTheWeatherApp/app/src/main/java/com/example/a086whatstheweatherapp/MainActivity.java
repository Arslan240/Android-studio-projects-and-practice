package com.example.a086whatstheweatherapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

    //----------------------------------------------------------- API STUFF -------------------------------------------------------------------
    String api_Key = "a51b2d7605e381417dd60dc7e2ededdf";
    String api_call_link_p1 = "https://api.openweathermap.org/data/2.5/weather?q=";
    String city_name = ""; //it will take entry from the user and we'll add all three to make a full apicall link
    String api_call_link_p3 = "&appid=a51b2d7605e381417dd60dc7e2ededdf";
    String api_call_link_full;

    //----------------------------------------------------------- LAYOUT STUFF -------------------------------------------------------------------
    EditText userEntry;
    TextView reportView;
    TextView reportHeading;

    //----------------------------------------------------------- FUNCTIONAL STUFF -------------------------------------------------------------------
    String unFinalizedResult = "";
    String finalMessage = "";

    public void checkWeather(View view){

        //we are using input method manager to remove the keyboard as soon as the button is clicked for checking the weather.
        InputMethodManager imgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imgr.hideSoftInputFromWindow(userEntry.getWindowToken(),0); //we tell it to hide input from window token and the specifies the edit text which is being given input to

        try {
            city_name = URLEncoder.encode((userEntry.getText().toString()).toLowerCase(), "UTF-8"); //getting name and converting to lower case. and also encoding it so that blank spaces can be converted to urls properly

            getApiLink(); //making the full api link
            //System.out.println(api_call_link_full);

            Downloader getWeatherReport = new Downloader();

            getWeatherReport.execute(api_call_link_full); //passing the link to the downloader to get json stuff

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            Toast.makeText(MainActivity.this, "Could not find weather.", Toast.LENGTH_LONG).show();
        }

        //if user enters


    }

    public class Downloader extends AsyncTask<String,Void,String>{

        String result = "";
        URL url;
        HttpURLConnection urlConnection;

        @Override
        protected String doInBackground(String... urls) {

            try {

                url = new URL(urls[0]);

                urlConnection = (HttpURLConnection) url.openConnection();

                InputStream in = urlConnection.getInputStream();

                InputStreamReader reader = new InputStreamReader(in);

                int data = reader.read();

                while(data != -1){
                    char sequence = (char) data;
                    unFinalizedResult += sequence;
                    data = reader.read();

                }

                Log.i("doInBackground","Data is downloaded.");
                return unFinalizedResult;

            } catch (Exception e){
                Toast.makeText(MainActivity.this, "Could not find weather.", Toast.LENGTH_LONG).show();
                return null;
            }

        }

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        protected void onPostExecute(String s) { //when downloading is completed.
            super.onPostExecute(s);

            try {   // 1. we first get contents of jsonObject weather from the whole lot of content from the api call. 2. Then because that weather section is an array of jsonObjects so we get that weather info in an jsonArray. 3. We get the specific jsonObjects from that jsonArray which we need. 4. We

                /* //this is what i understood about getting the json data from json array but it didn't work.
                JSONObject jsonObject = new JSONObject(unFinalizedResult);
                String weatherInfo = jsonObject.getString("weather");//here we get an object which have weather info

                JSONArray jsonArray = new JSONArray(weatherInfo);
                JSONObject wMain = jsonArray.getJSONObject(1);
                String w_main = wMain.getString("main");

                JSONObject wDescription = jsonArray.getJSONObject(2);
                String w_description = wMain.getString("main");
                 */

                JSONObject jsonObject = new JSONObject(unFinalizedResult);
                String weatherInfo = jsonObject.getString("weather");//here we get an object which have weather info

                JSONArray array = new JSONArray(weatherInfo);

                for (int i = 0; i < array.length(); i++) { //this part retrieves data from jsonarray  but i can't understand it how it works.
                    JSONObject jsonPart = array.getJSONObject(i);

                    String w_main = "";
                    String w_description = "";

                    w_main = jsonPart.getString("main");
                    w_description = jsonPart.getString("description");

                    //showing the data to the user if the message is extracted.
                    if (w_main != "" && w_description != "") {
                        finalMessage = String.format("%s : %s\n", w_main, w_description);
                    }
                }

                if (finalMessage != "") { //setting the textViews
                    reportView.setText(finalMessage);
                    reportHeading.setVisibility(View.VISIBLE);
                    reportView.setVisibility(View.VISIBLE);
                }else{
                    Toast.makeText(MainActivity.this, "Could not find weather.", Toast.LENGTH_LONG).show();
                }



            } catch (JSONException e) {
                e.printStackTrace();
                Log.i("onPostExecute","Failed");
            }
        }
    }

    private void getApiLink(){
        api_call_link_full = api_call_link_p1 + city_name + api_call_link_p3; //full link
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userEntry = findViewById(R.id.cityName);
        reportView = findViewById(R.id.reportView);
        reportHeading = findViewById(R.id.reportHeading);

    }
}