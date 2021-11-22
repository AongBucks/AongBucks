package com.ssafy.aongbucks_user.model.api

import com.ssafy.aongbucks_user.model.dto.Favorite
import retrofit2.Response
import retrofit2.http.*

interface FavoriteApiService {

    // 즐겨찾기 추가
    @POST("/rest/favorite")
    suspend fun addFavorite(@Body favorite : Favorite) : Response<Boolean>

    // 즐겨찾기 삭제
    @DELETE("/rest/favorite/{userId}")
    suspend fun delFavorite(@Path("userId") userId : String, @Query("productId") productId : Int) : Response<Boolean>

}