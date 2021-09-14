package com.example.firebaseautomatic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference myRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("message"); //this path is like key. When we set value using method setValue(), we are setting a key value pair where message is key and Hello World is value.


        myRef.setValue("Hello, World!");

        myRef = database.getReference("second message"); //here if we change the database.getReference() it will create a new key and the value will be stored with that key pair.
        myRef.setValue("All is Well");


        //----------------------------------- READING FROM FIREBASE -----------------------------------

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                //Because we know the value is String so we will declare String variable.
                String value;

                value = snapshot.getValue(String.class); //we want to get value so we use getValue() function and then pass it the instance of the type the value is. E.g the value is of type String, so we pass String.class.
                
                Toast.makeText(MainActivity.this, value, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}