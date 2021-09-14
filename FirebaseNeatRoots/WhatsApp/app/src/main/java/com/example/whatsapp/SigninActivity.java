package com.example.whatsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.whatsapp.databinding.SigninActivityBinding;
import com.example.whatsapp.models.Users;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.FirebaseDatabase;

public class SigninActivity extends AppCompatActivity {

    SigninActivityBinding binding;
    ProgressDialog progressDialog;
    FirebaseAuth auth;
    FirebaseDatabase database;

    GoogleSignInClient mGoogleSignInClient;
    GoogleSignInOptions gso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = SigninActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        progressDialog = new ProgressDialog(SigninActivity.this);
        progressDialog.setTitle("Signing in");
        progressDialog.setMessage("We're signing you in.");




        binding.signinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();

                //trying to signin using emain and password
                auth.signInWithEmailAndPassword(binding.emailEditText.getText().toString(),binding.passwordEditText.getText().toString()).
                        addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressDialog.dismiss();

                                //if the login is successful we direct the user to the main activity.
                                if(task.isSuccessful()){
                                    Intent intent = new Intent(SigninActivity.this,MainActivity.class);
                                    startActivity(intent);
                                }
                                else{
                                    Toast.makeText(SigninActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show(); //if the task is not successful we show the firebase exception to the user.
                                }

                            }
                        });

            }
        });


        //clickforSignup Textview
        binding.SignupTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //we direct the user to SignUpActivity.
                Intent intent = new Intent(SigninActivity.this,Signup_Activity.class);
                startActivity(intent);
            }
        });


        //checking if a user is already signed in or not.
        if(auth.getCurrentUser() != null){
            Intent intent = new Intent(SigninActivity.this,MainActivity.class); //directed to the MainActivity.
            startActivity(intent);
        }



        //addding clicklistener to the google signin button
        binding.googleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });


        // Configure Google Sign In
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    //To make google signin work we need to enable google signin for out project in firebase and also we have to add SHA-1
    //for security of our app and stuff.



    //related to google signin.
    //When the google button is clicked, this method is called.
    //This method has an intent and it will take us to a new screen which will ask us to use an already signed in google account
    //or to add a new account. And from that activity we'll fetch data back to this signinActivity.

    int RC_SIGN_IN = 20; //10 to 99 any values.
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);

    }

    //the method startActivityForResult is called then we use onActivityResult method and stuff is done in it.
    //if the RC_SIGN_IN is equal to the number then we do stuff in try block.
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {

                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.d("TAG", "firebaseAuthWithGoogle:" + account.getId());
                firebaseAuthWithGoogle(account.getIdToken()); //this method is copied and pasted from the firebase website page.

            } catch (ApiException e) {

                // Google Sign In failed, update UI appropriately
                Log.w("TAG", "Google sign in failed", e);

            }
        }
    }

    private void firebaseAuthWithGoogle(String idToken) {

        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            // Sign in success, update UI with the signed-in firebaseUser's information
                            Log.d("TAG", "signInWithCredential:success");
                            FirebaseUser firebaseUser = auth.getCurrentUser();

                            //Updating the users in the real time database
                            //probably should make a new method for this.
                            updateRTDatabase(firebaseUser);


                            Intent intent = new Intent(SigninActivity.this,MainActivity.class);
                            startActivity(intent); //firebaseUser is moved to mainactivity
                            Toast.makeText(SigninActivity.this, "Sign in with Google successful.", Toast.LENGTH_SHORT).show();
                            //updateUI(firebaseUser);

                        } else {

                            // If sign in fails, display a message to the user.
                            Log.w("TAG", "signInWithCredential:failure", task.getException());
                            //updateUI(null);

                        }
                    }
                });
    }



    //storing the data of user who signed in by using Google sign in. Idk if it'll happen everytime they signin using google.
    private void updateRTDatabase(FirebaseUser firebaseUser) {
        try {
            Users users = new Users();
            users.setUserID(firebaseUser.getUid()); //set the Uid in database using the id created by Firebase
            users.setUserName(firebaseUser.getDisplayName());
            users.setProfilePic(firebaseUser.getPhotoUrl().toString()); //setting the profile pic

            //This line will add everything to the database.
            database.getReference().child("Users").child(firebaseUser.getUid()).setValue(users);

            Toast.makeText(this, "Data stored in database", Toast.LENGTH_SHORT).show();

        }catch (Exception e){ //if not successfull
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }





}