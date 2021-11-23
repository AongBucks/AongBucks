package com.ssafy.aongbucks_user.model.api

import com.ssafy.aongbucks_user.model.dto.Order
import com.ssafy.aongbucks_user.model.response.LatestOrderResponse
import com.ssafy.aongbucks_user.model.response.OrderDetailResponse
import retrofit2.Response
import retrofit2.http.*

interface OrderApiService {

    // Order 객체를 저장하고 추가된 Order의 id를 반환한다.
    @POST("/rest/order")
    suspend fun makeOrder(@Body order : Order) : Response<Int>

    // {orderId}에 해당하는 주문의 상세 내역을 목록 형태로 반환한다.
    // 사용자 정보 화면의 주문 내역 조회에서 사용된다.
    @GET("/rest/order/{orderId}")
    suspend fun getOrderDetail(@Path("orderId") orderId : Int) : Response<List<OrderDetailResponse>>

    // {id}에 해당하는 사용자의 최근 1개월간 주문 내역을 반환한다.
    // 반환 정보는 1차 주문번호 내림순, 2차 주문 상세 내림차순으로 정렬된다.
    @GET("/rest/order/byUser")
    suspend fun getLastMonthOrder(@Query("id") id : String) : Response<List<LatestOrderResponse>>

}