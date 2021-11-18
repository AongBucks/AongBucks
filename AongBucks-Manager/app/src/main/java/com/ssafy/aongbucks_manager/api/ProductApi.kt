package com.ssafy.aongbucks_manager.api

import com.ssafy.aongbucks_manager.dto.Product
import retrofit2.Call
import retrofit2.http.GET

interface ProductApi {
    // 전체 상품의 목록을 반환한다
    @GET("rest/product")
    fun getProductList(): Call<List<Product>>
}