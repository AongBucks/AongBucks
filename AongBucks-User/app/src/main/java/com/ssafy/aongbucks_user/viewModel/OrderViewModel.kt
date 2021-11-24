package com.ssafy.aongbucks_user.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.aongbucks_user.model.RetrofitClient
import com.ssafy.aongbucks_user.model.dto.Order
import com.ssafy.aongbucks_user.model.response.LatestOrderResponse
import com.ssafy.aongbucks_user.model.response.OrderDetailResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private const val TAG = "OrderViewModel_싸피"
class OrderViewModel : ViewModel() {

    /**
     * 주문 추가
     */
    private val _orderId = MutableLiveData<Int>()
    val orderId : LiveData<Int>
        get() = _orderId

    private fun addOrder(order : Order) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = RetrofitClient.orderService.makeOrder(order)
            if (response.isSuccessful) {
                if (response.body() != null) {
                    _orderId.postValue(response.body()!!)
                }
            } else {
                Log.d(TAG, "addOrder: ${response.code()}")
            }
        }
    }

    fun makeOrder(order : Order) {
        addOrder(order)
    }

    /**
     * 주문내역 조회
     */
    private val _orderDetails = MutableLiveData<List<OrderDetailResponse>>()
    val orderDetails : LiveData<List<OrderDetailResponse>>
        get() = _orderDetails

    private fun getOrderDetailList(orderId : Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = RetrofitClient.orderService.getOrderDetail(orderId)
            if (response.isSuccessful) {
                if (response.body() != null) {
                    _orderDetails.postValue(response.body())
                }
            } else {
                Log.d(TAG, "getOrderDetailList: ${response.code()}")
            }
        }
    }

    fun getOrderDetail(orderId : Int) {
        getOrderDetailList(orderId)
    }

    /**
     * 최근 1개월간 주문내역 조회
     */
    private val _latestOrders = MutableLiveData<List<LatestOrderResponse>>()
    val latestOrders : LiveData<List<LatestOrderResponse>>
        get() = _latestOrders

    private fun getLatestOrder(userId : String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = RetrofitClient.orderService.getLastMonthOrder(userId)
            if (response.isSuccessful) {
                if (response.body() != null) {
                    _latestOrders.postValue(response.body())
                }
            } else {
                Log.d(TAG, "getLatestOrder: ${response.code()}")
            }
        }
    }

    fun getLastMonthOrder(userId : String) {
        getLatestOrder(userId)
    }

}