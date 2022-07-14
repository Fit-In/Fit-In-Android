package com.example.fitin_kotlin.data.remote.api

import com.example.fitin_kotlin.data.model.network.request.*
import com.example.fitin_kotlin.data.model.network.response.ResponseSignUp
import com.example.fitin_kotlin.data.model.network.response.ResponseToken
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface UserService {

    @POST("/auth/signup")
    suspend fun postSignUp(
        @Body body: RequestSignUp
    ): Response<ResponseSignUp>

    @POST("/auth/login")
    suspend fun postSignIn(
        @Body body: RequestSignIn
    ): Response<ResponseToken>

    @POST("/auth/reissue")
    fun postReIssue(
        @Body body: RequestTokenReissue
    ): Call<ResponseToken>

    @POST("/auth/logout")
    suspend fun postSignOut(
        @Body body: RequestSignOut
    ): Response<Void>

    @GET("/member/me")
    suspend fun getEmail(
        @Header("Authorization") accessToken: String
    ): Response<ResponseSignUp>

    @POST("/sms/memberPhoneCheck")
    suspend fun postCoolSms(
        @Body body: RequestPhoneNumber
    ): Response<String>
}