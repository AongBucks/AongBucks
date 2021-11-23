package com.ssafy.aongbucks_user.model.dto

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Grade(
    var id: Int,
    var title: String,
    var discount : Float,
    var img: String,
    var standard : Int,
    var to: Int,
    var next : String
) : Parcelable {
    constructor(id : Int, title : String, discount : Float, img : String, standard : Int)
            : this(id, title, discount, img, standard, 0, "")

    constructor(title : String, img: String, to: Int, next : String)
            : this(-1, title, 0f, img, 0, to, next)
}
