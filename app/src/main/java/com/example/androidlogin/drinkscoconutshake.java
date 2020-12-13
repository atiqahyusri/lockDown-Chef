package com.example.androidlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class drinkscoconutshake extends AppCompatActivity {
    private EditText servingnum;
    private TextView bahan1;
    private TextView bahan2;
    private TextView bahan3;
    private TextView bahan4;
    Button addvid, viewvid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drinkscoconutshake);



        //declare, map tp activity main xml file
        servingnum = (EditText) findViewById(R.id.servingnumber);
        bahan1 = (TextView) findViewById(R.id.servingnumber1);
        bahan2 = (TextView) findViewById(R.id.servingnumber2);
        bahan3 = (TextView) findViewById(R.id.servingnumber3);
        bahan4 = (TextView) findViewById(R.id.servingnumber4);
        addvid = (Button) findViewById(R.id.addvideos);
        viewvid = (Button) findViewById(R.id.viewvideos);


        //bila user tekan add video
        addvid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), addingvid.class));
            }
        });


        //bila user tekan view video
        viewvid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), viewingvid.class));
            }
        });



        servingnum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                float serve = Float.parseFloat(servingnum.getText().toString());
                bahan1.setText(String.valueOf(serve*2));
                bahan2.setText(String.valueOf(serve*15));
                bahan3.setText(String.valueOf(serve*1));
                bahan4.setText(String.valueOf(serve*1));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
}
