package com.ssafy.aongbucks_user.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.ssafy.aongbucks_user.R
import com.ssafy.aongbucks_user.activity.LoginActivity
import com.ssafy.aongbucks_user.config.ApplicationClass.Companion.sharedPreferencesUtil
import com.ssafy.aongbucks_user.databinding.FragmentLoginBinding
import com.ssafy.aongbucks_user.model.dto.User
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
    ): View {
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
        val id = binding.id.text.toString()
        val pass = binding.pass.text.toString()

        if (id.isEmpty() || pass.isEmpty()) {
            Toast.makeText(context, R.string.login_exits_empty, Toast.LENGTH_SHORT).show()
            return
        }

        val user = User(binding.id.text.toString(), binding.pass.text.toString())
        viewModel.userLogin(user)
        viewModel.user.observe(viewLifecycleOwner, {
            // 로그인 성공
            Toast.makeText(context, R.string.login_completed, Toast.LENGTH_SHORT).show()
            sharedPreferencesUtil.addUser(it)
            loginActivity.openFragment(1)
        })
        
        viewModel.loginFailToast.observe(viewLifecycleOwner, {
            // 로그인 실패
            Toast.makeText(context, viewModel.loginFailToast.value, Toast.LENGTH_SHORT).show()
        })
    }

    private fun moveToJoin() {
        loginActivity.openFragment(2)
    }
}