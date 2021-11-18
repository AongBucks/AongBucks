package com.ssafy.aongbucks_user.config

import android.Manifest
import android.app.Application
import com.ssafy.aongbucks_user.util.SharedPreferencesUtil

class ApplicationClass : Application() {
    companion object{
        // ipconfig를 통해 ip확인하기
        // 핸드폰으로 접속은 같은 인터넷으로 연결 되어있어야함 (유,무선)
        const val SERVER_URL = "http://121.151.11.143:9999/"
        const val MENU_IMGS_URL = "${SERVER_URL}imgs/menu/"
        const val IMGS_URL = "${SERVER_URL}imgs/"

        lateinit var sharedPreferencesUtil: SharedPreferencesUtil
//        lateinit var retrofit: Retrofit

        // 모든 퍼미션 관련 배열
        val requiredPermissions = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
        )

        // 주문 준비 완료 확인 시간 1분
        const val ORDER_COMPLETED_TIME = 60*1000

    }


    override fun onCreate() {
        super.onCreate()

        //shared preference 초기화
        sharedPreferencesUtil = SharedPreferencesUtil(applicationContext)

//        val okHttpClient = OkHttpClient.Builder()
//            .addInterceptor(AddCookiesInterceptor())
//            .addInterceptor(ReceivedCookiesInterceptor())
//            .connectTimeout(30, TimeUnit.SECONDS).build()

        // 앱이 처음 생성되는 순간, retrofit 인스턴스를 생성
//        retrofit = Retrofit.Builder()
//            .baseUrl(SERVER_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .client(okHttpClient)
//            .build()
    }
}