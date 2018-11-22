package com.example.rohit.tictactoe;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int[] boardValues={2,2,2,2,2,2,2,2,2};
    int count=0;
    int[][] winningValues={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean won=false;
    int player=0;

    public void clickedTile(View view){
        ImageView img=(ImageView) view;
        int imageClickedTag=Integer.parseInt(img.getTag().toString());
        Log.i("ImageClicked",String.valueOf(imageClickedTag));
        if(boardValues[imageClickedTag]==2 && !won) {
            count++;
            boardValues[imageClickedTag]=player;
            if(player==0){
//                boardValues[imageClickedTag]=0;
                player=1;
//                img.animate().alpha(0.9f).setStartDelay(2000);
                img.setImageResource(R.drawable.cross);
            }else {
//                boardValues[imageClickedTag]=0;
                player = 0;
//                img.animate().alpha(0.9f).setStartDelay(2000);
                img.setImageResource(R.drawable.circle);
            }
            for(int i=0;i<winningValues.length;i++){
                if( boardValues[winningValues[i][0]]==boardValues[winningValues[i][1]]
                        && boardValues[winningValues[i][1]]==boardValues[winningValues[i][2]]
                        && boardValues[winningValues[i][1]]!=2){
                    won=true;
                    TextView tv=((TextView)findViewById(R.id.textView));
                    if(player==0) {
                        Toast.makeText(this,"Player Yellow won",Toast.LENGTH_SHORT).show();
                        tv.setText("Player Yellow won!!!");
                        tv.setVisibility(View.VISIBLE);
                    }else{
                        Toast.makeText(this,"Player Red won",Toast.LENGTH_SHORT).show();
                        tv.setText("Player Red won!!!");
                        tv.setVisibility(View.VISIBLE);
                    }
                    break;
                }
            }
            Log.i("Count=",String.valueOf(count));
            if(!won && count==9){
                TextView tv=((TextView)findViewById(R.id.textView));
                tv.setText("Game Over!!!");
                tv.setTextColor(Color.RED);
                tv.setVisibility(View.VISIBLE);
            }
        }
    }

    public void playAgainFunction(View view){
        for(int i=0;i<boardValues.length;i++){
            boardValues[i]=2;
        }
        GridLayout gridLayout=findViewById(R.id.gridLayout);
        for(int i=0;i<gridLayout.getChildCount();i++){
//            Log.i("resetting now",((ImageView)gridLayout.getChildAt(i)).getTag().toString());
            ((ImageView)gridLayout.getChildAt(i)).setImageDrawable(null);
        }
        TextView tv=((TextView)findViewById(R.id.textView));
        tv.setVisibility(View.INVISIBLE);
        tv.setTextColor(Color.GREEN);
        tv.setText("");
        count=0;
        player=0;
        won=false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
