package com.example.lovecalculator;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class display extends AppCompatActivity {

    private TextView display1,display2;
    String percentage1,des,descri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        display1 = (TextView) findViewById(R.id.disp);
        display2 = (TextView) findViewById(R.id.disp2);
        String percentage1 = getIntent().getStringExtra("keyvalue");
        display1.setText(percentage1);
        des=percentage1.substring(0,2);
        int r = Integer.parseInt(des);
        if (r > 85){
            descri = "You Guys Are Perfect For Each Other";
        }
        else if (r>70 && r<= 85){
            descri = "You guys make a good pair";

        }

        else if (r>50 && r<= 70){
            descri = "It can workout.But you should take initiative";

        }

        else if (r>30 && r<= 50){
            descri = "Hardly possible.Anything can happen.";

        }

        else if (r>0 && r<= 30){
            descri = "Never possible. Don't waste your time..";

        }
        display2.setText(descri);







    }


}