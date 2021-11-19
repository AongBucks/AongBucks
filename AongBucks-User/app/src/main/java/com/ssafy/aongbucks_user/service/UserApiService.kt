package com.ssafy.aongbucks_user.service

import android.app.Application
import com.ssafy.aongbucks_user.config.ApplicationClass
import com.ssafy.aongbucks_user.dto.User
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApiService {
    @GET("/rest/user")
    suspend fun login(@Body user : User) : User

    @GET("/rest")
    suspend fun join(@Body user : User) : Boolean

    @GET("/rest/user/isUsed")
    suspend fun isUsedId(@Query("id") id : String) : Boolean

    @GET("/rest/user/info")
    suspend fun getUserInfo(@Query("id") id : String) : User
}

object UserApi {
    val userService : UserApiService by lazy {
        ApplicationClass.retrofit.create(UserApiService::class.java)
    }
}