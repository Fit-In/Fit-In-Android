package com.example.fitin_v1.ui.login;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fitin_v1.HomeActivity;
import com.example.fitin_v1.Util.Utils;
import com.example.fitin_v1.dto.AccountLoginDto;
import com.example.fitin_v1.remote.api.SingIn;
import com.example.fitin_v1.dto.TokenDto;
import com.example.fitin_v1.remote.singleton.RetrofitBuilder;
import com.example.fitin_v1.ui.main.MainActivity;
import com.example.fitin_v1.WebviewActivity;
import com.example.fitin_v1.databinding.ActivityLoginBinding;
import com.example.fitin_v1.ui.findid.FindIdActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

    private String email;
    private String password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setTitle("로그인");

        Utils.init(getApplicationContext());

        SingIn signIn = RetrofitBuilder.getRetrofit().create(SingIn.class);

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
                email = binding.etEmail.getText().toString();
                password = binding.etPassword.getText().toString();
                AccountLoginDto account = new AccountLoginDto(email, password);
                Call<TokenDto> call = signIn.getSingIn(account);
                call.enqueue(new Callback<TokenDto>() {
                    @Override
                    public void onResponse(Call<TokenDto> call, Response<TokenDto> response) {
                        if (!response.isSuccessful()) {
                            Log.e("연결이 비정상적 : ", "error code : " + response.code());
                        } else {
                            Utils.setAccessToken(response.body().getAccessToken());
                            Utils.setRefreshToken(response.body().getRefreshToken());
//                            Log.e("Login", "at : " + Utils.getAccessToken("nein"));
//                            Log.e("Login", "rt : " + Utils.getRefreshToken("none"));
//                            // 인텐트 처리해서 Home으로 넘겨서 보내주면 될 듯(현재 테스트에선)
                            // 만약 그게 아니면 굳이 인텐트 처리 안해줘도 됨, 어차피 저장되니깐
                        }
                    }

                    @Override
                    public void onFailure(Call<TokenDto> call, Throwable t) {

                    }
                });

                Intent intentHome = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intentHome);
                finish();
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