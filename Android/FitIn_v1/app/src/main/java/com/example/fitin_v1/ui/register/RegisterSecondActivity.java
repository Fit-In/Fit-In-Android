package com.example.fitin_v1.ui.register;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fitin_v1.ui.complete.CompleteActivity;
import com.example.fitin_v1.databinding.ActivityRegisterSecondBinding;

public class RegisterSecondActivity extends AppCompatActivity {

    private ActivityRegisterSecondBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterSecondBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setTitle("회원가입");
//        Intent inIntent = getIntent();

        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(getApplicationContext(), CompleteActivity.class);
                startActivity(registerIntent);
            }
        });


    }
}

