package com.example.fitin_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.fitin_v1.Util.Utils;
import com.example.fitin_v1.databinding.ActivityHomeBinding;
import com.example.fitin_v1.dto.AccountResponseDto;
import com.example.fitin_v1.dto.TokenDto;
import com.example.fitin_v1.dto.TokenRequestDto;
import com.example.fitin_v1.remote.api.GetAccount;
import com.example.fitin_v1.remote.api.ReIssue;
import com.example.fitin_v1.remote.singleton.RetrofitBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ReIssue reIssue = RetrofitBuilder.getRetrofit().create(ReIssue.class);
        GetAccount getAccount = RetrofitBuilder.getRetrofit().create(GetAccount.class);

        Utils.init(getApplicationContext());

//        // 서버와 POST 통신에는 문제 없음, 단지 직접 불러와서 출력하는데 있어서 살짝의 차이가 발생하는 것 같음
//        // 어차피 SharedPreferences 작업은 백그라운드 작업이므로 문제 없음

        binding.btnReissue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // SharedPreferences로 불러오는 시점 중요함, 재발급하는 상황에서 불러와서 처리해줘야함
                String at = Utils.getAccessToken("none");
                String rt = Utils.getRefreshToken("nein");
                TokenRequestDto tokenRequest = new TokenRequestDto(at,rt);
                Call<TokenDto> call = reIssue.getReIssue(tokenRequest);
                call.enqueue(new Callback<TokenDto>() {
                    @Override
                    public void onResponse(Call<TokenDto> call, Response<TokenDto> response) {
                        if (!response.isSuccessful()) {
                            Log.e("연결이 비정상적 : ", "error code : " + response.code());
                        } else {
                            Utils.setAccessToken(response.body().getAccessToken());
                            Utils.setRefreshToken(response.body().getRefreshToken());
//                            Log.e("액세스 토큰", "at : " + Utils.getAccessToken("nein"));
//                            Log.e("리프레쉬 토큰", "rt : " + Utils.getRefreshToken("none"));
                        }
                    }

                    @Override
                    public void onFailure(Call<TokenDto> call, Throwable t) {

                    }
                });
            }
        });

        binding.btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.clearToken();
//                Log.e("AT","Is it clear?"+Utils.getAccessToken("non"));
            }
        });

        binding.btnGetinfo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Call<AccountResponseDto> call = getAccount.getEmail("Bearer "+Utils.getAccessToken("nein"));
//                Log.e("Call get:","At:"+Utils.getAccessToken("ein"));
                call.enqueue(new Callback<AccountResponseDto>() {
                    @Override
                    public void onResponse(Call<AccountResponseDto> call, Response<AccountResponseDto> response) {
                        if (!response.isSuccessful()) {
//                            Log.e("연결이 비정상적 : ", "error code : " + response.code());
//                            Log.e("Not renewal", "At:"+Utils.getAccessToken("nein"));
                        } else {
//                            Log.e("At", "At:"+Utils.getAccessToken("nein"));
                            binding.tvEmail.setText(response.body().getEmail());
                        }
                    }

                    @Override
                    public void onFailure(Call<AccountResponseDto> call, Throwable t) {

                    }
                });
            }
        });

        binding.btnClear.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                binding.tvEmail.setText(" ");
            }
        });
    }
}