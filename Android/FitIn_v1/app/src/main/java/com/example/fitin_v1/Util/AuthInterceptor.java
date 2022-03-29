package com.example.fitin_v1.Util;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.fitin_v1.MyApp;
import com.example.fitin_v1.dto.TokenDto;
import com.example.fitin_v1.dto.TokenRequestDto;
import com.example.fitin_v1.remote.api.ReIssue;
import com.example.fitin_v1.remote.singleton.RetrofitBuilder;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthInterceptor implements Interceptor {


    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        ReIssue reIssue = RetrofitBuilder.getRetrofit().create(ReIssue.class);
        Utils.init(MyApp.getContext());
        String at = Utils.getAccessToken("none");
        String rt = Utils.getRefreshToken("nein");

        Request original = chain.request().newBuilder().header("Authorization", "Bearer " + at).build();
        Response response = chain.proceed(original);

        if (response.code() == 401) {

                TokenRequestDto tokenRequestDto = new TokenRequestDto(at, rt);
                retrofit2.Response<TokenDto> token = reIssue.getReIssue(tokenRequestDto).execute();
                if (token.isSuccessful()) {
                    Utils.setAccessToken(token.body().getAccessToken());
                    Utils.setRefreshToken(token.body().getRefreshToken());
//                    Log.e("Interceptor", "At:" + Utils.getAccessToken("nein"));
                    return chain.proceed(original.newBuilder().header("Authorization", "Bearer " + token.body().getAccessToken()).build());
                }


                return chain.proceed(original.newBuilder().header("Authorization", "Bearer " + Utils.getAccessToken("nein")).build());




        }
//        Log.e("Code What?", String.valueOf(response.code()));
        return response;
    }
}
