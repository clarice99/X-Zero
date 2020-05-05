package com.example.xzero;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    boolean gameactive =true;
    // Player representaion
    // 0-X
    // 1-0
    int activeplayer = 0;
    int[] gamestate = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    // State meanings
    // 0-X
    // 1-0
    // 2-Null

    int[][] winpositions = {{0,1,2}, {3,4,5},{6,7,8},
                            {0,3,6}, {1,4,7}, {2,5,8},
                            {0,4,8}, {2,4,6}};

    public void playertap(View view){
        ImageView img = (ImageView) view;
        int tappedimage = Integer.parseInt(img.getTag().toString());

        Log.i("Tap check", "img Tapped: "+ tappedimage);

        if (!gameactive){
            gameReset(view);
            Log.i("reset  check", "Game has reset ");
        }
        if (gamestate[tappedimage]==2 ){
            gamestate[tappedimage]=activeplayer;
            img.setTranslationY(-1000f);
            if (activeplayer==0){
                img.setImageResource(R.drawable.xalpha);
                activeplayer=1;
                TextView status = findViewById(R.id.msg);
                Log.i("O check", "img print: "+ tappedimage);
                status.setText("O's turn tap to play");
            }
            else{
                img.setImageResource(R.drawable.o);
                activeplayer=0;
                TextView status = findViewById(R.id.msg);
                Log.i("X check", "img print: "+ tappedimage);
                status.setText("X's turn tap to play");
            }

            img.animate().translationYBy(1000f).setDuration(300);
        }
        //check who won
        for (int[] winposition: winpositions) {
            if (gamestate[winposition[0]] == gamestate[winposition[1]] && gamestate[winposition[1]] == gamestate[winposition[2]] && gamestate[winposition[0]]!= 2) {
                String winnerstr;
                gameactive= false;
                if (gamestate[winposition[0]] == 0) {
                    winnerstr = "X won!!";
                    TextView status = findViewById(R.id.msg);
                    status.setText(winnerstr);

                } else {
                    winnerstr = "O Won!!";
                    TextView status = findViewById(R.id.msg);
                    status.setText(winnerstr);
                }
                //update who won
                TextView status = findViewById(R.id.msg);
                status.setText(winnerstr);
            }
        }
    }

    public void gameReset(View view){
        gameactive = true;
        activeplayer = 0;
        for(int i=0; i<gamestate.length;i++){
            gamestate[i]=2;
            ((ImageView)findViewById(R.id.imageView11)).setImageResource(0);
            ((ImageView)findViewById(R.id.imageView10)).setImageResource(0);
            ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
            ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
            ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
            ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
            ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
            ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
            ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);

            TextView status = findViewById(R.id.msg);
            status.setText("X's turn tap to play");
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}