package com.example.numberguessinggame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.*;

public class GameActivity extends AppCompatActivity {

    private TextView one,right,hint;
    private EditText guess;
    private Button confirm;

    Boolean twodigit,threedigit,fourdigit;
    Random random=new Random();
    int ran,remainingRight=10;

    ArrayList<Integer> guessList=new ArrayList<>();
    int userAttempt=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        one=findViewById(R.id.textView2);
        right=findViewById(R.id.textView4);
        hint=findViewById(R.id.textView5);
        guess=findViewById(R.id.editTextNumber);
        confirm=findViewById(R.id.button2);

        twodigit=getIntent().getBooleanExtra("two",false);
        threedigit=getIntent().getBooleanExtra("three",false);
        fourdigit=getIntent().getBooleanExtra("four",false);

        if(twodigit){
            ran=random.nextInt(90)+10;
        }
        else if(threedigit){
            ran=random.nextInt(900)+100;

        }
        else{
            ran=random.nextInt(9000)+1000;
        }

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String temp=guess.getText().toString();

                if(temp.isEmpty()){
                    Toast.makeText(GameActivity.this, "Please enter a guess.", Toast.LENGTH_SHORT).show();
                }
                else{
                    one.setVisibility(View.VISIBLE);
                    right.setVisibility(View.VISIBLE);
                    hint.setVisibility(View.VISIBLE);

                    remainingRight--;
                    userAttempt++;

                    int userGuess=Integer.parseInt(temp);
                    guessList.add(userGuess);

                    one.setText("Your last guess : "+temp);
                    right.setText("Your remaining Right : "+remainingRight);

                    if(ran==userGuess){
                        AlertDialog.Builder alert=new AlertDialog.Builder(getApplicationContext());
                        alert.setCancelable(false);
                        alert.setTitle("Number Guessing Game");
                        alert.setMessage("Congratulations, guess is right.\n\n"+
                                "You guess it in" +userAttempt+"attempts.\n\n"+
                                "The following guesses made by you are : "+guessList+".\n"+
                                "Do you want to play again?");
                        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                moveTaskToBack(true);
                                android.os.Process.killProcess(android.os.Process.myPid());
                                System.exit(1);

                            }
                        });
                        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent=new Intent(GameActivity.this,MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });
                        alert.create().show();
                    }
                    else if(ran>userGuess){
                        hint.setText("Increase your guess");
                    }
                    else if(ran<userGuess){
                        hint.setText("Decrease your guess");
                    }

                    if(remainingRight  <=0){
                        AlertDialog.Builder alert=new AlertDialog.Builder(GameActivity.this);
                        alert.setCancelable(false);
                        alert.setTitle("Number Guessing Game");
                        alert.setMessage("Sorry, your guess limit is over.\n\n"+
                                "Random Integer was "+ran+".\n\n"+
                                "The following guesses made by you are : "+guessList+".\n"+
                                "Do you want to play again?");
                        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                moveTaskToBack(true);
                                android.os.Process.killProcess(android.os.Process.myPid());
                                System.exit(1);

                            }
                        });
                        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });
                        alert.create().show();
                    }

                    guess.setText("");
                }
            }
        });

    }
}