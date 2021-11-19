package com.ssafy.aongbucks_user.dto

data class Stamp (
    val id: Int,
    val userId: String,
    val orderId: Int,
    val quantity: Int,
    val used: Char
)