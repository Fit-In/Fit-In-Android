package com.example.fitin_kotlin.data.model.network.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseBookmark (
    @SerializedName("id")
    val id: Long?,
    @SerializedName("bookmarkName")
    val bookmarkName: String,
    @SerializedName("bookmarkProfile")
    val bookmarkDescription: String
) : Parcelable