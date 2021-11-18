package com.ssafy.aongbucks_user.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.ssafy.aongbucks_user.R
import com.ssafy.aongbucks_user.activity.LoginActivity
import com.ssafy.aongbucks_user.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    private lateinit var loginActivity: LoginActivity
    private lateinit var binding : FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    fun login() {
        Toast.makeText(context, "login", Toast.LENGTH_SHORT).show()

    }

    fun moveToJoin() {
        Toast.makeText(context, "moveToJoin", Toast.LENGTH_SHORT).show()
        loginActivity.openFragment(2)
    }
}