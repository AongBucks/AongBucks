package com.ssafy.aongbucks_user.model.dto

import java.util.ArrayList

data class Order (
    var id: Int,
    var userId: String,
    var orderTable: String,
    var orderTime: String,
    var completed: String,
    val details: ArrayList<OrderDetail> = ArrayList()
){

    var totalQnanty:Int = 0
    var totalPrice:Int = 0
    var topProductName:String = ""
    var topImg:String = ""

    constructor():this(0,"","","","")

    constructor(userId: String, orderTable: String, orderTime: String):
            this(0, userId, orderTable, orderTime, "N")
}
