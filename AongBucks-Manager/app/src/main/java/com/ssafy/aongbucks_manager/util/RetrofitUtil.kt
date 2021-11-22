package com.ssafy.aongbucks_manager.util

import com.ssafy.aongbucks_manager.api.GradeApi
import com.ssafy.aongbucks_manager.api.OrderApi
import com.ssafy.aongbucks_manager.api.ProductApi
import com.ssafy.aongbucks_manager.config.ApplicationClass

class RetrofitUtil {
    companion object{
        val orderService = ApplicationClass.retrofit.create(OrderApi::class.java)
        val productService = ApplicationClass.retrofit.create(ProductApi::class.java)
        val gradeService = ApplicationClass.retrofit.create(GradeApi::class.java)
    }
}