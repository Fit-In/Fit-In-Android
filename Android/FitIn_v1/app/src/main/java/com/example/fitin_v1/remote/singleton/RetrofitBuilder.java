package com.example.fitin_v1.remote.singleton;


import androidx.annotation.NonNull;

import com.example.fitin_v1.Util.AuthInterceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {



    // 기본 Retrofit 세팅 기준 URL을 가지고
    public static Retrofit getRetrofit() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(new AuthInterceptor())
                .build();

        return new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }




}

