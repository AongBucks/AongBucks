package com.ssafy.aongbucks_user.model

import com.ssafy.aongbucks_user.config.ApplicationClass
import com.ssafy.aongbucks_user.model.api.*

object RetrofitClient {

    val userService : UserApiService by lazy {
        ApplicationClass.retrofit.create(UserApiService::class.java)
    }

    val productService : ProductApiService by lazy {
        ApplicationClass.retrofit.create(ProductApiService::class.java)
    }

    val commentService : CommentApiService by lazy {
        ApplicationClass.retrofit.create(CommentApiService::class.java)
    }

    val favoriteService : FavoriteApiService by lazy {
        ApplicationClass.retrofit.create(FavoriteApiService::class.java)
    }

    val orderService : OrderApiService by lazy {
        ApplicationClass.retrofit.create(OrderApiService::class.java)
    }

}