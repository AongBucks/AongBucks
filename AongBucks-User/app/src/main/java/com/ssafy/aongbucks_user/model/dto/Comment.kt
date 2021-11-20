package com.ssafy.aongbucks_user.model.dto

data class Comment(
    val id: Int = -1,
    val userId: String,
    var productId : Int = -1,
    val rating: Double,
    val comment: String
)