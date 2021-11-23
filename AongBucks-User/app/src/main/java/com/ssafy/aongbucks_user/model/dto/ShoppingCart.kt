package com.ssafy.aongbucks_user.model.dto

data class ShoppingCart(
    val menuId: Int,
    val menuImg: String,
    val menuName: String,
    var menuCnt: Int,
    val menuPrice: Int,
    var totalPrice: Int = menuCnt*menuPrice,
    val type: String
){
    var position = -1

    fun addDupMenu(shoppingCart: ShoppingCart){
        this.menuCnt += shoppingCart.menuCnt
        this.totalPrice = this.menuCnt * this.menuPrice
    }
}