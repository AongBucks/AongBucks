package com.ssafy.aongbucks_manager.dto

data class OrderDetail (
    var id: Int,
    var orderId: Int,
    var productId: Int,
    var quantity: Int ) {

    var unitPrice:Int = 0
    var img:String = ""
    var productName:String = ""
    var productType:String = ""

    constructor(orderId: Int, productId: Int, quantity: Int) :this(0, orderId, productId, quantity)
    constructor(productId: Int, quantity: Int) :this(0, 0, productId, quantity)
    constructor():this(0,0)

}
