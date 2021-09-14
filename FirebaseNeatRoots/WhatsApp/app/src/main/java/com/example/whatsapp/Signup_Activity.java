package com.example.whatsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.whatsapp.databinding.SignupActivityBinding;
import com.example.whatsapp.models.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Signup_Activity extends AppCompatActivity {

    //Here we are using view binding technique introduced. It replaces findViewById().
    //By using view binding we eliminate the risk of referencing a null view in layout.
    private SignupActivityBinding binding;

    private FirebaseAuth auth;
    private FirebaseDatabase database;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //using view binding
        binding = SignupActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        progressDialog = new ProgressDialog(Signup_Activity.this);
        progressDialog.setTitle("Creating Account");
        progressDialog.setMessage("We're creating your account");

        //adding an OnclickListener to the SignUp button
        binding.signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show(); //showing the dialog when the button is clicked

                //when the button is clicked we create a new user using the method provided to us ,using auth variable of FirebaseAuth.
                //we pass email and password to the function to create new user using those credentials.
                auth.createUserWithEmailAndPassword(binding.emailEditText.getText().toString(),
                        binding.passwordEditText.getText().toString()).
                        addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                            //this is the method which is called when the method createUserWithEmailAndPassword() is completed.
                            //and here we check if it is completed successfully and all those things.
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressDialog.dismiss(); //hiding when the task is completed.

                                if(task.isSuccessful()){

                                    //Inside task we are going to make a new User object and pass the values to the constructor. Then we will pass the reference of this user to the firebase and it will be stored in the real time database.

                                    Users user = new Users(binding.userNameEditText.getText().toString(),binding.emailEditText.getText().toString(),
                                            binding.passwordEditText.getText().toString()); //a new user is created.

                                    //here we get the UId of new user who signed up from the firebase. This UId is created by firebase.
                                    //task is the task performed by the firebase and it generates result. We get the result and from the result we can get the user and the id of that user.
                                    String id = task.getResult().getUser().getUid();

                                    //here we create a new node named Users to store the credentials of the Users.
                                    //To store it we pass on the reference to the Users object.
                                    //After getting the reference we can create as many childs or nodes of a node as we want.
                                    //Then to store value in a node we use method setValue and pass the reference to the object.
                                    database.getReference().child("Users").child(id).setValue(user);


                                    Toast.makeText(Signup_Activity.this, "New User created Successfully.", Toast.LENGTH_SHORT).show();

                                }
                                else{
                                    //if any exception is thrown e.g. mail is not of correct format or otherthings, we'll catch those exceptions in the toast for the moment.
                                    Toast.makeText(Signup_Activity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                                }

                            }
                });

            }
        });


        //clickforSignin
        binding.SigninTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Signup_Activity.this,SigninActivity.class); //directing the user to the signinActivity.
                startActivity(intent);
            }
        });

        //checking if a user is already signed in or not.
        if(auth.getCurrentUser() != null){
            Intent intent = new Intent(Signup_Activity.this,MainActivity.class); //directed to the MainActivity.
            startActivity(intent);
        }


        getSupportActionBar().hide();

    }
}