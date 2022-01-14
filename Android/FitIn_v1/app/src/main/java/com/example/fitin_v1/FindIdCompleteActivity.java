package com.example.fitin_v1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fitin_v1.databinding.ActivityFindIdCompleteBinding;

public class FindIdCompleteActivity extends AppCompatActivity {

    private ActivityFindIdCompleteBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFindIdCompleteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setTitle("아이디 찾기 완료");

        Intent inIntent = getIntent();

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(loginIntent);
            }
        });
    }
}
