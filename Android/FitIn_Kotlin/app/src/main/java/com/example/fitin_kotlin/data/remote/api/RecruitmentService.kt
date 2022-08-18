package com.example.fitin_kotlin.data.remote.api

import com.example.fitin_kotlin.data.model.network.response.ResponseRecruitment
import com.example.fitin_kotlin.data.model.network.response.ResponseRecruitmentList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RecruitmentService {

    @GET("/recruit/save")
    suspend fun callRecruitment(): Response<Void>

    @GET("/recruit")
    suspend fun getRecruitmentList(): List<ResponseRecruitmentList>

    @GET("/recruit/{id}")
    suspend fun getRecruitment(
        @Path("id") id: Long
    ) : ResponseRecruitment
}