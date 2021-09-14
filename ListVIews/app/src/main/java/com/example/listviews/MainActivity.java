package com.example.listviews;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.internal.BaselineLayout;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView myList = findViewById(R.id.myListView);

//        ArrayList<String> myfamily = ArrayList<>.(Arrays.asList("Arslan","M Jamil","Najma Jamil","Zeeshan Jamil","Aqeela Andleeb"));

//        myfamily.add("Arslan");
//        myfamily.add("Muhammad Jamil");
//        myfamily.add("Najma Jamil");
//        myfamily.add("Aqeela Andleeb");
//        myfamily.add("Zeeshan Jamil");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,myfamily);//(context,type of the list, arraylist which we want to add to the list view).
        myList.setAdapter(arrayAdapter); //we can't set items to the list view directly. We use arrayadapter to get the values and convert them to the type (i.e ListView in this case) we want to use them in.


        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() { //a change listener that will listen to the clicks on the list view. Basically, some code is already running when we click on an item of a ListView
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) { //(AdapterView<?> parent) is used instead of AdapterView<ListView> becuase at this time we are using generic type. Like in the case we don't know what type of adapterview is it. It can be  of any type that's what '?' is for
                //parent.setVisibility(View.GONE); //it will disappear the list when we click on it.
                //System.out.println("Family member : "+parent.getItemAtPosition(position).toString()); //getItemAtPosition(position) will return the data of that item on which user has clicked
                Toast.makeText(getApplicationContext(), "Hello ", Toast.LENGTH_LONG).show();

            }
        });

    }
}