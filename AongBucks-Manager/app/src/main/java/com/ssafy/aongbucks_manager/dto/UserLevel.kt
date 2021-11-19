package com.ssafy.aongbucks_manager.dto

data class UserLevel(
    var title: String,
    var unit: Int,
    var max: Int,
    var img: String
){
    companion object{
        val levelTitleArr = arrayOf("씨앗", "꽃", "열매", "커피콩", "커피나무")

        var userInfoList = arrayOf(
            UserLevel(levelTitleArr[0], 10, 50, "seeds.png"),
            UserLevel(levelTitleArr[1], 15, 125, "flower.png"),
            UserLevel(levelTitleArr[2], 20, 225, "coffee_fruit.png"),
            UserLevel(levelTitleArr[3], 25, 350, "coffee_beans.png"),
            UserLevel(levelTitleArr[4], Int.MAX_VALUE, Int.MAX_VALUE, "coffee_tree.png")
        )
    }
}