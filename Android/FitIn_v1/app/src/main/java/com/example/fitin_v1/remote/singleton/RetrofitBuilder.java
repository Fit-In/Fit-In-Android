package com.example.fitin_v1.remote.singleton;

import com.example.fitin_v1.remote.api.SignUp;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {

    // 기본 Retrofit 세팅 기준 URL을 가지고
    public static Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


}
