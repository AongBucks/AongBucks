package com.ssafy.aongbucks_user.model.dto

data class UserInfo(
    val grade : Grade,
    val order : List<Order>,
    val user : User
)
