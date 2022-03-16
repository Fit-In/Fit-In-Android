package com.example.fitin_v1.ui.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fitin_v1.remote.api.AccountLoginDto;
import com.example.fitin_v1.remote.api.AccountRequestDto;
import com.example.fitin_v1.remote.api.SingIn;
import com.example.fitin_v1.remote.api.TokenDto;
import com.example.fitin_v1.remote.singleton.RetrofitBuilder;
import com.example.fitin_v1.ui.complete.CompleteActivity;
import com.example.fitin_v1.ui.main.MainActivity;
import com.example.fitin_v1.WebviewActivity;
import com.example.fitin_v1.databinding.ActivityLoginBinding;
import com.example.fitin_v1.ui.findid.FindIdActivity;
import com.example.fitin_v1.ui.register.RegisterFirstActivity;

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
                            return;
                        } else {
                            Log.e("액세스 토큰 ", response.body().getAccessToken());
                            Log.e("리프레시 토큰 ", response.body().getRefreshToken());
                            SharedPreferences prefs = getSharedPreferences("pref_name", Context.MODE_PRIVATE);
                            prefs.edit().putString("Access Token",response.body().getAccessToken());
                            prefs.edit().putString("Refresh Token", response.body().getRefreshToken());
                            prefs.edit().commit();
                            Toast.makeText(getApplicationContext(),"AT: " + prefs.getString("Access Token",""),Toast.LENGTH_LONG).show();
                            Toast.makeText(getApplicationContext(),"RT: " + prefs.getString("Refresh Token", ""),Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<TokenDto> call, Throwable t) {

                    }
                });
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