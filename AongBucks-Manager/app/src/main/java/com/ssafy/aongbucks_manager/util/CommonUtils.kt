package com.ssafy.aongbucks_manager.util

import com.ssafy.aongbucks_manager.reponse.OrderDetailResponse
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

object CommonUtils {

    //천단위 콤마
    fun makeComma(num: Int): String {
        var comma = DecimalFormat("#,###")
        return "${comma.format(num)} 원"
    }

    fun getFormattedString(date:Date): String {
        val dateFormat = SimpleDateFormat("yyyy.MM.dd HH시 mm분")
        dateFormat.timeZone = TimeZone.getTimeZone("Seoul/Asia")

        return dateFormat.format(date)
    }

    fun getFormattedShortString(date:Date): String {
        val dateFormat = SimpleDateFormat("yyyy.MM.dd")
        dateFormat.timeZone = TimeZone.getTimeZone("Seoul/Asia")

        return dateFormat.format(date)
    }

    // 시간 계산을 통해 완성된 제품인지 확인
    fun isOrderCompleted(completed: Char): String {
        return if(completed == 'Y')  "주문완료" else "진행 중.."
    }
}