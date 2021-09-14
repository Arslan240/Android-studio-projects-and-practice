package com.example.whatsapp.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.whatsapp.Adapters.UsersAdapter;
import com.example.whatsapp.R;
import com.example.whatsapp.databinding.FragmentChatBinding;
import com.example.whatsapp.models.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class ChatFragment extends Fragment {

    ArrayList<Users> list = new ArrayList<Users>();
    FragmentChatBinding binding;
    FirebaseDatabase database;

    public ChatFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // we've already inflated the layout so we'll not inflate it again. We'll just pass the inflator to it.
        binding = FragmentChatBinding.inflate(inflater,container,false);

        database = FirebaseDatabase.getInstance();

        UsersAdapter adapter = new UsersAdapter(list,getContext());

        binding.chatRecyclerView.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

        binding.chatRecyclerView.setLayoutManager(linearLayoutManager);


        //this method is used to retreive all the data from the database. We add sth called ValueEventListener to Users.
        database.getReference().child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                    list.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Users users = dataSnapshot.getValue(Users.class);
//                    users.getUserID();
                    list.add(users);
                }

                //not the ideal method to tell the adapter that something has changed. This method will force everything to be loaded within its range because it is told something has changed but method does not tell where it is changed. Instead use other methods like
                // notifyItemChanged(int), notifyItemInserted(int), notifyItemRemoved(int), notifyItemRangeChanged(int, int), notifyItemRangeInserted(int, int), notifyItemRangeRemoved(int, int)
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println(error.getMessage());
            }
        });


        return binding.getRoot();
    }
}