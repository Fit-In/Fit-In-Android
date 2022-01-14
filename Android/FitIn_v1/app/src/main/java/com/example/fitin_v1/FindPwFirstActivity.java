package com.example.fitin_v1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fitin_v1.databinding.ActivityFindPwFisrtBinding;

public class FindPwFirstActivity extends AppCompatActivity {

    private ActivityFindPwFisrtBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFindPwFisrtBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setTitle("비밀번호 찾기");

        Intent inIntent = getIntent();

        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backIntent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(backIntent);
            }
        });

        binding.btnFindId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent findIdIntent = new Intent(getApplicationContext(), FindIdActivity.class);
                findIdIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(findIdIntent);
            }
        });

        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent findPwIntent = new Intent(getApplicationContext(),FindPwSecondActivity.class);
                startActivity(findPwIntent);
            }
        });






    }
}