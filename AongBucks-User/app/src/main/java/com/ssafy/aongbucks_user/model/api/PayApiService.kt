package com.ssafy.aongbucks_user.model.api

import com.ssafy.aongbucks_user.model.dto.Pay
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface PayApiService {
    @POST("rest/pay/{userId}")
    suspend fun postUserPay(@Path("userId") userId: String) : Response<Boolean>

    @GET("rest/pay/{userId}")
    suspend fun getUserPay(@Path("userId") userId: String): Response<Pay>

    @GET("rest/pay/isJoined/{userId}")
    suspend fun getUserIsJoined(@Path("userId") userId: String): Response<Boolean>

    @PATCH("rest/pay/price")
    suspend fun patchUserPrice(@Body pay: Pay) : Response<Boolean>
}