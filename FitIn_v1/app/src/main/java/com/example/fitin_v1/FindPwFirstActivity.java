package com.example.fitin_v1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class FindPwFirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_pw_fisrt);
        setTitle("비밀번호 찾기");


        Intent inIntent = getIntent();

        ImageButton buttonBack = (ImageButton)findViewById(R.id.btn_back);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backIntent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(backIntent);
            }
        });



        ImageButton buttonFindId = (ImageButton) findViewById(R.id.btn_find_id);
        buttonFindId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent findIdIntent = new Intent(getApplicationContext(), FindIdActivity.class);
                findIdIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(findIdIntent);
            }
        });

        ImageButton buttonNext = (ImageButton) findViewById(R.id.btn_next);
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent findPwIntent = new Intent(getApplicationContext(),FindPwSecondActivity.class);
                startActivity(findPwIntent);
            }
        });






    }
}