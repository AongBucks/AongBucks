package com.ssafy.aongbucks_manager.util

interface RetrofitCallback<T> {
    fun onError(t: Throwable)

    fun onSuccess(code: Int, responseData: T)

    fun onFailure(code: Int)
}