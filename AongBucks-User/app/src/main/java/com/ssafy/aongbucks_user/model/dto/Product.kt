package com.ssafy.aongbucks_user.model.dto

data class Product (
    val id: Int,
    val name: String,
    val type: String,
    val price: Int,
    val img: String,
    val comment: ArrayList<Comment> = ArrayList()
) {
    constructor(): this(0, "","",0,"")
}
