package com.example.fadinganimations;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    int state = 0;
    ImageView carA;

    public void fadeAtoB(View view) {
            ImageView carA = findViewById(R.id.carA);
//            ImageView carB = findViewById(R.id.carB);
//            carA.animate().alpha(0f).setDuration(2000); //2000th of a minute = 2 sec , alpha 0 = invisible, alpha 1 = total visible.
//        carB.animate().alpha(1f).setDuration(2000);
//      carA.animate().translationYBy(540f).setDuration(2000); //when we click this will move the image vertically to the down of screen
//        carA.animate().translationXBy(1000f).setDuration(2000);
//        carA.animate().rotation(500).setDuration(2000);
//        carA.animate().scaleX(2).scaleY(2).setDuration(2000);
        carA.animate().scaleX(2).scaleY(5).rotationBy(30000).setDuration(2000);

        if(state == 1){ //
            carA.animate().scaleX(1).scaleY(1).setDuration(2000);
            state = 0;
        }
        else if(state == 0){
            state = 1;
            System.out.println("Hala leua321");;
        }

//        if (state == 0) {
//            image.setImageResource(R.drawable.b);
//            state = 1;
//        }else if(state == 1) {
//            image.setImageResource(R.drawable.a);
//            state = 0;
//        }
    }

    public void fadeBtoA(View view) {
//        ImageView carB = findViewById(R.id.carB);
        ImageView carA = findViewById(R.id.carA);
//        carB.animate().alpha(0f).setDuration(2000); //2000th of a minute = 2 sec , alpha 0 = invisible, alpha 1 = total visible.
        carA.animate().alpha(1f).setDuration(2000);
    }
        @Override
    protected void onCreate(Bundle savedInstanceState) { //This is the method which is run when the app runs and sets everything up. If we want to place something that is off the screen we can't see it we can do it here.
                                                         //e.g I want to place the car to the left of the screen and on tap it will show on the screen.
                                                        // we can use setTranslateX or Y. This is similar as translate function with animate method but there is not duration it happens instantly.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView carA = findViewById(R.id.carA);
//        carA.setTranslationX(-1000f);
    }
}