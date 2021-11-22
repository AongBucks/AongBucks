package com.ssafy.aongbucks_user.model.api

import com.ssafy.aongbucks_user.model.dto.Product
import com.ssafy.aongbucks_user.model.response.MenuDetailWithCommentResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductApiService {

    // 전체 상품의 목록 반환
    @GET("/rest/product")
    suspend fun getProducts() : Response<List<Product>>

    // 사용자가 즐겨찾기로 등록한 상품의 목록 반환
    @GET("/rest/product/favorite")
    suspend fun getFavoriteProducts(@Query("userId") userId : String) : Response<List<Product>>

    // {productId}에 해당하는 상품의 정보를 comment와 함께 반환
    @GET("/rest/product/{productId}")
    suspend fun getProductWithComments(@Path("productId") productId : Int, @Query("userId") userId : String): Response<List<MenuDetailWithCommentResponse>>

}