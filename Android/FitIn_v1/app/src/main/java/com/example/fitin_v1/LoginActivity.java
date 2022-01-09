package com.example.fitin_v1;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("로그인");

        Intent inIntent = getIntent();

        ImageButton buttonBack = (ImageButton)findViewById(R.id.btn_back);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        ImageButton buttonLogin = (ImageButton)findViewById(R.id.btn_login);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent outIntent = new Intent(getApplicationContext(),CompleteActivity.class);
                startActivity(outIntent);
            }
        });


        ImageButton buttonNaver = (ImageButton)findViewById(R.id.btn_naver_register);
        ImageButton buttonGoogle = (ImageButton)findViewById(R.id.btn_google_register);
        Intent intentWeb = new Intent(getApplicationContext(),WebviewActivity.class);


        buttonNaver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentWeb.putExtra("Uri",0);
                startActivity(intentWeb);
            }
        });
        buttonGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentWeb.putExtra("Uri",1);
                startActivity(intentWeb);
            }
        });

        TextView findText = (TextView)findViewById(R.id.tv_find_id);
        setPartBold(11,14,findText);

        TextView registerText = (TextView)findViewById(R.id.tv_register_title);
        setPartColor(12,registerText.getText().length(),registerText);
        registerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentRegister =  new Intent(getApplicationContext(), RegisterFirstActivity.class);
                startActivity(intentRegister);
            }
        });
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