package com.example.fitin_kotlin.data.model.network.request

import com.google.gson.annotations.SerializedName

data class RequestNewsBookmark(
    @SerializedName("press")
    val press: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("content")
    val content: String?,
    @SerializedName("category")
    val category: String?,
    @SerializedName("image_link")
    val image_link: String?,
    @SerializedName("link")
    val link: String?,
    @SerializedName("keyword")
    val keyword: String?,
    @SerializedName("url_link")
    val url_link: String?,
    @SerializedName("time")
    val time: String?

)
