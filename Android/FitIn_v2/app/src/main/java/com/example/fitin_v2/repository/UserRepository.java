package com.example.fitin_v2.repository;

import android.annotation.SuppressLint;
import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.fitin_v2.model.AccountLoginDto;
import com.example.fitin_v2.model.AccountRequestDto;
import com.example.fitin_v2.model.AccountResponseDto;
import com.example.fitin_v2.model.TokenDto;
import com.example.fitin_v2.model.TokenRequestDto;
import com.example.fitin_v2.network.RetrofitBuilder;
import com.example.fitin_v2.network.UserAPI;
import com.example.fitin_v2.util.Preferences;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

@SuppressLint("LogNotTimber")
public class UserRepository {

    private final UserAPI userApi;


    public UserRepository(Application application) {
        userApi = RetrofitBuilder.getRetrofit().create(UserAPI.class);
        Preferences.init(application);
    }

    public LiveData<TokenDto> getToken(AccountLoginDto accountLoginDto) {
        final MutableLiveData<TokenDto> token = new MutableLiveData<>();
        userApi.getSignIn(accountLoginDto)
                .enqueue(new Callback<TokenDto>() {

                    @Override
                    public void onResponse(Call<TokenDto> call, Response<TokenDto> response) {
                        if(!response.isSuccessful()) {
                            Timber.e("error code: %s", response.code());
                        } else {
                            Log.e("완료","응답값: " + response.body().getAccessToken());
                            // SharedPreferences의 저장
                            Preferences.setAccessToken(response.body().getAccessToken());
                            Preferences.setRefreshToken(response.body().getRefreshToken());
                        }
                    }

                    @Override
                    public void onFailure(Call<TokenDto> call, Throwable t) {

                    }
                });
        return token;
    }

    public void getAccount(AccountRequestDto accountRequestDto) {
        userApi.getSignUp(accountRequestDto).enqueue(new Callback<AccountResponseDto>() {
            @Override
            public void onResponse(Call<AccountResponseDto> call, Response<AccountResponseDto> response) {
                if(!response.isSuccessful()) {
                    Log.e("실패", "error code: " + response.code());
                } else {
                    Log.e("완료","응답값: " + response.body().getEmail());
                }
            }

            @Override
            public void onFailure(Call<AccountResponseDto> call, Throwable t) {

            }
        });
    }

    public String getEmail() {
        final MutableLiveData<String> account = new MutableLiveData<>();
        userApi.getEmail("Bearer " + Preferences.getAccessToken("none"))
                .enqueue(new Callback<AccountResponseDto>() {
                    @Override
                    public void onResponse(Call<AccountResponseDto> call, Response<AccountResponseDto> response) {
                        if (!response.isSuccessful()) {
                            Log.e("연결이 비정상적 : ", "error code : " + response.code());
                        } else {
                            account.setValue(response.body().getEmail());
                            Log.e("At", "At:"+Preferences.getAccessToken("nein"));
                            Log.e("email", account.getValue());
                        }
                    }

                    @Override
                    public void onFailure(Call<AccountResponseDto> call, Throwable t) {

                    }
                });
        return String.valueOf(account.getValue());
    }

    public void getReissue() {
        TokenRequestDto token = new TokenRequestDto(Preferences.getAccessToken("none"), Preferences.getRefreshToken("nein"));
        userApi.getReIssue(token).enqueue(new Callback<TokenDto>() {
            @Override
            public void onResponse(Call<TokenDto> call, Response<TokenDto> response) {
                if(!response.isSuccessful()) {
                    Log.e("연결이 비정상적 : ", "error code : " + response.code());
                } else {
                    Preferences.setAccessToken(response.body().getAccessToken());
                    Preferences.setRefreshToken(response.body().getRefreshToken());
                    Log.e("AT:",Preferences.getAccessToken("nein"));
                    Log.e("Rt:",Preferences.getRefreshToken("none"));
                }
            }

            @Override
            public void onFailure(Call<TokenDto> call, Throwable t) {

            }
        });
    }

    public void getLogout() {
        TokenRequestDto token = new TokenRequestDto(Preferences.getAccessToken("none"), Preferences.getRefreshToken("nein"));
        userApi.getLogout(token).enqueue(new Callback<TokenDto>() {
            @Override
            public void onResponse(Call<TokenDto> call, Response<TokenDto> response) {
                if(!response.isSuccessful()) {
                    Log.e("연결이 비정상적 : ", "error code : " + response.code());
                } else {
                    Log.e("연결이 성공 : ", "code : " + response.code());
                }
            }

            @Override
            public void onFailure(Call<TokenDto> call, Throwable t) {

            }
        });
    }
}
