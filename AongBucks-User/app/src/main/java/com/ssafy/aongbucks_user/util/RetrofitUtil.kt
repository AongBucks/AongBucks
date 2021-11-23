package com.ssafy.aongbucks_user.util

import com.ssafy.aongbucks_user.config.ApplicationClass
import com.ssafy.aongbucks_user.model.api.PayApiService

class RetrofitUtil {
    companion object {
        val payService = ApplicationClass.retrofit.create(PayApiService::class.java)
    }
}