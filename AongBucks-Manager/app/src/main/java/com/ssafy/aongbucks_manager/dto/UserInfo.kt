package com.ssafy.aongbucks_manager.dto

data class UserInfo(
    val grade: Grade,
    val order: List<Order>,
    val user: User
)