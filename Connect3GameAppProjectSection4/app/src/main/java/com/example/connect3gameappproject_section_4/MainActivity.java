package com.example.connect3gameappproject_section_4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.view.View;


import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    //it represents the player
    int activePlayer = 0;  //0 = yellow , 1 = red

    //to check player can't play after he has won. To play again you have to click play again
    boolean gameIsActive = true;

    //we use an array to store the game state. 9 elements = each ImageView equals 1 element. 2 shows that the box is unplayed. When played by a certain player we store the number of that player's box to the corresponding element. We use tags to identify which box has been clicked by giving each box/ImageView a tag value like elements in an array starting from 0 to 8 for 9 total elements.
    int[] gameState = {2,2,2,2,2,2,2,2,2};

    int[][] winning_Positions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}}; //these are total 8 winning combinations, which can occur. It include rows, columns and diagonals. 0,1,2,... those values are tags for the boxes and indexes of the game states are also stored on the same pattern so we can easily compare them. If any of these combinations have same player, that player has won.


    public void dropIn(View view) {

        int whileSpecifier = 0; // 1 = play again, 2 = exit;

        ImageView counter = (ImageView) view; //here we are getting the imageview which is tapped by the user. We don't use R.findById.id cz we don't know which image will the user click on. So we just use abstraction and get whatever image he taps on. To the function View is passed so we have to convert View to ImageView by typecasting it.
        System.out.println(counter.getTag().toString()); //todo remove after debugging


        int tappedCounter = Integer.parseInt(counter.getTag().toString()); //getting the tag in form of integer to compare to gamestate.

        if (gameState[tappedCounter] == 2 && gameIsActive == true) { //2 = unplayed. If that state is unplayed we play otherwise we do nothing

            gameState[tappedCounter] = activePlayer; //then we change the gameState of that box to the played by specific player so it don't get played again.

            //counter.animate().alpha(0); //the image disappears //when the game starts there are no ticks or shit. The board is plain. So we don't need this anymore.
            //counter.animate().setTranslationYBy(-1000f); //It didn't work. The one without animate() worked down below.
                                                           //we move it out of the screen without any time lag cz we are using translationYBy i guess

            counter.setTranslationY(-1000f); //it is still needed cz we want the sticker to come into view from out of the scree. If we don't send it outside we can't make it move where it already is.

            if (activePlayer == 0) {  //setting image resources
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else if(activePlayer == 1){
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }

            counter.animate().alpha(1); //again making the image visible before animating it onto the screen
            counter.animate().rotationBy(360).translationYBy(1000f).setDuration(1500); //500 ms. this way it will appear from top and move down to its place

            //If Someone has won or not.
            for(int winning_Position[]: winning_Positions){

                //winning_Position[] lets us iterate over 8 rows one by one of winning_Positions[][]. first 1st row, second 2nd row, ...
                //then we check the values of combinations stored inside gamestate[] for every box.

                if(gameState[winning_Position[0]] == gameState[winning_Position[1]] && gameState[winning_Position[1]] == gameState[winning_Position[2]]
                                    && gameState[winning_Position[0]] != 2){ //players are same and game is not unplayed

                    gameIsActive = false; //we set that game is won and now is inactive.

                    String winner = "Red";
                    if(gameState[winning_Position[0]] == 0){ //we print out the name 0=yellow, 1=red

                        winner = "Yellow";
                    }

                    LinearLayout linearLayout = (LinearLayout) findViewById(R.id.playAgainLayout); //simple typecasting.

                    //set the color of the layout panel that pops up. If the red player win background color set to red and yellow otherwise.
                    if(gameState[winning_Position[0]] == 0) {
                        linearLayout.setBackgroundColor(Color.YELLOW);
                    }
                    else if(gameState[winning_Position[0]] == 1){
                        linearLayout.setBackgroundColor(Color.RED);
                        TextView textView = findViewById(R.id.winnerMessage);
                        textView.setTextColor(Color.WHITE);
                    }

                    TextView winnerMessage = findViewById(R.id.winnerMessage);
                    winnerMessage.setText(winner +" has won.");

                    linearLayout.setTranslationY(-1500f); //we'll send it upwards
                    linearLayout.setVisibility(View.VISIBLE); //this sets the visibility to the visible.
                    linearLayout.animate().translationYBy(1500f).setDuration(1000);

                }
                else { //to check if no one has won and them being able to playAgain
                    boolean gameIsOver = true;
                    for(int state:gameState){
                        if(state == 2){
                            gameIsOver = false; //2 = unplayed box
                        }
                    }

                    if(gameIsOver == true){
                        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.playAgainLayout); //simple typecasting.

                        TextView winnerMessage = findViewById(R.id.winnerMessage);
                        winnerMessage.setText("It's a draw.");

                        linearLayout.setTranslationY(-1500f); //we'll send it upwards
                        linearLayout.setVisibility(View.VISIBLE); //this sets the visibility to the visible.
                        linearLayout.animate().translationYBy(1500f).setDuration(1000);
                    }

                }

            }

        }
    }



    //my method
//    public void playAgain(View view){
//
//        //we resets all the values so that user can play the game again
//        activePlayer = 0;
//        for(int i = 0; i < 9;i++){
//            gameState[i] = 2;
//        }
//
//        setContentView(R.layout.activity_main); //It resets the layout again but don't load the class. That's why we have to manually reset the values.
//    }


    //Instructors Method
    public void playAgain(View view){
        LinearLayout linearLayout = findViewById(R.id.playAgainLayout);
        linearLayout.setVisibility(View.INVISIBLE);


        gameIsActive = true; // when resetting we set the game is active again
        activePlayer = 0;
        for(int i = 0; i < gameState.length ;i++){
            gameState[i] = 2;
        }

        //now we set the image resources of all the ImageViews to nothing. so that it is playable.

        //Here you gotta make a variable from class androidx.gridlayout.widget.GridLayout. Other wise the app keeps crashing.
        GridLayout gridLayout =  findViewById(R.id.gridLayout);

        for(int i = 0; i < gridLayout.getChildCount(); i++) {
            //we first getChildAt(index of the childs) of the grid using i to iterate over all the childs. Then we convert the childs to ImageView and then we setImageResource(0).
            ((ImageView)gridLayout.getChildAt(i)).setImageResource(0);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}























