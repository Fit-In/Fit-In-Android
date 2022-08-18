package com.example.fitin_kotlin.data.model.network.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseRecruitmentList(
    @SerializedName("id")
    val id: Long?,
    @SerializedName("company_name")
    val companyName: String?,
    @SerializedName("position")
    val position: String?,
    @SerializedName("recruitment_type")
    val recruitmentType: String?,
    @SerializedName("career")
    val career: String?
) : Parcelable