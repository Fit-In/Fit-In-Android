package com.example.fitin_v1.ui.findpw;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fitin_v1.ui.login.LoginActivity;
import com.example.fitin_v1.databinding.ActivityFindPwFisrtBinding;
import com.example.fitin_v1.ui.findid.FindIdActivity;

public class FindPwFirstActivity extends AppCompatActivity {

    private ActivityFindPwFisrtBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFindPwFisrtBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setTitle("비밀번호 찾기");

//        Intent inIntent = getIntent();

        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backIntent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(backIntent);
                finish();
            }
        });

        binding.btnFindId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent findIdIntent = new Intent(getApplicationContext(), FindIdActivity.class);
                startActivity(findIdIntent);
                overridePendingTransition(0,0);
                finish();
            }
        });

        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent findPwIntent = new Intent(getApplicationContext(),FindPwSecondActivity.class);
                startActivity(findPwIntent);
                finish();
            }
        });






    }
}