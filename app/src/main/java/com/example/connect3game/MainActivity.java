package com.example.connect3game;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //0=YELLOW  1=RED
    int activePlayer = 0;
    boolean gameIsActive = true;
    //2 MEANS UN-PLAYED AND THE VALUES IN THE ARRAY REPRESENTS EACH IMAGE VIEW IN THE GRID LAYOUT!
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    /////THESE ARE ALL THE WINNING COMBINATIONS THAT CAN OCCUR AT ANY ONE TIME///////
    int[][] winningPositions = {
            {0,1,2},{3,4,5},{6,7,8},
            {0,3,6},{1,4,7},{2,5,8},
            {0,4,8},{2,4,6}


    };
    public void fadeIn(View view) {

        ImageView counter = (ImageView) view;
        // Log.i("Info",counter.getTag().toString());

        //HERE WE SET THE COUNTERS ONCE CLICKED//////
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if (gameState[tappedCounter] == 2 && gameIsActive) {
            gameState[tappedCounter]=activePlayer;
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }
        /////////////////CHECKING //////////////////////
            /////////////FOR//////////
            /////////////A/////////
            // //////////WINNER//////////
        for(int[]winningPosition:winningPositions){
            if(gameState[winningPosition[0]]==gameState[winningPosition[1]]
                    && gameState[winningPosition[1]]== gameState[winningPosition[2]]
                    && gameState[winningPosition[0]] != 2 && gameState[winningPosition[1]] != 2
                    && gameState[winningPosition[2]] != 2 && gameState[winningPosition[3]] != 2
                    && gameState[winningPosition[4]] != 2 && gameState[winningPosition[5]] != 2
                    && gameState[winningPosition[6]] != 2 && gameState[winningPosition[7]] != 2
                    && gameState[winningPosition[8]] != 2){



                /////////HERE WE MAKE THE LAYOUT APPEAR/////
                ///////CONTAINING THE TEXT OF THE WINNER//////
                ///////AND BUTTON TO PLAY AGAIN/////////////
                gameIsActive = false;
                String message = "Red";
                if(gameState[winningPosition[0]]==0){

                    message = "Yellow";
                }
                TextView winnerMessage = findViewById(R.id.winnerMessage);
                winnerMessage.setText(message + " has won");
                LinearLayout layout = findViewById(R.id.playAgainLayout);
                layout.setVisibility(View.VISIBLE);

            }
            //////HERE WE CHECK IF THE GAME IS A DRAW////////
            else{
                boolean gameIsOver = true;
                for(int counterState:gameState){
                    if(counterState==2)gameIsOver=false;
                }
                if(gameIsOver){
                    TextView winnerMessage = findViewById(R.id.winnerMessage);
                    winnerMessage.setText("game is a draw!");
                    LinearLayout layout = findViewById(R.id.playAgainLayout);
                    layout.setVisibility(View.VISIBLE);
                }


            }


        }

        }

    }
    ///////HERE WE RESET EVERYTHING BACK TO A FRESH START////////

    public void playAgain(View view){
        gameIsActive = true;
        ///HERE WE MAKE THE LINEAR LAYOUT DISAPPEAR///
        LinearLayout layout = findViewById(R.id.playAgainLayout);
        layout.setVisibility(View.INVISIBLE);
        /////HERE WE SET THE GAME STATE BACK TO 2 TO INDICATE THE EMPTY IMAGE VIEW SLOTS//////
         activePlayer = 0;
        for(int i=0;i<gameState.length;i++){
            gameState[i] = 2;
        }
        //////HERE THE SRC FOR THE IMAGES ARE ONCE AGAIN REMOVED/////
       GridLayout giveLayout = findViewById(R.id.gridLayout);
        for(int i=0;i<giveLayout.getChildCount();i++){
            ((ImageView)  giveLayout.getChildAt(i)).setImageResource(0);      }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       /* ImageView imageOne =findViewById(R.id.imageView4);
        imageOne.setTranslationY(-1000f);*/
    }
}
