package com.ssafy.aongbucks_user.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.aongbucks_user.model.RetrofitClient
import com.ssafy.aongbucks_user.model.dto.Pay
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private const val TAG = "PayViewModel_싸피"
class PayViewModel: ViewModel() {

    /**
     * user pay 정보
     */
    private val _userPay = MutableLiveData<Pay>()
    val userPay: LiveData<Pay?> // user pay info
        get() = _userPay


    fun initPayInfo(userId: String) {
        selectById(userId)
    }

    fun plusMoney(price: Int) {
        _userPay.value?.price = _userPay.value?.price?.plus(price)!!
        val temp = _userPay.value
        _userPay.postValue(temp!!)
        updatePrice(_userPay.value!!)
    }

    private fun selectById(userId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = RetrofitClient.payService.getUserPay(userId)
            if (response.isSuccessful) {
                val res = response.body()
                Log.d(TAG, "selectById: ${res}")
                if (response.code() == 200) {
                    if (res != null) {
                        _userPay.postValue(res!!)
                        Log.d(TAG, "selectById: success")
                    }
                }
            } else {
                Log.d(TAG, "selectById: ${response.code()}")
            }
        }
    }

    private fun updatePrice(pay: Pay) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = RetrofitClient.payService.patchUserPrice(pay)
            if (response.isSuccessful) {
                val res = response.body()
                Log.d(TAG, "updatePrice: ${res}")
                if (response.code() == 200) {
                    if (res != null) {
                        Log.d(TAG, "updatePrice: success")
                    }
                }
            } else {
                Log.d(TAG, "updatePrice: ${response.code()}")
            }
        }
    }

    /**
     * user pay 가입여부
     */
    private val _isJoined = MutableLiveData<Boolean>(false)
    val isJoined : LiveData<Boolean>
        get() = _isJoined

    fun initUserJoin(userId: String) {
        isJoinById(userId)
    }

    fun joinUser(userId: String) {
        postUser(userId)
    }

    private fun isJoinById(userId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = RetrofitClient.payService.getUserIsJoined(userId)
            if (response.isSuccessful) {
                val res = response.body()
                Log.d(TAG, "isJoinById: ${res}")
                if (response.code() == 200) {
                    if (res != null) {
                        Log.d(TAG, "isJoinById: success")
                        _isJoined.postValue(res!!)
                    }
                }
            } else {
                Log.d(TAG, "selectUserIsJoin: ${response.code()}")
            }
        }
    }

    private fun postUser(userId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = RetrofitClient.payService.postUserPay(userId)
            if (response.isSuccessful) {
                val res = response.body()
                Log.d(TAG, "joinPay: ${res}")
                if (response.code() == 200) {
                    Log.d(TAG, "joinPay: success")
                    _isJoined.postValue(true) // 회원가입처리!
                }
            } else {
                Log.d(TAG, "joinPay: ${response.code()}")
            }
        }
    }
}