package com.ssafy.aongbucks_user.model.dto

data class User(
    val id: String,
    val name: String,
    val pass: String,
    val stamps: Int,
    val grade_id: Int,
    val stampList: ArrayList<Stamp> = ArrayList()
) {
    constructor(): this("", "", "", 0, 0)
    constructor(id : String, pass : String) : this(id, "", pass, 0, 0)
}