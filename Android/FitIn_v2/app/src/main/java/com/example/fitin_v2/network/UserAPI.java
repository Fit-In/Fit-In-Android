package com.example.fitin_v2.network;

import com.example.fitin_v2.model.AccountLoginDto;
import com.example.fitin_v2.model.AccountRequestDto;
import com.example.fitin_v2.model.AccountResponseDto;
import com.example.fitin_v2.model.TokenDto;
import com.example.fitin_v2.model.TokenRequestDto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface UserAPI {

    @POST("/auth/signup")
    Call<AccountResponseDto> getSignUp(@Body AccountRequestDto accountRequestDto);

    @POST("/auth/login")
    Call<TokenDto> getSignIn(@Body AccountLoginDto accountLoginDto);

    @POST("/auth/reissue")
    Call<TokenDto> getReIssue(@Body TokenRequestDto tokenRequestDto);

    @GET("/member/me")
    Call<AccountResponseDto> getEmail(@Header("Authorization") String accessToken);


}
