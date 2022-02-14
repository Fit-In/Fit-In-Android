package com.example.fitin_v1.remote.api;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface SignUp {
    // 해당 URL로 DTO로 넘김
    @FormUrlEncoded
    @POST("/api/accounts")
    Call<RequestAccount> getSingUp(@Body RequestAccount requestAccount);

}

