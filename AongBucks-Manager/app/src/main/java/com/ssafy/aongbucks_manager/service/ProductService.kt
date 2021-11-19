package com.ssafy.aongbucks_manager.service

import com.ssafy.aongbucks_manager.dto.Product
import com.ssafy.aongbucks_manager.util.RetrofitCallback
import com.ssafy.aongbucks_manager.util.RetrofitUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "UserService_싸피"
class ProductService {

    fun getProductList(callback: RetrofitCallback<List<Product>>)  {
        val menuInfoRequest: Call<List<Product>> = RetrofitUtil.productService.getProductList()
        menuInfoRequest.enqueue(object : Callback<List<Product>> {
            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
                val res = response.body()
                if(response.code() == 200){
                    if (res != null) {
                        callback.onSuccess(response.code(), res)
                    }
                } else {
                    callback.onFailure(response.code())
                }
            }

            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                callback.onError(t)
            }
        })
    }
}