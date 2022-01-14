package com.example.fitin_v1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fitin_v1.databinding.ActivityFindPwCompleteBinding;

public class FindPwCompleteActivity extends AppCompatActivity {

    private ActivityFindPwCompleteBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFindPwCompleteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setTitle("비밀번호 변경 완료");

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
