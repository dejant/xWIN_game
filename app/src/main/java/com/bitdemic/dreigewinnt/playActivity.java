package com.bitdemic.dreigewinnt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class playActivity extends AppCompatActivity {

    // Variablen
    //
    int player = 0;

    // 0 = Player1, 1 = Player2, 2 = leer
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},
            {0,3,6},{1,4,7},{2,5,8},
            {0,4,8},{2,4,6}};

    String winner = "";
    boolean gameActive = true;

    // Objekte
    //
    Button playAgainButton;
    ImageView indicator;
    ImageView indicatorTwo;
    TextView textView;
    TextView textView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        playAgainButton = findViewById(R.id.playAgainButton);
        indicator = findViewById(R.id.indicatorPlayerOne);
        indicatorTwo = findViewById(R.id.indicatorPlayerTwo);
        textView = findViewById(R.id.winnerView1);
        textView1 = findViewById(R.id.winnerView2);

        indicator.setVisibility(View.VISIBLE);
    }

    // Methoden
    //
    public void dropIn(View view){
        ImageView counter = (ImageView) view;

        // Feld reservieren mittels Tag
        //
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        gameState[tappedCounter] = player;
        System.out.println(gameState[tappedCounter]);

        // Feld bereits besetzt?
        //
        if (counter.getDrawable()==null && gameActive==true){
            counter.setTranslationY(-1500);

            // Prüfen welcher Spieler, dann Feld besetzen
            //
            if (player==0){
                counter.setImageResource(R.drawable.xred);
                counter.animate().translationYBy(1500).rotation(3600).setDuration(300);
                player=1;
                indicator.setVisibility(View.INVISIBLE);
                indicatorTwo.setVisibility(View.VISIBLE);
            } else if (player==1){
                counter.setImageResource(R.drawable.xblack);
                counter.animate().translationYBy(1500).rotation(3600).setDuration(300);
                player=0;
                indicatorTwo.setVisibility(View.INVISIBLE);
                indicator.setVisibility(View.VISIBLE);
            }
        }

        // Prüfung Sieger
        //
        for (int[] winningPosition: winningPositions){
            if (gameState[winningPosition[0]]==gameState[winningPosition[1]] && gameState[winningPosition[0]]==gameState[winningPosition[2]] && gameState[winningPosition[0]] !=2){
                gameActive = false;

                if (player==0){
                    winner = "Player 2";
                } else if (player==1){
                    winner = "Player 1";
                }
                String WinnerMessage = winner + " wins!";
                textView.setText(WinnerMessage);
                textView1.setText(WinnerMessage);
                textView.setVisibility(View.VISIBLE);
                textView1.setVisibility(View.VISIBLE);
                playAgainButton.setVisibility(View.VISIBLE);
            } else {
                gameActive=false;

                for (int counterState : gameState){
                    if (counterState == 2){
                        gameActive=true;
                    }
                }
                if (!gameActive){
                    String WinnerMessage = "Unentschieden!";
                    textView.setText(WinnerMessage);
                    textView1.setText(WinnerMessage);
                    textView.setVisibility(View.VISIBLE);
                    textView1.setVisibility(View.VISIBLE);
                    playAgainButton.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    public void playAgain(View view){
        for (int i=0; i<gameState.length; i++){
            gameState[i]=2;
        }
        gameActive=true;
        player=1;
        indicator.setVisibility(View.VISIBLE);
        indicatorTwo.setVisibility(View.INVISIBLE);
        playAgainButton.setVisibility(View.INVISIBLE);
        textView.setVisibility(View.INVISIBLE);
        textView1.setVisibility(View.INVISIBLE);

        android.support.v7.widget.GridLayout gridLayout = findViewById(R.id.gridLayout);
        for (int i=0;i<gridLayout.getChildCount();i++){
            ImageView imageView = (ImageView) gridLayout.getChildAt(i);
            imageView.setImageDrawable(null);
        }
    }
}
