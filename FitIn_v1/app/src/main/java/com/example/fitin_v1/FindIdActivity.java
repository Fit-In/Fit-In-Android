package com.example.fitin_v1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class FindIdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_id);
        setTitle("아이디 찾기");

        Intent inIntent = getIntent();

        ImageButton buttonFindPw = (ImageButton) findViewById(R.id.btn_find_pw);
        buttonFindPw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent FindPwIntent = new Intent(getApplicationContext(), FindPwFirstActivity.class);
                FindPwIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(FindPwIntent);
            }
        });

        ImageButton buttonBack = (ImageButton) findViewById(R.id.btn_back);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backIntent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(backIntent);
            }
        });
        
        ImageButton buttonConfirm = (ImageButton) findViewById(R.id.btn_confirm);


        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent findIdIntent = new Intent(getApplicationContext(), FindIdCompleteActivity.class);
                startActivity(findIdIntent);
            }
        });



    }
}