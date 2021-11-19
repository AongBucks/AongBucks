package com.ssafy.aongbucks_user.service

import android.app.Application
import com.ssafy.aongbucks_user.config.ApplicationClass
import com.ssafy.aongbucks_user.dto.User
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface UserApiService {
    @POST("/rest/user/login")
    suspend fun login(@Body body : User) : User

    @POST("/rest/user")
    suspend fun join(@Body body : User) : Boolean

    @GET("/rest/user/isUsed")
    suspend fun isUsedId(@Query("id") id : String) : Boolean

    @POST("/rest/user/info")
    suspend fun getUserInfo(@Query("id") id : String) : User
}

object UserApi {
    val userService : UserApiService by lazy {
        ApplicationClass.retrofit.create(UserApiService::class.java)
    }
}