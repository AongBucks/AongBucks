package com.ssafy.aongbucks_user.model.api

import com.ssafy.aongbucks_user.model.dto.Grade
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path

interface GradeApiService {

    @GET("/rest/grade/all")
    suspend fun getAllGrade() : Response<List<Grade>>

    @GET("rest/grade/discount/{userId}")
    suspend fun getDiscountGrade(@Path("userId") userId : String) : Response<Float>

}