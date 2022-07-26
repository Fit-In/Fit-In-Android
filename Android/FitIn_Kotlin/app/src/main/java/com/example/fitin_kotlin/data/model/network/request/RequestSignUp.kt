package com.example.fitin_kotlin.data.model.network.request

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
    val name: String?,
    @SerializedName("phone")
    val phone: Long?

) : Parcelable
