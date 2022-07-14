package com.example.fitin_kotlin.data.model.network.request

import com.google.gson.annotations.SerializedName

data class RequestPhoneNumber (
    @SerializedName("to")
    val phoneNumber: String?
)