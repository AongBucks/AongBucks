package com.ssafy.aongbucks_user.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ssafy.aongbucks_user.R
import com.ssafy.aongbucks_user.config.ApplicationClass.Companion.sharedPreferencesUtil
import com.ssafy.aongbucks_user.fragment.JoinFragment
import com.ssafy.aongbucks_user.fragment.LoginFragment

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // 로그인된 상태인지 확인
        var user = sharedPreferencesUtil.getUser()

        if (user.id != "") { // 이미 로그인이 되어 있음
            openFragment(1)
        } else { // 로그인 x
            supportFragmentManager.beginTransaction()
                .replace(R.id.frame_layout_login, LoginFragment())
                .commit()
        }
    }

    fun openFragment(int : Int) {
        val transaction = supportFragmentManager.beginTransaction()
        when(int){
            1 -> {
                val intent = Intent(this, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent)
            }
            2 -> transaction.replace(R.id.frame_layout_login, JoinFragment())
                .addToBackStack(null)

            3 -> transaction.replace(R.id.frame_layout_login, LoginFragment())
                .addToBackStack(null)
        }
        transaction.commit()
    }
}