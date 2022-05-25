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

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

@SuppressLint("LogNotTimber")
public class UserRepository {

    private final UserAPI userApi;
    private final CompositeDisposable disposable = new CompositeDisposable();


    public UserRepository(Application application) {
        userApi = RetrofitBuilder.getRetrofit().create(UserAPI.class);
        Preferences.init(application);
    }

    public void getToken(AccountLoginDto accountLoginDto) {
        disposable.add(userApi.getSignIn(accountLoginDto)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
                tokenDto -> {
                    Log.e("완료", "응답값: " + tokenDto.getAccessToken());
                    Preferences.setAccessToken(tokenDto.getAccessToken());
                    Preferences.setRefreshToken(tokenDto.getRefreshToken());
                }, error -> {
                    Log.e("실패", "error:" + error.getMessage());
                }
        ));
    }

    public void getAccount(AccountRequestDto accountRequestDto) {
        disposable.add(userApi.getSignUp(accountRequestDto)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        accountResponseDto -> {
                            Log.e("완료", "응답값" + accountResponseDto.getEmail());
                        }, error -> {
                            Log.e("실패", "error: " + error.getMessage());
                        }
                ));
    }


    public String getEmail() {
        final MutableLiveData<String> account = new MutableLiveData<>();
        disposable.add(userApi.getEmail("Bearer " + Preferences.getAccessToken("none"))
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
           response -> {
               account.setValue(response.getEmail());
               Log.e("At", "At: " + Preferences.getAccessToken("NONE"));
               Log.e("email", account.getValue());
           }, error -> {
               Log.e("실패", "error: " + error.getMessage());
                }
        ));
//        userApi.getEmail("Bearer " + Preferences.getAccessToken("none"))
//                .enqueue(new Callback<AccountResponseDto>() {
//                    @Override
//                    public void onResponse(Call<AccountResponseDto> call, Response<AccountResponseDto> response) {
//                        if (!response.isSuccessful()) {
//                            Log.e("연결이 비정상적 : ", "error code : " + response.code());
//                        } else {
//                            account.setValue(response.body().getEmail());
//                            Log.e("At", "At:" + Preferences.getAccessToken("nein"));
//                            Log.e("email", account.getValue());
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<AccountResponseDto> call, Throwable t) {
//
//                    }
//                });
        return String.valueOf(account.getValue());
    }

    public void getReissue() {
        TokenRequestDto token = new TokenRequestDto(Preferences.getAccessToken("none"), Preferences.getRefreshToken("nein"));

        userApi.getReIssue(token).enqueue(new Callback<TokenDto>() {
            @Override
            public void onResponse(Call<TokenDto> call, Response<TokenDto> response) {
                if (!response.isSuccessful()) {
                    Log.e("연결이 비정상적 : ", "error code : " + response.code());
                } else {
                    Preferences.setAccessToken(response.body().getAccessToken());
                    Preferences.setRefreshToken(response.body().getRefreshToken());
                    Log.e("AT:", Preferences.getAccessToken("nein"));
                    Log.e("Rt:", Preferences.getRefreshToken("none"));
                }
            }

            @Override
            public void onFailure(Call<TokenDto> call, Throwable t) {

            }
        });
    }

    public void getLogouts() {
        TokenRequestDto token = new TokenRequestDto(Preferences.getAccessToken("none"), Preferences.getRefreshToken("nein"));
        disposable.add(userApi.getLogout(token)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
                response -> {
                    Log.e("성공", "성공: " + response);
                }, error -> {
                    Log.e("실패", "실패: " + error.getMessage());
                }
        ));
//        userApi.getLogout(token).enqueue(new Callback<TokenDto>() {
//            @Override
//            public void onResponse(Call<TokenDto> call, Response<TokenDto> response) {
//                if (!response.isSuccessful()) {
//                    Log.e("연결이 비정상적 : ", "error code : " + response.code());
//                } else {
//                    Log.e("연결이 성공 : ", "code : " + response.code());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<TokenDto> call, Throwable t) {
//
//            }
//        });
    }
}
