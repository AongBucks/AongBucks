package com.ssafy.aongbucks_user.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.ssafy.aongbucks_user.R
import com.ssafy.aongbucks_user.activity.LoginActivity
import com.ssafy.aongbucks_user.config.ApplicationClass.Companion.sharedPreferencesUtil
import com.ssafy.aongbucks_user.databinding.FragmentLoginBinding
import com.ssafy.aongbucks_user.dto.User
import com.ssafy.aongbucks_user.viewModel.UserViewModel

private const val TAG = "LoginFragment_싸피"
class LoginFragment : Fragment() {
    private lateinit var loginActivity: LoginActivity
    private lateinit var binding : FragmentLoginBinding
    private val viewModel : UserViewModel by viewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        loginActivity = context as LoginActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(this)
            .load(R.drawable.aongbucks)
            .circleCrop()
            .into(binding.logo)

        binding.loginBtn.setOnClickListener { login() }

        binding.joinBtn.setOnClickListener { moveToJoin() }

    }

    private fun login() {
        val user = User(binding.id.text.toString(), binding.pass.text.toString())
        viewModel.userLogin(user)
        viewModel.user.observe(viewLifecycleOwner, Observer {
            if (it.id != null) {
                Log.d(TAG, "login success: ${viewModel.user.value}")
                sharedPreferencesUtil.addUser(it)
                loginActivity.openFragment(1)
            } else {
                Log.d(TAG, "login fail: ${viewModel.user.value}")
                Toast.makeText(context, "ID 또는 패스워드를 확인해주세요.", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun moveToJoin() {
        loginActivity.openFragment(2)
    }
}