package com.example.fitin_v1.remote.singleton;

import com.example.fitin_v1.remote.api.SignUp;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {

    // 기본 Retrofit 세팅 기준 URL을 가지고
    public static Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("http://localhost:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    // 회원가입에 매핑시킴, 다른 기능 추가할 경우 더 추가하면 됨
    public SignUp singUpAPI = getRetrofit().create(SignUp.class);
}
