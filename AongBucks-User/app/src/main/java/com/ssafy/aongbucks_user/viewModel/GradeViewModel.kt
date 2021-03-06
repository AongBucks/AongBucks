package com.ssafy.aongbucks_user.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.aongbucks_user.config.ApplicationClass
import com.ssafy.aongbucks_user.model.RetrofitClient
import com.ssafy.aongbucks_user.model.dto.Grade
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private const val TAG = "GradeViewModel_싸피"
class GradeViewModel : ViewModel() {

    /**
     * 모든 등급 조회
     */
    private val _grades = MutableLiveData<List<Grade>>()
    val grades : LiveData<List<Grade>>
        get() = _grades

    fun getGrades() {
        getAllGrades()
    }

    private fun getAllGrades() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = RetrofitClient.gradeService.getAllGrade()
            if (response.isSuccessful) {
                if (response.body() != null) {
                    _grades.postValue(response.body())
                }
            } else {
                Log.d(TAG, "getAllGrades: ${response.code()}")
            }
        }
    }

    /**
     * 특정 등급의 할인률 조회
     */
    private val _discount = MutableLiveData<Float>()
    val discount : LiveData<Float>
        get() = _discount

    fun initDiscount(id: String) {
        getDiscountById(id)
    }

    private fun getDiscountById(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = RetrofitClient.gradeService.getDiscountGrade(id)
            if (response.isSuccessful) {
                if (response.body() != null) {
                    _discount.postValue(response.body())
                    Log.d(TAG, "getDiscountById: ${_discount}")
                }
            } else {
                _discount.postValue(-1f)
                Log.d(TAG, "getDiscountById: ${response.code()}")
            }
        }
    }
}