package com.example.fitin_v2.network;

import com.example.fitin_v2.model.AccountLoginDto;
import com.example.fitin_v2.model.AccountRequestDto;
import com.example.fitin_v2.model.AccountResponseDto;
import com.example.fitin_v2.model.TokenDto;
import com.example.fitin_v2.model.TokenRequestDto;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface UserAPI {

    @POST("/auth/signup")
    Single<AccountResponseDto> getSignUp(@Body AccountRequestDto accountRequestDto);

    @POST("/auth/login")
    Single<TokenDto> getSignIn(@Body AccountLoginDto accountLoginDto);

    @POST("/auth/reissue")
    Call<TokenDto> getReIssue(@Body TokenRequestDto tokenRequestDto);

    @GET("/member/me")
    Single<AccountResponseDto> getEmail(@Header("Authorization") String accessToken);

    @POST("/auth/logout")
    Single<TokenDto> getLogout(@Body TokenRequestDto tokenRequestDto);

}
