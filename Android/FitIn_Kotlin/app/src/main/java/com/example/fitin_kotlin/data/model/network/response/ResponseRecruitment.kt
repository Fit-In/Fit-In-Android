package com.example.fitin_kotlin.data.model.network.response

import com.example.fitin_kotlin.data.model.network.request.RequestRecruitmentBookmark
import com.google.gson.annotations.SerializedName

data class ResponseRecruitment(
    @SerializedName("id")
    val id: Long?,
    @SerializedName("company_name")
    val companyName: String?,
    @SerializedName("position")
    val position: String?,
    @SerializedName("tag")
    val tag: String?,
    @SerializedName("career")
    val career: String?,
    @SerializedName("recruitment_type")
    val recruitmentType: String?,
    @SerializedName("recruitment_period")
    val recruitmentPeriod: String?,
    @SerializedName("url_link")
    val urlLink: String?,
    @SerializedName("specific_info")
    val specificInfo: String?
)

fun ResponseRecruitment.asRequestRecruitmentBookmark(): RequestRecruitmentBookmark {
    return RequestRecruitmentBookmark(
        companyName,
        position,
        tag,
        career,
        recruitmentType,
        recruitmentPeriod,
        urlLink,
        specificInfo
    )
}
