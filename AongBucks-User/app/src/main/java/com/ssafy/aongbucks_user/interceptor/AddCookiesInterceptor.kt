package com.ssafy.aongbucks_user.interceptor

import android.util.Log
import com.ssafy.aongbucks_user.config.ApplicationClass
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

private const val TAG = "AddCookiesInter_싸피"
class AddCookiesInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response? {
        val builder: Request.Builder = chain.request().newBuilder()

        // cookie 가져오기
        val getCookies = ApplicationClass.sharedPreferencesUtil.getUserCookie()
        for (cookie in getCookies!!) {
            builder.addHeader("Cookie", cookie)
            Log.v(
                TAG,
                "Adding Header: $cookie"
            )
        }
        return chain.proceed(builder.build())
    }
}