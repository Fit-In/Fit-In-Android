package com.example.fitin_v1.ui.login;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fitin_v1.ui.complete.CompleteActivity;
import com.example.fitin_v1.ui.main.MainActivity;
import com.example.fitin_v1.WebviewActivity;
import com.example.fitin_v1.databinding.ActivityLoginBinding;
import com.example.fitin_v1.ui.findid.FindIdActivity;
import com.example.fitin_v1.ui.register.RegisterFirstActivity;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setTitle("로그인");

//        Intent inIntent = getIntent();

        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(backIntent);
                finish();
            }
        });

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 바로 메인화면으로 넘어가게끔 처리
//                Intent loginIntent = new Intent(getApplicationContext(), CompleteActivity.class);
//                startActivity(loginIntent);
            }
        });

        Intent intentWeb = new Intent(getApplicationContext(), WebviewActivity.class);
        binding.btnNaverRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentWeb.putExtra("Uri",0);
                startActivity(intentWeb);
            }
        });
        binding.btnGoogleRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentWeb.putExtra("Uri",1);
                startActivity(intentWeb);
            }
        });

        binding.tvFindId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent findIdIntent = new Intent(getApplicationContext(), FindIdActivity.class);
                startActivity(findIdIntent);
                finish();
            }
        });




        //디자인

        setPartBold(11,14,binding.tvFindId);

//        setPartColor(12,binding.tvRegisterTitle.getText().length(),binding.tvRegisterTitle);
//        binding.tvRegisterTitle.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intentRegister =  new Intent(getApplicationContext(), RegisterFirstActivity.class);
//                startActivity(intentRegister);
//            }
//        });


    }






    private TextView setPartColor(int start, int end, TextView textLogin) {
        String textData = textLogin.getText().toString();
        SpannableStringBuilder spannable = new SpannableStringBuilder(textData);
        spannable.setSpan(new StyleSpan(Typeface.BOLD),start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textLogin.setText(spannable);
        return textLogin;
    }

    private TextView setPartBold(int start, int end, TextView textLogin) {
        String textData = textLogin.getText().toString();
        SpannableStringBuilder spannable = new SpannableStringBuilder(textData);
        spannable.setSpan(new StyleSpan(Typeface.BOLD),start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannable.setSpan(new StyleSpan(Typeface.BOLD),17,textLogin.getText().length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textLogin.setText(spannable);
        return textLogin;
    }
}