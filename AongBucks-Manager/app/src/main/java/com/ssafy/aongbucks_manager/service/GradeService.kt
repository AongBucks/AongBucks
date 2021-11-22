package com.ssafy.aongbucks_manager.service

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ssafy.aongbucks_manager.reponse.GradeResponse
import com.ssafy.aongbucks_manager.util.RetrofitUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "GradeService_싸피"
class GradeService {
    fun getGradeLists() : LiveData<List<GradeResponse>> {
        val responseLiveData: MutableLiveData<List<GradeResponse>> = MutableLiveData()
        val response: Call<List<GradeResponse>> = RetrofitUtil.gradeService.selectAll()

        response.enqueue(object : Callback<List<GradeResponse>> {
            override fun onResponse(
                call: Call<List<GradeResponse>>,
                response: Response<List<GradeResponse>>
            ) {
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

            override fun onFailure(call: Call<List<GradeResponse>>, t: Throwable) {
                Log.d(TAG, t.message ?: "등급 정보 받아오는 중 통신오류")
            }
        })

        return responseLiveData
    }

    fun updateGrade(grade: GradeResponse){
        val response: Call<Boolean> = RetrofitUtil.gradeService.updateGradeInfo(grade)

        response.enqueue(object : Callback<Boolean> {
            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                val res = response.body()
                if(response.code() == 200){
                    Log.d(TAG, "onResponse: $res")
                } else {
                    Log.d(TAG, "onResponse: Error Code ${response.code()}")
                }
            }

            override fun onFailure(call: Call<Boolean>, t: Throwable) {
                Log.d(TAG, t.message ?: "등급 정보 갱신 중 통신오류")
            }

        })
    }

}