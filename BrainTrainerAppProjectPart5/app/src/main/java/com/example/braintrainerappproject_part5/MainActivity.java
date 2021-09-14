package com.example.braintrainerappproject_part5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.security.SecureRandom;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    GridLayout gridLayout;
    LinearLayout linearLayout;

    TextView timerTextView;
    TextView numbersToBeAddedTextView;
    TextView scoreTextView;
    TextView feedBackTextView;

    //options for right answers
    TextView option1;
    TextView option2;
    TextView option3;
    TextView option4;

    //Buttons
    Button goButton;
    Button playAgainButton;

    //Random numbers
    SecureRandom secureRandom = new SecureRandom();

    //Functional variables
    int sum = 0;
    int score = 0;
    int counter = 0;
    int number1 = 0;
    int number2 = 0;
    int upperBound = 0;
    int digitsForRandomNumbers = 0;
    boolean userWantToPlay = false;
    int[] numbers = {0,0,0,0}; //To implement the logic of assigning answer to the textViews we can use this to store numbers and correct answers at random location.


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //when timer is ended then this set to false and the loop (not essentially the loop but it will be called when user clicks on one of the answers) of creating random numbers and stuff will be ended
        boolean goOn = true;

        //----------------------------------------------------------------------------------------------------------------------------------

        //Layouts initializations
        gridLayout = findViewById(R.id.gridLayout);
        linearLayout = findViewById(R.id.linearLayout);

        //TextViews initialization
        timerTextView = findViewById(R.id.timer);
        numbersToBeAddedTextView = findViewById(R.id.numbersAdded);
        scoreTextView = findViewById(R.id.score);
        feedBackTextView = findViewById(R.id.answerFeedback);

        //Answer Options TextViews
        option1 = findViewById(R.id.ans1);
        option2 = findViewById(R.id.ans2);
        option3 = findViewById(R.id.ans3);
        option4 = findViewById(R.id.ans4);

        //Buttons
        goButton = findViewById(R.id.goButton);
        playAgainButton = findViewById(R.id.playAgainButton);

        //----------------------------------------------------------------------------------------------------------------------------------


        //if(userWantToPlay == true) { //todo idk but count downn timer has stopped working after implementing if statement.
                                       //todo And when timer stops then upon clicking play again the timer does not start again.
            numberGenerationAndUpdatingTextViews();
            startCountDownTimer();

            /* //I didn't knew that timer will work where ever you call it so i just put it in onCreate method. But now I came to know that we can start it in another method also so i have created another method for it.
            //---------------------------------------------- TRYING TO IMPLEMENT TIMER ---------------------------------------------------
            int duration = 60_000; //one minute
            CountDownTimer timer = new CountDownTimer(duration, 1000) { //milliseconds, 1000 = 1 sec
                @Override
                public void onTick(long millisUntilFinished) {
                    //updating the timer at every tick
                    timerTextView.setText(String.format("%d" + " s", millisUntilFinished / 1000)); // because it is in long and milliseconds. To get seconds we divide it by 1000
                }

                @Override
                public void onFinish() {
                    // button will be visible and grid and linear layout will be unclickable hopefully.

                    //Instructors Implementation
                    timerTextView.setText("0 s"); //setting that there are 0s remaining
                    feedBackTextView.setText(String.format("%d / %d",score,counter)); //showing the user their score at ending

                    //it was my implementation
                    resetClickabilityToFalse();
                    playAgainButton.setVisibility(View.VISIBLE);
                }
            }.start();

             */
        //}
    }

    //-------------------------------------- PLAY AGAIN METHOD -----------------------------------------------------------------------------

    public void playAgain(View view){
        sum = 0;
         score = 0;
         counter = 0;
         number1 = 0;
         number2 = 0;
         upperBound = 0;
         digitsForRandomNumbers = 0;

        //Instructors implementation
        timerTextView.setText("0 s");
        feedBackTextView.setText("");
        scoreTextView.setText("0 / 0");
        numberGenerationAndUpdatingTextViews();
        startCountDownTimer();

        //My implementation was :
        resetClickabilityToTrue();
        playAgainButton.setVisibility(View.INVISIBLE);


    }

    public void startCountDownTimer(){
        //---------------------------------------------- TRYING TO IMPLEMENT TIMER ---------------------------------------------------
        int duration = 10_000; //one minute
        CountDownTimer timer = new CountDownTimer(duration, 1000) { //milliseconds, 1000 = 1 sec
            @Override
            public void onTick(long millisUntilFinished) {
                //updating the timer at every tick
                timerTextView.setText(String.format("%d" + " s", millisUntilFinished / 1000)); // because it is in long and milliseconds. To get seconds we divide it by 1000
            }

            @Override
            public void onFinish() {
                // button will be visible and grid and linear layout will be unclickable hopefully.

                //Instructors Implementation
                timerTextView.setText("0 s"); //setting that there are 0s remaining
                feedBackTextView.setText(String.format("%d / %d",score,counter)); //showing the user their score at ending

                //it was my implementation
                resetClickabilityToFalse();
                playAgainButton.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    private void resetClickabilityToTrue(){
        gridLayout.setFocusable(true);
        gridLayout.setFocusableInTouchMode(true);
        linearLayout.setFocusable(true);
        linearLayout.setFocusableInTouchMode(true);

    }

    private void resetClickabilityToFalse(){
        gridLayout.setFocusable(false);
        //gridLayout.setFocusableInTouchMode(false);
        linearLayout.setFocusable(false);
        //linearLayout.setFocusableInTouchMode(false);

    }

    private void resetVisibilityToTrue(){
        goButton.setVisibility(View.INVISIBLE);
        gridLayout.setVisibility(View.VISIBLE);
        gridLayout.setVisibility(View.VISIBLE);
        linearLayout.setVisibility(View.VISIBLE);
        linearLayout.setVisibility(View.VISIBLE);
        feedBackTextView.setVisibility(View.VISIBLE);
    }

    private void resetVisibilityToFalse(){
        goButton.setVisibility(View.VISIBLE);
        gridLayout.setVisibility(View.INVISIBLE);
        gridLayout.setVisibility(View.INVISIBLE);
        linearLayout.setVisibility(View.INVISIBLE);
        linearLayout.setVisibility(View.INVISIBLE);
        feedBackTextView.setVisibility(View.INVISIBLE);
    }


    public void play(View view){

        //Instructors implementation
        goButton.setVisibility(View.INVISIBLE);

        //my implementation
        userWantToPlay = true;
        resetVisibilityToTrue();
    }

    //--------------------------- METHOD CALLING OTHER METHODS THAT NEEDS TO BE REPEATED ON USER CLICK ------------------------------------
    public void numberGenerationAndUpdatingTextViews(){
        randomNumberGeneration(); //numbers are generated and the timers and stuff is updated

        //setting the question to be answered textview text.
        numbersToBeAddedTextView.setText(String.format(number1 + " + " + number2));

        updateAnswers(upperBound);
        updateScores();
    }

    //My implementation  --  Check Answers on user Click
    //However -- Instructors Implementation => We can just get the tag value of the view by using view.getTag.toString() and then just compare it to the location that was previously decided to put the right answers in the numbers[] array. In this way we can shave off 4 methods and just have one method and doing the function. However the problem is that we are not comparing the actual value by comparing the location. That might be a problem,
    public void checkAnswer(int tag,int value){
        if(sum == value){
            score++;
            feedBackTextView.setText("Correct");
        }else{
            feedBackTextView.setText("Wrong");
        }

        //counting how many times the user has clicked. Or in other words how many times the user has played.
        counter++;
        numberGenerationAndUpdatingTextViews();
    }


    //------------------------------------------- NOT SO IMPORTANT METHODS AND STUFF --------------------------------------------------------

    //update Answers in the textViews
    public void updateAnswers(int upperbound){
        int location = 1 + secureRandom.nextInt(4);

        for(int i = 0; i < 4; i++){ //Instructors implementation. We decide the location of sum and then we place it in the array according to the location.
            if(i == location){
                numbers[i] = sum;
            }else{
                numbers[i] = secureRandom.nextInt(upperbound);
            }
        }

        //simple yet effective implementation //but there is a problem that
        option1.setText(Integer.toString(numbers[0]));
        option2.setText(Integer.toString(numbers[1]));
        option3.setText(Integer.toString(numbers[2]));
        option4.setText(Integer.toString(numbers[3]));

        /* //my implementation of the logic to set numbers and sum to the textViews for user to click
        if(boxNumber == 1){
            option1.setText(Integer.toString(sum)); //answer is here
            option2.setText(Integer.toString(secureRandom.nextInt(upperbound)));
            option3.setText(Integer.toString(secureRandom.nextInt(upperbound)));
            option4.setText(Integer.toString(secureRandom.nextInt(upperbound)));
        }else if(boxNumber == 2){
            option1.setText(Integer.toString(secureRandom.nextInt(upperbound)));
            option2.setText(Integer.toString(sum)); //answer is here
            option3.setText(Integer.toString(secureRandom.nextInt(upperbound)));
            option4.setText(Integer.toString(secureRandom.nextInt(upperbound)));
        }else if(boxNumber == 3){
            option1.setText(Integer.toString(secureRandom.nextInt(upperbound)));
            option2.setText(Integer.toString(secureRandom.nextInt(upperbound)));
            option3.setText(Integer.toString(sum)); //answer is here
            option4.setText(Integer.toString(secureRandom.nextInt(upperbound)));
        }else if(boxNumber == 4){
            option1.setText(Integer.toString(secureRandom.nextInt(upperbound)));
            option2.setText(Integer.toString(secureRandom.nextInt(upperbound)));
            option3.setText(Integer.toString(secureRandom.nextInt(upperbound)));
            option4.setText(Integer.toString(sum)); //answer is here
        }
         */
    }

    //Random numbers generation and all that good stuff.
    public void randomNumberGeneration(){
        digitsForRandomNumbers = 1 + secureRandom.nextInt(2); //This will generate numbers 1 or 2
        upperBound = 0;
        if(digitsForRandomNumbers == 1){
            upperBound = 10;
        }else{
            upperBound = 100;
        }

        number1 = secureRandom.nextInt(upperBound);
        number2 = secureRandom.nextInt(upperBound);
        sum = number1 + number2;
    }

    //update Scores
    public void updateScores(){
        scoreTextView.setText(String.format("%d / %d",score,counter));
    }

    //On user clicks following methods are called.
    public void box1Clicked(View view){
        //getting the tag value and converting it to int
        int tag = Integer.parseInt(String.valueOf(option1.getTag()));
        int value = Integer.parseInt(option1.getText().toString());

        checkAnswer(tag,value);
    }

    public void box2Clicked(View view){
        //getting the tag value and converting it to int
        int tag = Integer.parseInt(String.valueOf(option2.getTag()));
        int value = Integer.parseInt(option2.getText().toString());

        checkAnswer(tag,value);
    }

    public void box3Clicked(View view){
        //getting the tag value and converting it to int
        int tag = Integer.parseInt(String.valueOf(option3.getTag()));
        int value = Integer.parseInt(option3.getText().toString());

        checkAnswer(tag,value);
    }

    public void box4Clicked(View view){
        //getting the tag value and converting it to int
        int tag = Integer.parseInt(String.valueOf(option4.getTag()));
        int value = Integer.parseInt(option4.getText().toString());

        checkAnswer(tag,value);
    }


}