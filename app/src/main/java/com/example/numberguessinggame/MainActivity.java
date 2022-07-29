package com.example.numberguessinggame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private RadioButton two,three,four;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button=findViewById(R.id.button);
        two=findViewById(R.id.twodigit);
        three=findViewById(R.id.threedigit);
        four=findViewById(R.id.fourdigit);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,GameActivity.class);

                //if no radio button is selected
                if(!two.isChecked() && !three.isChecked() && !four.isChecked()){
                    Snackbar.make(view,getString(R.string.op),Snackbar.LENGTH_INDEFINITE)
                            .setAction("Close", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                        }
                    }).show();

                }
                else{
                     if(two.isChecked()){
                        intent.putExtra("two",true);
                    }
                    else if(three.isChecked()){
                        intent.putExtra("three",true);
                    }
                    else if(four.isChecked()){
                        intent.putExtra("four",true);
                    }

                    startActivity(intent);
                }

            }
        });
    }
}