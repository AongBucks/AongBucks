package com.ssafy.aongbucks_user.service

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ssafy.aongbucks_user.model.dto.Pay
import com.ssafy.aongbucks_user.util.RetrofitUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "PayService_싸피"
class PayService {
    fun joinPay(userId: String) : LiveData<Boolean> {
        val liveData: MutableLiveData<Boolean> = MutableLiveData()

        RetrofitUtil.payService.postUserPay(userId)
            .enqueue(object : Callback<Boolean> {
                override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                    val res = response.body()
                    if(response.code() == 200){
                        Log.d(TAG, "onResponse: $res")
                        liveData.value = true
                    } else {
                        Log.d(TAG, "onResponse: Error Code ${response.code()}")
                        liveData.value = false
                    }
                }

                override fun onFailure(call: Call<Boolean>, t: Throwable) {
                    Log.d(TAG, t.message ?: "pay 정보 받아오는 중 통신오류")
                    liveData.value = false
                }

            })
        return liveData
    }

    fun getPayInfo(userId: String) : MutableLiveData<Pay?> {
        val liveData: MutableLiveData<Pay?> = MutableLiveData()
        RetrofitUtil.payService.getUserPay(userId)
            .enqueue(object : Callback<Pay> {
                override fun onResponse(call: Call<Pay>, response: Response<Pay>) {
                    val res = response.body()
                    if(response.code() == 200){
                        liveData.value = res
                        Log.d(TAG, "onResponse: $res")
                    } else {
                        Log.d(TAG, "onResponse: Error Code ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<Pay>, t: Throwable) {
                    Log.d(TAG, t.message ?: "pay 정보 받아오는 중 통신오류")
                }

            })

        return liveData
    }

    fun isUserPayJoined(userId: String) : LiveData<Boolean> {
        val liveData: MutableLiveData<Boolean> = MutableLiveData()

        RetrofitUtil.payService.getUserIsJoined(userId)
            .enqueue(object : Callback<Boolean> {
                override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                    val res = response.body()
                    if(response.code() == 200){
                        Log.d(TAG, "onResponse: $res")
                        liveData.value = res
                    } else {
                        Log.d(TAG, "onResponse: Error Code ${response.code()}")
                        liveData.value = false
                    }
                }

                override fun onFailure(call: Call<Boolean>, t: Throwable) {
                    Log.d(TAG, t.message ?: "pay 정보 받아오는 중 통신오류")
                    liveData.value = false
                }

            })
        return liveData
    }

    fun changePayPrice(pay: Pay) : LiveData<Boolean> {
        val liveData: MutableLiveData<Boolean> = MutableLiveData()

        RetrofitUtil.payService.patchUserPrice(pay)
            .enqueue(object : Callback<Boolean> {
                override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                    val res = response.body()
                    if(response.code() == 200){
                        Log.d(TAG, "onResponse: $res")
                        liveData.value = true
                    } else {
                        Log.d(TAG, "onResponse: Error Code ${response.code()}")
                        liveData.value = false
                    }
                }

                override fun onFailure(call: Call<Boolean>, t: Throwable) {
                    Log.d(TAG, t.message ?: "pay 정보 받아오는 중 통신오류")
                    liveData.value = false
                }

            })
        return liveData
    }
}