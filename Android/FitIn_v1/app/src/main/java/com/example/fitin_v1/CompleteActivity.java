package com.example.fitin_v1;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CompleteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete);
        setTitle("회원가입 완료");
        Intent inIntent  = getIntent();

        TextView completeText = (TextView) findViewById(R.id.tv_completeText);
        setPartColor(0,5,completeText);
    }

    private TextView setPartColor(int start, int end, TextView textLogin) {
        String textData = textLogin.getText().toString();
        SpannableStringBuilder spannable = new SpannableStringBuilder(textData);
        spannable.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.morningGlory)),start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textLogin.setText(spannable);
        return textLogin;
    }
}
