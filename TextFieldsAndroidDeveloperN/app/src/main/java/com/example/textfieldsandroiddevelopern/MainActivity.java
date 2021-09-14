package com.example.textfieldsandroiddevelopern;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showSomethingClicked(View view){

        EditText myTextField = findViewById(R.id.myTextField); /*Here a bunch of things are happening here.
                                                                 1.TextField is a subclass of class EditText that's why EditText is the
                                                                 variable type.
                                                                 2. then we use method findViewById() method to find the actual TextField
                                                                    using its declared id.
                                                                 3. in tutorial that guy explicitly casted that method cz it returns View
                                                                    but here it seems like they are being implicitly casted to an EditText.*/

        Log.i("Info: ",myTextField.getText().toString()); //id.getText() method to get the text enterd then we convert into string to show it.
        System.out.println("lHAHAHAHlalalala");
        Toast.makeText(MainActivity.this, "Hi "+myTextField.getText(), Toast.LENGTH_LONG).show(); //Toast is a type of prompt that is visible for a short amount of time on the screen


    }
}