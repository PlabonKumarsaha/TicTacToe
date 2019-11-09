package com.example.tictoctoeandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button[][] buttons = new Button[3][3];
    private Boolean player1Turn = true;
    private int roundCount;
    private int Player1Point;
    private int Player2Point;
    private TextView textViewPlayer1;
    private TextView textViewPlayer2;
    Button resetBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textViewPlayer1 = findViewById(R.id.TextViewP1);
        textViewPlayer2 = findViewById(R.id.TextViewP2);
        resetBtn = findViewById(R.id.resetBtn);

        for(int i = 0 ;i <3;i++)
        {
            for(int j = 0 ;j<3;j++)
            {
                String buttonID = "button_" + i + j;
                //this is how to get resource id..kinda equivalent to R.id.idName
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }
        }

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!((Button) view).getText().toString().equals("")))
                {
                    return;}
                if (player1Turn) {
                    ((Button) view).setText("X");
                } else {
                    ((Button) view).setText("O");
                }


                roundCount++;

                if (checkForWin()) {
                    if (player1Turn) {
                        player1Wins();
                    } else {
                        player2Wins();
                    }
                } else if (roundCount == 9) {
                    //totak colum are 9!
                    draw();
                } else {
                    //if no one wins then swipe turns!
                    player1Turn = !player1Turn;
                }


            }

            private void  draw()
            {
                
            }
            private void player2Wins() {
            }

            private void player1Wins() {
            }
        });
    }

    private boolean checkForWin() {
        String[][] field = new String[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = buttons[i][j].getText().toString();
            }
        }

        //checks for rows!checks with the first element and comapre with other two..ofcourse first one can't be empty string!
        for (int i = 0; i < 3; i++) {
            if (field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2])
                    && !field[i][0].equals("")) {
                return true;
            }
        }

        //check for colums if same!
        for (int i = 0; i < 3; i++) {
            if (field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].equals("")) {
                return true;
            }
        }

        //check for diagonals \ left top to bottom right
        if (field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && !field[0][0].equals("")) {
            return true;
        }

        //check for diagonal / right top to bottom left!
        if (field[0][2].equals(field[1][1])
                && field[0][2].equals(field[2][0])
                && !field[0][2].equals("")) {
            return true;
        }


        return false;
    }

    @Override
    public void onClick(View view) {

    }
}
