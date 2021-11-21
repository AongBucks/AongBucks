package com.ssafy.aongbucks_manager.service

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ssafy.aongbucks_manager.reponse.OrderDetailResponse
import com.ssafy.aongbucks_manager.util.RetrofitUtil
import com.ssafy.aongbucks_manager.dto.*
import com.ssafy.aongbucks_manager.reponse.TotalOrderResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "OrderService_싸피"
class OrderService{

    // 주문 상세 내역 가져오는 API
    fun getOrderDetails(orderId: Int): LiveData<List<OrderDetailResponse>> {
        val responseLiveData: MutableLiveData<List<OrderDetailResponse>> = MutableLiveData()
        val orderDetailRequest: Call<List<OrderDetailResponse>> = RetrofitUtil.orderService.getOrderDetail(orderId)

        orderDetailRequest.enqueue(object : Callback<List<OrderDetailResponse>> {
            override fun onResponse(call: Call<List<OrderDetailResponse>>, response: Response<List<OrderDetailResponse>>) {
                val res = response.body()
                if(response.code() == 200){
                    if (res != null) {
                        responseLiveData.value = res
                    }
                    Log.d(TAG, "onResponse: $res")
                } else {
                    Log.d(TAG, "onResponse: Error Code ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<OrderDetailResponse>>, t: Throwable) {
                Log.d(TAG, t.message ?: "주문 상세 내역 받아오는 중 통신오류")
            }
        })

        return responseLiveData
    }

    fun getAllOrderInfo(): LiveData<List<TotalOrderResponse>> {
        val responseLiveData: MutableLiveData<List<TotalOrderResponse>> = MutableLiveData()
        val allOrderInfoResponse: Call<List<TotalOrderResponse>> = RetrofitUtil.orderService.getTotalOrderList()

        allOrderInfoResponse.enqueue(object : Callback<List<TotalOrderResponse>> {
            override fun onResponse(call: Call<List<TotalOrderResponse>>, response: Response<List<TotalOrderResponse>>) {
                val res = response.body()
                if(response.code() == 200){
                    if (res != null) {
                        responseLiveData.value = res
                    }
                    Log.d(TAG, "onResponse: $res")
                } else {
                    Log.d(TAG, "onResponse: Error Code ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<TotalOrderResponse>>, t: Throwable) {
                Log.d(TAG, t.message ?: "주문 내역 받아오는 중 통신오류")
            }
        })

        return responseLiveData
    }

    fun completeState(id: Int) : LiveData<Boolean> {
        var isSuccess = MutableLiveData<Boolean>(false)

        RetrofitUtil.orderService.postOrderState(id)
            .enqueue(object: Callback<Int> {
                override fun onResponse(call: Call<Int>, response: Response<Int>) {
                    val res = response.body()
                    if(response.code() == 200){
                        if (res != null) {
                            isSuccess.value = true
                        }
                        Log.d(TAG, "onResponse: $res")
                    } else {
                        Log.d(TAG, "onResponse: Error Code ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<Int>, t: Throwable) {
                    Log.d(TAG, t.message ?: "주문 상태 변경 중 통신오류")
                }
            })

        return isSuccess
    }
}