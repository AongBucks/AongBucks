package com.ssafy.aongbucks_user.model.dto

data class Favorite(
    val id : Int,
    val userId : String,
    val productId : Int
) {
    constructor(userId : String, productId : Int):this(-1, userId, productId)
}