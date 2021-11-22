package com.ssafy.aongbucks_user.model.response

import com.google.gson.annotations.SerializedName

data class MenuDetailWithCommentResponse (
    @SerializedName("img") val productImg: String,
    @SerializedName("avg") val productRatingAvg: Double,
    @SerializedName("user_id") val userId: String?,
    @SerializedName("sells") val productTotalSellCnt: Int,
    @SerializedName("price") val productPrice: Int,
    @SerializedName("name") val productName: String,
    @SerializedName("rating") val productRating: Double,
    @SerializedName("commentId") val commentId: Int = -1,
    @SerializedName("comment") var commentContent: String?,
    @SerializedName("userName") val commentUserName: String?,
    @SerializedName("commentCnt") val productCommentTotalCnt: Int,
    @SerializedName("type") val type: String
)
