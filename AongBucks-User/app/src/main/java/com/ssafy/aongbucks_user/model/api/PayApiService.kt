package com.ssafy.aongbucks_user.model.api

import com.ssafy.aongbucks_user.model.dto.Pay
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface PayApiService {
    @POST("rest/pay/{userId}")
    fun postUserPay(@Path("userId") userId: String) : Call<Boolean>

    @GET("rest/pay/{userId}")
    fun getUserPay(@Path("userId") userId: String): Call<Pay>

    @GET("rest/pay/isJoined/{userId}")
    fun getUserIsJoined(@Path("userId") userId: String): Call<Boolean>

    @PATCH("rest/pay/price")
    fun patchUserPrice(@Body pay: Pay) : Call<Boolean>
}