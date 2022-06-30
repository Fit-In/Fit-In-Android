package com.example.fitin_kotlin.data.model.network.response

import com.google.gson.annotations.SerializedName

data class ResponseNews(
    @SerializedName("id")
    val id: Long?,
    @SerializedName("press")
    val press: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("content")
    val content: String?,
    @SerializedName("category")
    val category: String?,
    @SerializedName("image_url")
    val image_url: String?,
    @SerializedName("link")
    val link: String?
)
