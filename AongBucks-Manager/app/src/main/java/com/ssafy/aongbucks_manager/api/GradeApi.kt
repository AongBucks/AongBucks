package com.ssafy.aongbucks_manager.api

import com.ssafy.aongbucks_manager.reponse.GradeResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH

interface GradeApi {
    @GET("rest/grade/all")
    fun selectAll(): Call<List<GradeResponse>>

    @PATCH("rest/grade/info")
    fun updateGradeInfo(@Body body: GradeResponse): Call<Boolean>
}