package com.example.fitin_kotlin.data.model.network.request

import com.google.gson.annotations.SerializedName

data class RequestFindPassword (

    @SerializedName("email")
    val email: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("phone")
    val phone: String
)