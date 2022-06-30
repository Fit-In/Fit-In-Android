package com.example.fitin_kotlin.data.remote.api

import androidx.lifecycle.LiveData
import com.example.fitin_kotlin.data.model.network.response.ResponseNews
import com.example.fitin_kotlin.data.model.network.response.ResponseNewsList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface NewsService {

    @GET("/news/save")
    suspend fun callNews(): Response<Void>

    @GET("/news")
    suspend fun getNewsList(): LiveData<List<ResponseNewsList>>

    @GET("/news/{id}")
    suspend fun getNews(
        @Path("id") id: Long
    ): LiveData<ResponseNews>


}