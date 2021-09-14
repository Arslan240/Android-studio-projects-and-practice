package com.example.listviews;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import static java.util.Arrays.asList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView myListView = findViewById(R.id.myListView);
        String[] friends = {"Ali","Ahmad","Abdullah","Saim"};

        //to create an ArrayList in one single line. We use a static method from the class Arrays.
        ArrayList<String> frie = new ArrayList<>(asList("Saim","LALA","Ubaidullah","Sain Zahoor"));


//        for(String s : friends){
//            frie.add(s);
//        }

        ArrayAdapter<String> myAdapterView = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,frie);
        myListView.setAdapter(myAdapterView);

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, parent.getAdapter().getItem(position).toString(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}