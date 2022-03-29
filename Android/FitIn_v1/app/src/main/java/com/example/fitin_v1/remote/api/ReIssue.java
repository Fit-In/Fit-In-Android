package com.example.fitin_v1.remote.api;

import com.example.fitin_v1.dto.TokenDto;
import com.example.fitin_v1.dto.TokenRequestDto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ReIssue {

    @POST("/auth/reissue")
    Call<TokenDto> getReIssue(@Body TokenRequestDto tokenRequestDto);
}
