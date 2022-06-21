package com.example.fitin_kotlin.data.model.network.response

import com.google.gson.annotations.SerializedName

data class ResponseSignUp(
    @SerializedName("email")
    val email: String
)
