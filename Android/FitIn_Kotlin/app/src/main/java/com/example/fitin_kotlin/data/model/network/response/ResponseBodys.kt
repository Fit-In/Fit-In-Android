package com.example.fitin_kotlin.data.model.network.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class ResponseBodys(
    @SerializedName("state")
    val state: Int,
    @SerializedName("result")
    val result: String,
    @SerializedName("massage")
    val message: String,
    @SerializedName("data")
    val data: Any,
    @SerializedName("error")
    val error: List<Any>
)