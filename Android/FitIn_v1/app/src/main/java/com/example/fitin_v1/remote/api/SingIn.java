package com.example.fitin_v1.remote.api;

import com.example.fitin_v1.dto.AccountLoginDto;
import com.example.fitin_v1.dto.TokenDto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SingIn {

    @POST("/auth/login")
    Call<TokenDto> getSingIn(@Body AccountLoginDto accountLoginDto);
}
