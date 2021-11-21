package com.ssafy.aongbucks_user.model.api

import com.ssafy.aongbucks_user.model.dto.User
import com.ssafy.aongbucks_user.model.dto.UserInfo
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface UserApiService {

    @POST("/rest/user/login")
    suspend fun login(@Body user : User) : Response<User>

    @POST("/rest/user")
    suspend fun join(@Body user : User) : Response<Boolean>

    @GET("/rest/user/isUsed")
    suspend fun isUsedId(@Query("id") id : String) : Response<Boolean>

    @POST("/rest/user/info")
    suspend fun getUserInfo(@Query("id") id : String) : Response<UserInfo>

}