package com.ssafy.aongbucks_manager.dto

data class UserOrderDetail (
    val orderId : Int, // 주문번호
    val orderDate : String, //일자
    val sumQuantity : Int,  //주문수량 함계
    val sumPrice : Int,  //합계 금액
    val img : String,  //대표 주문 이미지
    val productId : Int, // 대표 주문 번호
    val productName :String //대표 주문 상품
)