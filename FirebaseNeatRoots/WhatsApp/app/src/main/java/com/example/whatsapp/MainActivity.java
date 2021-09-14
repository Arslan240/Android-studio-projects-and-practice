package com.example.whatsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.whatsapp.Adapters.FragmentAdapter;
import com.example.whatsapp.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();
        binding.viewPager.setAdapter(new FragmentAdapter(getSupportFragmentManager())); //adding adapter to pagerview and passing fragment manager.
        binding.tabLayout.setupWithViewPager(binding.viewPager); //we set viewpager for the tablayout so that it can switch between those tabs.




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu); //.inflate(R.menu.menu, menu) the 2nd menu is the menu that is accepted by the onCreateOptionsMenu() method.

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) //we use switch case on the item Ids that are in the menu bar we just created in the R.menu
        {
            case R.id.settings:
                break;
            case R.id.logout:

                auth.signOut(); //we sign out using simple method in the FirebaseAuth class.
                Intent intent = new Intent(MainActivity.this,SigninActivity.class);
                startActivity(intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}