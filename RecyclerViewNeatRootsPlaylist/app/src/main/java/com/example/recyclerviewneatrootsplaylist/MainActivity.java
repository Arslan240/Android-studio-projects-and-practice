package com.example.recyclerviewneatrootsplaylist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.recyclerviewneatrootsplaylist.Adapters.RecipeAdapter;
import com.example.recyclerviewneatrootsplaylist.Models.RecipeModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        ArrayList<RecipeModel> list = new ArrayList<>();

        list.add(new RecipeModel(R.drawable.pone,"Food1"));
        list.add(new RecipeModel(R.drawable.ptwo,"Food1"));
        list.add(new RecipeModel(R.drawable.pthree,"Food1"));
        list.add(new RecipeModel(R.drawable.pfour,"Food1"));
        list.add(new RecipeModel(R.drawable.pfive,"Food1"));
        list.add(new RecipeModel(R.drawable.psix,"Food1"));
        list.add(new RecipeModel(R.drawable.pseven,"Food1"));
        list.add(new RecipeModel(R.drawable.peight,"Food1"));
        list.add(new RecipeModel(R.drawable.pnine,"Food1"));

        RecipeAdapter adapter = new RecipeAdapter(list,this);
        recyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


    }
}