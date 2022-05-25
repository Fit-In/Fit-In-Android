package com.example.fitin_v2.util;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.fitin_v2.MyApp;
import com.example.fitin_v2.model.TokenDto;
import com.example.fitin_v2.model.TokenRequestDto;
import com.example.fitin_v2.network.RetrofitBuilder;
import com.example.fitin_v2.network.UserAPI;

import java.io.IOException;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthInterceptor implements Interceptor {

    @SuppressLint("LogNotTimber")
    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        UserAPI userAPI = RetrofitBuilder.getRetrofit().create(UserAPI.class);
        Preferences.init(MyApp.getContext());

        String at = Preferences.getAccessToken("none");
        String rt = Preferences.getRefreshToken("nein");

        Request original = chain.request().newBuilder().header("Authorization", "Bearer " + at).build();
        Response response = chain.proceed(original);

        if (response.code() == 401) {
            synchronized (this) {
                TokenRequestDto tokenRequestDto = new TokenRequestDto(at, rt);
                retrofit2.Response<TokenDto> token = userAPI.getReIssue(tokenRequestDto).execute();
                if (token.isSuccessful()) {
                    Preferences.setAccessToken(token.body().getAccessToken());
                    Preferences.setRefreshToken(token.body().getRefreshToken());
                    Log.e("Interceptor", "At:" + Preferences.getAccessToken("nein"));
                    return chain.proceed(original.newBuilder().header("Authorization", "Bearer " + token.body().getAccessToken()).build());
                }
                return chain.proceed(original.newBuilder().header("Authorization", "Bearer " + Preferences.getAccessToken("nein")).build());

            }
        }
        Log.e("Code What?", String.valueOf(response.code()));
        return response;
    }
}
