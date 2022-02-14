package com.example.fitin_v1.ui.register;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fitin_v1.remote.api.RequestAccount;
import com.example.fitin_v1.remote.singleton.RetrofitBuilder;
import com.example.fitin_v1.ui.login.LoginActivity;
import com.example.fitin_v1.databinding.ActivityRegisterFirstBinding;
import com.example.fitin_v1.ui.findid.FindIdActivity;

public class RegisterFirstActivity extends AppCompatActivity {

    private ActivityRegisterFirstBinding binding;

    private RetrofitBuilder requestData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterFirstBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setTitle("회원가입");




        Intent inIntent = getIntent();
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backIntent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(backIntent);
            }
        });
        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 안드로이드 스프링에서 Post 맵핑된 URL에다가 그대로 POST로 보냄
                // POST로 받은 데이터 처리 스프링에서 처리해서 회원가입 된 것을 나타내게끔 처리를 해 줌, 회원가입을 위해서 넘기는 것이므로
                // 현재는 Response가 굳이 필요없음
                RequestAccount account = new RequestAccount(binding.etEmail.getText().toString(),binding.etPassword.getText().toString(),binding.etName.getText().toString());
                requestData.singUpAPI.getSingUp(account);
                Intent nextIntent = new Intent(getApplicationContext(), RegisterSecondActivity.class);
                startActivity(nextIntent);
            }
        });

        binding.tvFindId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent findIdIntent = new Intent(getApplicationContext(), FindIdActivity.class);
                startActivity(findIdIntent);
            }
        });


        setPartColor(11,14,binding.tvFindId);
    }

    private TextView setPartColor(int start, int end, TextView textLogin) {
        String textData = textLogin.getText().toString();
        SpannableStringBuilder spannable = new SpannableStringBuilder(textData);
        spannable.setSpan(new StyleSpan(Typeface.BOLD),start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannable.setSpan(new StyleSpan(Typeface.BOLD),17,textLogin.getText().length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textLogin.setText(spannable);
        return textLogin;
    }
}