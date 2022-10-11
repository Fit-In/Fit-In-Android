package com.example.fitin_kotlin.data.remote.api

import com.example.fitin_kotlin.data.model.network.request.RequestNewsBookmark
import com.example.fitin_kotlin.data.model.network.request.RequestRecruitmentBookmark
import com.example.fitin_kotlin.data.model.network.response.ResponseBodys
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface SaveBookmarkService {

    // Response에서의 data 값이 곧 save DB에 있는 고유한 id 값이고 bookmark와 맵핑할 수 있게 하는 id 값임
    @POST("/save/news")
    suspend fun saveNewsBookmark(
        @Body body: RequestNewsBookmark
    ): Response<ResponseBodys>

    @POST("/save/recruit")
    suspend fun saveRecruitmentBookmark(
        @Body body: RequestRecruitmentBookmark
    ): Response<ResponseBodys>

    @GET("/save/news/{id}")
    suspend fun findSaveNews(
        @Path("id") id: Long
    ): Response<RequestNewsBookmark>

    @GET("save/recruit/{id}")
    suspend fun findSaveRecruitment(
        @Path("id") id: Long
    ): Response<RequestRecruitmentBookmark>
}