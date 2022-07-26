package com.example.fitin_kotlin.data.remote.api

import com.example.fitin_kotlin.data.model.network.request.*
import com.example.fitin_kotlin.data.model.network.response.ResponseBodys
import com.example.fitin_kotlin.data.model.network.response.ResponseToken
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface UserService {

    @POST("/auth/signup")
    suspend fun postSignUp(
        @Body body: RequestSignUp
    ): Response<ResponseBodys>

    @GET("/auth/exists/{email}")
    suspend fun getEmailDuplicateCheck(
        @Path("email") email: String
    ): Response<Boolean>

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

    @POST("/sms/memberPhoneCheck")
    suspend fun postCoolSms(
        @Body body: RequestPhoneNumber
    ): Response<String>
}