package com.example.fitin_v1.ui.findid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fitin_v1.ui.findpw.FindPwFirstActivity;
import com.example.fitin_v1.ui.login.LoginActivity;
import com.example.fitin_v1.databinding.ActivityFindIdBinding;

public class FindIdActivity extends AppCompatActivity {

    private ActivityFindIdBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFindIdBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setTitle("아이디 찾기");

//        Intent inIntent = getIntent();

        binding.btnFindPw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent FindPwIntent = new Intent(getApplicationContext(), FindPwFirstActivity.class);
                startActivity(FindPwIntent);
                overridePendingTransition(0,0);
                finish();
            }
        });

        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backIntent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(backIntent);
                finish();
            }
        });

        binding.btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent findIdIntent = new Intent(getApplicationContext(), FindIdCompleteActivity.class);
                startActivity(findIdIntent);
                finish();
            }
        });



    }
}