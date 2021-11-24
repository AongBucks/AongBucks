package com.ssafy.aongbucks_user.util

import com.ssafy.aongbucks_user.config.ApplicationClass
import com.ssafy.aongbucks_user.model.response.LatestOrderResponse
import com.ssafy.aongbucks_user.model.response.OrderDetailResponse
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

object CommonUtils {

    //천단위 콤마
    fun makeComma(num: Int): String {
        var comma = DecimalFormat("#,###")
        return "${comma.format(num)}원"
    }

    fun getFormattedString(date: Date): String {
        val dateFormat = SimpleDateFormat("yyyy.MM.dd HH시 mm분")
        dateFormat.timeZone = TimeZone.getTimeZone("Seoul/Asia")

        return dateFormat.format(date)
    }

    fun getFormattedShortString(date: Date): String {
        val dateFormat = SimpleDateFormat("yyyy.MM.dd")
        dateFormat.timeZone = TimeZone.getTimeZone("Seoul/Asia")

        return dateFormat.format(date)
    }

    // 시간 계산을 통해 완성된 제품인지 확인
    fun isOrderCompleted(orderDetail: OrderDetailResponse): String {
        return if( checkTime(orderDetail.orderDate.time))  "주문완료" else "진행 중.."
    }

    // 시간 계산을 통해 완성된 제품인지 확인
    fun isOrderCompleted(order: LatestOrderResponse): String {
        return if( checkTime(order.orderDate.time))  "주문완료" else "진행 중.."
    }

    private fun checkTime(time:Long):Boolean{
        val curTime = (Date().time+60*60*9*1000)

        return (curTime - time) > ApplicationClass.ORDER_COMPLETED_TIME
    }

}