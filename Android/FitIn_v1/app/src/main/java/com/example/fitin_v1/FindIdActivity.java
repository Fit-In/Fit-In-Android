package com.example.fitin_v1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fitin_v1.databinding.ActivityFindIdBinding;

public class FindIdActivity extends AppCompatActivity {

    private ActivityFindIdBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFindIdBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setTitle("아이디 찾기");

        Intent inIntent = getIntent();

        binding.btnFindPw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent FindPwIntent = new Intent(getApplicationContext(), FindPwFirstActivity.class);
                FindPwIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(FindPwIntent);
            }
        });

        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backIntent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(backIntent);
            }
        });

        binding.btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent findIdIntent = new Intent(getApplicationContext(), FindIdCompleteActivity.class);
                startActivity(findIdIntent);
            }
        });



    }
}