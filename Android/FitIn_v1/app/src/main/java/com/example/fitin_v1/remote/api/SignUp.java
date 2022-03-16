package com.example.fitin_v1.remote.api;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SignUp {
    // 해당 URL로 DTO로 넘김
    @POST("/auth/signup")
    Call<AccountResponseDto> getSingUp(@Body AccountRequestDto accountRequestDto);

}

