package com.example.xzero;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    boolean gameactive =true;
    // Player representaion
    // 0-Shinchan
    // 1-Pingu
    int activeplayer = 0;
    int[] gamestate = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    // State meanings
    // 0-shinchan
    // 1-pingu
    // 2-Null

    int[][] winpositions = {{0,1,2}, {3,4,5},{6,7,8},
                            {0,3,6}, {1,4,7}, {2,5,8},
                            {0,4,8}, {2,4,6}};

    public void playertap(View view) throws InterruptedException {
        ImageView img = (ImageView) view;
        int tappedimg = Integer.parseInt(img.getTag().toString());

        Log.i("Tap check", "img Tapped: "+ tappedimg);

        if (!gameactive){
            gameReset(view);
            Log.i("reset  check", "Game has reset ");
        }
        if (gamestate[tappedimg]==2 ){
            gamestate[tappedimg]=activeplayer;
            img.setTranslationY(-1000f);
            if (activeplayer==0){
                img.setImageResource(R.drawable.shinchan);
                activeplayer=1;
                TextView status = findViewById(R.id.msg);
                Log.i("Pingu check", "img print: "+ tappedimg);
                status.setText("Pingu's turn tap to play");
            }
            else{
                img.setImageResource(R.drawable.pingu);
                activeplayer=0;
                TextView status = findViewById(R.id.msg);
                Log.i("Shinchan check", "img print: "+ tappedimg);
                status.setText("Shinchan's turn tap to play");
            }

            img.animate().translationYBy(1000f).setDuration(300);
        }
        //check who won
        for (int[] winposition: winpositions) {
            if (gamestate[winposition[0]] == gamestate[winposition[1]] && gamestate[winposition[1]] == gamestate[winposition[2]] && gamestate[winposition[0]]!= 2) {
                String winnerstr;
                gameactive= false;
                if (gamestate[winposition[0]] == 0) {
                    winnerstr = "Shinchan won!!";
                    TextView status = findViewById(R.id.msg);
                    //img.setPaintFlags(status.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
                    status.setText(winnerstr);
                } else {
                    winnerstr = "Pingu Won!!";
                    TextView status = findViewById(R.id.msg);
                    //img.setPaintFlags(status.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
                    status.setText(winnerstr);
                }
//                boolean chk = true;
//                for (int i = 0; i<9;i++){
//                    if (gamestate[i] == 2){
//                        chk = false;
//                    }
//                }
//                if (chk==true){
//                    Toast.makeText(MainActivity.this,"Its Draw!!! Play again",Toast.LENGTH_SHORT).show();
//                    gameactive=false;
//                      }
                //update who won
                TextView status = findViewById(R.id.msg);
                //status.setPaintFlags(status.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
                status.setText(winnerstr);
            }
            boolean chk = true;
            for (int i = 0; i<9;i++){
                if (gamestate[i] == 2){
                    chk = false;
                }
            }
            if (chk==true){

                Toast.makeText(MainActivity.this,"Its Draw!!! Play again",Toast.LENGTH_SHORT).show();
                gameactive=false;
            }

        }
    }

    public void gameReset(View view){
        gameactive = true;
        activeplayer = 0;
        for(int i=0; i<gamestate.length;i++){
            gamestate[i]=2;
            ((ImageView)findViewById(R.id.imageView10)).setImageResource(0);
            ((ImageView)findViewById(R.id.imageView9)).setImageResource(0);
            ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
            ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
            ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
            ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
            ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
            ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
            ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);

            TextView status = findViewById(R.id.msg);
            status.setText("Shinchan's turn tap to play");
        }
    }
    //hdchchgckhcjhchg


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
