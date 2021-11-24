package com.ssafy.aongbucks_user.model.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.*

// o_id 기준으로 분류하고, img는 그 중 하나로 사용하기
@Parcelize
data class LatestOrderResponse(
    @SerializedName("img") val img: String,
    @SerializedName("quantity") var orderCnt: Int,
    @SerializedName("user_id") val userId: String,
    @SerializedName("o_id") val orderId: Int,
    @SerializedName("name") val productName: String,
    @SerializedName("order_time") val orderDate: Date,
    @SerializedName("completed") var orderCompleted: Char = 'N',
    @SerializedName("price") val productPrice: Int,
    @SerializedName("type") val type: String,
    @SerializedName("id") val productId: Int,
    var totalPrice: Int = 0
) : Parcelable