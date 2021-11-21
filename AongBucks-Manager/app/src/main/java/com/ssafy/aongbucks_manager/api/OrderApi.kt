package com.ssafy.aongbucks_manager.api

import com.ssafy.aongbucks_manager.reponse.OrderDetailResponse
import com.ssafy.aongbucks_manager.reponse.TotalOrderResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface OrderApi {
    // {orderId}에 해당하는 주문의 상세 내역을 목록 형태로 반환한다.
    // 사용자 정보 화면의 주문 내역 조회에서 사용된다.
    @GET("rest/order/{orderId}")
    fun getOrderDetail(@Path("orderId") orderId: Int): Call<List<OrderDetailResponse>>

    @GET("rest/order/manager")
    fun getTotalOrderList(): Call<List<TotalOrderResponse>>

    @POST("rest/order/state/{orderId}")
    fun postOrderState(@Path("orderId") orderId: Int) : Call<Int>
}