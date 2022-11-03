package com.example.fitin_kotlin.data.remote.api

import com.example.fitin_kotlin.data.model.network.request.RequestCreateBookmark
import com.example.fitin_kotlin.data.model.network.response.ResponseBodys
import com.example.fitin_kotlin.data.model.network.response.ResponseBookmark
import com.example.fitin_kotlin.data.model.network.response.ResponseSavedBookmark
import retrofit2.Response
import retrofit2.http.*

interface BookmarkService {

    @GET("/bookmark/list/{email}")
    suspend fun getBookmarkList(
        @Path("email") email: String
    ): List<ResponseBookmark>

    @POST("/bookmark/new")
    suspend fun createBookmark(
        @Body body: RequestCreateBookmark
    ): Response<Void>

    @POST("/bookmark/add")
    suspend fun addBookmark(
        @Body body: RequestCreateBookmark
    ): Response<Void>

    @POST("/bookmark/{bookmarkId}")
    suspend fun findBookmark(
        @Path("bookmarkId") id: Long
    ): List<ResponseSavedBookmark>

}