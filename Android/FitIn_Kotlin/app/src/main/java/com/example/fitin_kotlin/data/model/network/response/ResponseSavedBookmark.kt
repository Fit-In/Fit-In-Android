package com.example.fitin_kotlin.data.model.network.response

import com.google.gson.annotations.SerializedName

data class ResponseSavedBookmark(
    @SerializedName("bookmarkId")
    val bookmarkId: Long,
    @SerializedName("saveId")
    val saveId: Long,
    @SerializedName("position")
    val position: String,
    @SerializedName("title")
    val title:String
)
