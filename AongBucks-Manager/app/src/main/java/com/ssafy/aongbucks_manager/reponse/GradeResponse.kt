package com.ssafy.aongbucks_manager.reponse

import com.google.gson.annotations.SerializedName

data class GradeResponse (
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("discount") var discount: Float,
    @SerializedName("img") val img: String,
    @SerializedName("standard") var standard: Int
)