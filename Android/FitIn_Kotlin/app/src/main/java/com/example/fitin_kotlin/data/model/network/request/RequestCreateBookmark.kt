package com.example.fitin_kotlin.data.model.network.request

import com.google.gson.annotations.SerializedName

data class RequestCreateBookmark (
    @SerializedName("accountEmail")
    val email: String?,
    @SerializedName("bookmarkName")
    val bookmarkName: String?,
    @SerializedName("bookmarkProfile")
    val bookmarkDescription: String?,
    @SerializedName("bookmarkId")
    val bookmarkId: Long,
    @SerializedName("saveId")
    val saveId: Long
)