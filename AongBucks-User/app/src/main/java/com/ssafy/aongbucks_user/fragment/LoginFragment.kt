package com.ssafy.aongbucks_user.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.ssafy.aongbucks_user.R
import com.ssafy.aongbucks_user.activity.LoginActivity
import com.ssafy.aongbucks_user.config.ApplicationClass
import com.ssafy.aongbucks_user.config.ApplicationClass.Companion.sharedPreferencesUtil
import com.ssafy.aongbucks_user.databinding.FragmentLoginBinding
import com.ssafy.aongbucks_user.dto.User
import com.ssafy.aongbucks_user.service.UserApi
import com.ssafy.aongbucks_user.viewModel.UserViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

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
//        CoroutineScope(Dispatchers.IO).launch {
//            try {
//                Toast.makeText(context, "login ", Toast.LENGTH_SHORT).show()
//
//                var user = User(binding.id.toString(), binding.pass.toString())
//                user = UserApi.userService.login(user)
//
//
//                Toast.makeText(context, "login 2", Toast.LENGTH_SHORT).show()
//
//                if (user.id != null) {
//                    Log.d(TAG, "login: userid 있음")
//                    ApplicationClass.sharedPreferencesUtil.addUser(user)
//                    loginActivity.openFragment(1)
//                } else {
//                    Toast.makeText(context, "ID 또는 패스워드를 확인해주세요.", Toast.LENGTH_SHORT).show()
//                }
//            } catch (e : Exception) {
////                Toast.makeText(context, "로그인 오류", Toast.LENGTH_SHORT).show()
//            }
//        }

//        var user = User(binding.id.toString(), binding.pass.toString())
//        viewModel.userLogin(user)
//        viewModel.user.observe(viewLifecycleOwner, Observer {
////            Toast.makeText()
//            if (it != null) {
//                sharedPreferencesUtil.addUser(it)
                loginActivity.openFragment(1)
//            } else {
//                Toast.makeText(context, "ID 또는 패스워드를 확인해주세요.", Toast.LENGTH_SHORT).show()
//            }
//        })
    }

    private fun moveToJoin() {
        Toast.makeText(context, "moveToJoin", Toast.LENGTH_SHORT).show()
        loginActivity.openFragment(2)
    }
}