package com.example.fitin_kotlin.data.model.network.response

import com.example.fitin_kotlin.data.model.network.request.RequestNewsBookmark
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

fun ResponseNews.asRequestNewsBookmark(): RequestNewsBookmark {
    return RequestNewsBookmark(
        press,
        title,
        content,
        category,
        image_link,
        link,
        keyword,
        url_link,
        time
    )
}
