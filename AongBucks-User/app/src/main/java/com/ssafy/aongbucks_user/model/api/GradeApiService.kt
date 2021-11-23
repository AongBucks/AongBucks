package com.ssafy.aongbucks_user.model.api

import com.ssafy.aongbucks_user.model.dto.Grade
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.PATCH

interface GradeApiService {

    @GET("/rest/grade/all")
    suspend fun getAllGrade() : Response<Grade>

}