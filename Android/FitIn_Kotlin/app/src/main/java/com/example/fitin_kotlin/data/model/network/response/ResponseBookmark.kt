package com.example.fitin_kotlin.data.model.network.response

import com.google.gson.annotations.SerializedName

data class ResponseBookmark (
    @SerializedName("id")
    val id: Long?,
    @SerializedName("bookmarkName")
    val bookmarkName: String,
)