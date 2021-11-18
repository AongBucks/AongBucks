package com.ssafy.aongbucks_manager.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ssafy.aongbucks_manager.R
import com.ssafy.aongbucks_manager.fragment.OrderFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // fragment
        // 가장 첫 화면은 메뉴 목록 확인 Fragment로 지정
        supportFragmentManager.beginTransaction()
            .replace(R.id.mainFrameLayout, OrderFragment())
            .commit()
    }
}