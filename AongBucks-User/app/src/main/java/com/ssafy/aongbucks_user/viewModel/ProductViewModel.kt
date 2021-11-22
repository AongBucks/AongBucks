package com.ssafy.aongbucks_user.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.aongbucks_user.model.RetrofitClient
import com.ssafy.aongbucks_user.model.dto.Product
import com.ssafy.aongbucks_user.model.response.MenuDetailWithCommentResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private const val TAG = "ProductViewModel_싸피"
class ProductViewModel : ViewModel() {

    /**
     * 전체 상품의 목록 반환
     */
    private val _products = MutableLiveData<List<Product>>()
    val products : LiveData<List<Product>>
        get() = _products

    private fun getProductList() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = RetrofitClient.productService.getProducts()
            if (response.isSuccessful) {
                if (response.body() != null) {
                    _products.postValue(response.body())
                    Log.d(TAG, "getProductList: ${_products.value}")
                }
            } else {
                Log.d(TAG, "getProductList: ${response.code()}")
            }
        }
    }

    fun getProducts() {
        getProductList()
    }

    /**
     * 사용자가 즐겨찾기로 등록한 상품의 목록 반환
     */
    private val _favorites = MutableLiveData<List<Product>>()
    val favorites : LiveData<List<Product>>
        get() = _favorites

    private fun getFavoriteProductList(userId : String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = RetrofitClient.productService.getFavoriteProducts(userId)
            if (response.isSuccessful) {
                if (response.body() != null) {
                    _favorites.postValue(response.body())
                    Log.d(TAG, "getFavoriteProductList: ${_favorites.value}")
                }
            } else {
                Log.d(TAG, "getFavoriteProductList: ${response.code()}")
            }
        }
    }

    fun getFavoriteProducts(userId : String) {
        getFavoriteProductList(userId)
    }


    /**
     *  {productId}에 해당하는 상품의 정보를 comment와 함께 반환
     */
    private val _productWithComments = MutableLiveData<List<MenuDetailWithCommentResponse>>()
    val productWithComments : LiveData<List<MenuDetailWithCommentResponse>>
        get() = _productWithComments

    private fun getProductDetailWithComments(productId : Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = RetrofitClient.productService.getProductWithComments(productId)
            if (response.isSuccessful) {
                if (response.body() != null) {
                    val menuDetail = response.body() as List<MenuDetailWithCommentResponse>
                    _productWithComments.postValue(menuDetail)
                    Log.d(TAG, "getProductDetailWithComments: ${_productWithComments.value}")
                }
            } else {
                Log.d(TAG, "getProductWithComments: ${response.code()}")
            }
        }
    }

    fun getProductWithComments(productId : Int) {
        getProductDetailWithComments(productId)
    }

}