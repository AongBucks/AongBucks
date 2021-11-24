package com.ssafy.aongbucks_manager.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.ssafy.aongbucks_manager.R
import com.ssafy.aongbucks_manager.databinding.ActivityLoginBinding

private const val TAG = "LoginActivity_싸피"
class LoginActivity : AppCompatActivity() {
    lateinit var binding:ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        binding.button.setOnClickListener {
            checkcode()
        }
        binding.codeEditText.setOnEditorActionListener { _, id, _ ->
            if (id == EditorInfo.IME_ACTION_SEND) {
                checkcode()
                true
            } else {
                false
            }
        }
    }

    fun checkcode() {
        Log.d(TAG, "checkcode: ${binding.codeEditText.text}")
        if (binding.codeEditText.text.toString().equals("0000")) {
            startActivity(Intent(this, MainActivity::class.java))
            Toast.makeText(this, "인증되었습니다.", Toast.LENGTH_SHORT).show()
        }
        else {
            Toast.makeText(this, "관리자 코드를 확인해주세요.", Toast.LENGTH_SHORT).show()
        }
    }
}