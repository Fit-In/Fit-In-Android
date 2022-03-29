package com.example.fitin_v1.ui.register;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fitin_v1.dto.AccountRequestDto;
import com.example.fitin_v1.dto.AccountResponseDto;
import com.example.fitin_v1.remote.api.SignUp;
import com.example.fitin_v1.remote.singleton.RetrofitBuilder;
import com.example.fitin_v1.ui.login.LoginActivity;
import com.example.fitin_v1.databinding.ActivityRegisterFirstBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterFirstActivity extends AppCompatActivity {

    private ActivityRegisterFirstBinding binding;

    private String et1;
    private String et2;
    private String email;
    private String name;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterFirstBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setTitle("회원가입");

        SignUp signUp = RetrofitBuilder.getRetrofit().create(SignUp.class);


//        Intent inIntent = getIntent();
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backIntent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(backIntent);
                finish();
            }
        });
        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent nextIntent = new Intent(getApplicationContext(), RegisterSecondActivity.class);
//                startActivity(nextIntent);

                et1 = binding.etEmailId.getText().toString();
                et2 = binding.etEmail.getText().toString();
                email = et1 + "@" + et2;
                name = binding.etName.getText().toString();
                password = binding.etPassword.getText().toString();
                AccountRequestDto account = new AccountRequestDto(email, password, name);
                Call<AccountResponseDto> call = signUp.getSingUp(account);
                call.enqueue(new Callback<AccountResponseDto>() {

                    @Override
                    public void onResponse(Call<AccountResponseDto> call, Response<AccountResponseDto> response) {
                        if (!response.isSuccessful()) {
                            Log.e("연결이 비정상적 : ", "error code : " + response.code());
                            return;
                        } else {
                            Log.e("응답값 : ", response.body().getEmail());
                        }
                    }

                    @Override
                    public void onFailure(Call<AccountResponseDto> call, Throwable t) {

                    }
                });
            }
        });

//        binding.tvFindId.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent findIdIntent = new Intent(getApplicationContext(), FindIdActivity.class);
//                startActivity(findIdIntent);
//            }
//        });
//
//
//        setPartColor(11,14,binding.tvFindId);
    }

//    private TextView setPartColor(int start, int end, TextView textLogin) {
//        String textData = textLogin.getText().toString();
//        SpannableStringBuilder spannable = new SpannableStringBuilder(textData);
//        spannable.setSpan(new StyleSpan(Typeface.BOLD),start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        spannable.setSpan(new StyleSpan(Typeface.BOLD),17,textLogin.getText().length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        textLogin.setText(spannable);
//        return textLogin;
//    }
}

/**
 * Retrofit 통신 코드
 * // 안드로이드 스프링에서 Post 맵핑된 URL에다가 그대로 POST로 보냄
 * // POST로 받은 데이터 처리 스프링에서 처리해서 회원가입 된 것을 나타내게끔 처리를 해 줌, 회원가입을 위해서 넘기는 것이므로
 * // 현재는 Response가 굳이 필요없음
 * et1 = binding.etEmailId.getText().toString();
 * et2 = binding.etEmail.getText().toString();
 * email = et1 + "@" + et2;
 * name = binding.etName.getText().toString();
 * password = binding.etPassword.getText().toString();
 * RequestAccount account = new RequestAccount(email,password,name);
 * Call<RequestAccount> call = signUp.getSingUp(account);
 * call.enqueue(new Callback<RequestAccount>() {
 *
 * @Override public void onResponse(Call<RequestAccount> call, Response<RequestAccount> response) {
 * if(!response.isSuccessful()) {
 * Log.e("연결이 비정상적 : ", "error code : " + response.code());
 * return;
 * }
 * }
 * @Override public void onFailure(Call<RequestAccount> call, Throwable t) {
 * Log.e("연결실패",t.getMessage());
 * }
 * });
 */