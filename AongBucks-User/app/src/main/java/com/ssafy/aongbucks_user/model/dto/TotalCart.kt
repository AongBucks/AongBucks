package com.ssafy.aongbucks_user.model.dto

data class TotalCart (
    var price: Int,
    var discount: Int,
    var reserve: Int,
    var quantity: Int,
    var resultPrice: Int = price-discount
)