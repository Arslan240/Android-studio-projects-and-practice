package com.example.hiding_showingelements;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

enum State {
    vis,invis
}
public class MainActivity extends AppCompatActivity {

    TextView textView;
    State state;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        state = State.vis;

    }
   public void showElements(View view ){
        if(state == State.invis) {
            textView.setVisibility(View.VISIBLE);
            state = State.vis;
        }
   }

    public void hideElements(View view ){
        if(state == State.vis) {
            textView.setVisibility(View.INVISIBLE);
            state = State.invis;
        }
    }


}