package com.example.fitin_v1.remote.api;

import com.example.fitin_v1.dto.AccountResponseDto;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface GetAccount {

    @GET("/member/me")
    Call<AccountResponseDto> getEmail(@Header("Authorization") String accessToken);
}
