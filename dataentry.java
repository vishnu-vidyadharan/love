package com.example.lovecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static java.lang.String.valueOf;

public class dataentry extends AppCompatActivity {
    String str1,str2;
    int mydob,lovedob;

    EditText name1;
    EditText dob1;
    EditText name2;
    EditText dob2;
    Button button1;
    String calc;
    DatabaseReference reff;
    Member member;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dataentry);
        name1 = (EditText) findViewById(R.id.enter1);
        dob1 = (EditText) findViewById(R.id.enter2);
        name2 = (EditText) findViewById(R.id.enter3);
        dob2 = (EditText) findViewById(R.id.enter4);


        button1 = (Button) findViewById(R.id.button1);
        member = new com.example.lovecalculator.Member();
        reff = FirebaseDatabase.getInstance().getReference();
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                str1 = name1.getText().toString().toLowerCase();
                str2 = name2.getText().toString().toLowerCase();
                member.setNameone(str1);
                member.setNametwo(str2);
                reff.push().setValue(member);
                

                String calculation = calculate(str1, str2);
                calc = find(calculation);
                Toast.makeText(getApplicationContext(),"Calculating", Toast.LENGTH_SHORT).show();
                Intent homeIntent = new Intent(dataentry.this, display.class);
                homeIntent.putExtra("keyvalue",calc);
                startActivity(homeIntent);

            }
        });


    }

    String countChars(String str1, String str2) {
        String combinedString = str1 + "loves" + str2;

        String strAllChars = "";
        String strCount = "";
        for (char c1: combinedString.toCharArray()) {
            if (strAllChars.indexOf(c1) < 0) {
                int count = 0;
                for (char c2: combinedString.toCharArray()) {
                    if (c1 == c2) {
                        count = count + 1;
                    }
                strAllChars = strAllChars + c1;
                strCount = strCount + valueOf(count);
                }
            }

        }
        return strCount;

    }

    String shortenNumber(String str) {

        String shortenString = "";
        if (str.length() >= 2) {
            int int1 = Integer.parseInt(valueOf(str.toCharArray()[0]));
            int int2 = Integer.parseInt(valueOf(str.toCharArray()[str.length() -1]));
            shortenString = valueOf(int1 + int2) + shortenNumber(str.substring(1, str.length() - 1));
        }

        else {
            return str;
        }
            return shortenString;

    }

    String calculate(String str1, String str2) {
        String shortString = countChars(str1, str2);
        String output = shortString;
        do{
            output = output + "\n";
            shortString = shortenNumber(shortString);
            if (shortString.length() == 2){
                output = output + "\n";

            }
            output = output + shortString;

        } while (shortString.length() > 2);
        output = output + "%";

        return output;
    }

    String find(String calculation){
        int index = calculation.indexOf('%');
        int rindex = index - 2;
        String realcalculation;
        realcalculation = calculation.substring(rindex, rindex + 3);
        return realcalculation;

    }




}