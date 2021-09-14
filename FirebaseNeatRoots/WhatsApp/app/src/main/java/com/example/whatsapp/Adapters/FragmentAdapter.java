package com.example.whatsapp.Adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.whatsapp.Fragments.CallsFragment;
import com.example.whatsapp.Fragments.ChatFragment;
import com.example.whatsapp.Fragments.StatusFragment;


//FragmentPagerAdapter uses a lot of memory that's why we should use FragmentStagePagerAdapter or something like this.
//FragmentPagerAdapter stores everything in the memory and loads from there every time we need it after storing.
//Where as on the other hand FragmentStagePagerAdapter creates a new activity everytime and destroys it after using it.
public class FragmentAdapter extends FragmentPagerAdapter {



    public FragmentAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {

        //This method is used to switch between different fragments classes that we have created.
        switch (position){
            case 0:
                return new ChatFragment();
            case 1:
                return new StatusFragment();
            case 2:
                return new CallsFragment();
            default:
                return new ChatFragment(); //idk in tutorial the guy said this method is for cursor appointment.
                                            //idk
        }
    }


    @Override
    public int getCount() { //this method returns the total number of fragments that we are dealing with in this adapter.
        return 3;
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        //this method will set the titles of the fragments.

        String title = null;
        if(position == 0){
            title = "CHATS";
        }else if(position == 1){
            title = "STATUS";
        }else if(position == 2){
            title = "CALLS";
        }

        return title;

    }
}
