package com.ssafy.aongbucks_user.model.api

import com.ssafy.aongbucks_user.model.dto.Comment
import retrofit2.Response
import retrofit2.http.*

interface CommentApiService {

    // comment 추가
    @POST("/rest/comment")
    suspend fun addComment(@Body comment : Comment) : Response<Boolean>

    // comment 수정
    @PUT("/rest/comment")
    suspend fun editComment(@Body comment : Comment) : Response<Boolean>

    // comment 삭제
    @DELETE("/rest/comment/{id}")
    suspend fun delComment(@Path("id") id : Int) : Response<Boolean>

}