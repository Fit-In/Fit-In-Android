package com.example.fitin_kotlin.data.model.network.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseNewsList(
    @SerializedName("id")
    val id: Long?,
    @SerializedName("press")
    val press: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("category")
    val category: String?
) : Parcelable