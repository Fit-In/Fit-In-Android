package com.example.fitin_kotlin.data.model.network.request

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RequestSignUp(
    @SerializedName("email")
    val email: String?,
    @SerializedName("password")
    val password: String?,
    @SerializedName("name")
    val name: String?
    // Parcelable 직접 구현 필요할 수 있음


) : Parcelable
