package com.example.fitin_v1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fitin_v1.databinding.ActivityFindPwSecondBinding;

public class FindPwSecondActivity extends AppCompatActivity {

    private ActivityFindPwSecondBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFindPwSecondBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setTitle("비밀번호 찾기");

        Intent inIntent = getIntent();

        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backIntent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(backIntent);
            }
        });

        binding.btnChangePw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent changePwIntent = new Intent(getApplicationContext(), FindPwCompleteActivity.class);
                startActivity(changePwIntent);
            }
        });

    }
}