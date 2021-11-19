package com.ssafy.aongbucks_user.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.ssafy.aongbucks_user.R
import com.ssafy.aongbucks_user.activity.LoginActivity
import com.ssafy.aongbucks_user.databinding.FragmentJoinBinding
import com.ssafy.aongbucks_user.dto.User
import com.ssafy.aongbucks_user.viewModel.UserViewModel

private const val TAG = "JoinFragment_싸피"
class JoinFragment : Fragment() {
    private lateinit var loginActivity : LoginActivity
    private lateinit var binding : FragmentJoinBinding
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
        binding = FragmentJoinBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(this)
            .load(R.drawable.aongbucks)
            .circleCrop()
            .into(binding.logo)

        binding.joinBtn.setOnClickListener { join() }
    }

    private fun join() {
        val id = binding.id.text.toString()
        val name = binding.name.text.toString()
        val pass = binding.pass.text.toString()

        // 빈 칸이 없는지 확인해야함
        // 비어있으면 다이얼로그로 알려주자

        // 중복 아이디인지 확인
        viewModel.userDuplicated(id)
        viewModel.isUsedId.observe(viewLifecycleOwner, Observer { isUsed ->
            if (!isUsed) {
                // 중복 아이디가 아니면 사용자 추가
                Log.d(TAG, "UserId is not used")
                val user = User(id, name, pass, 0, 1)
                viewModel.userJoin(user)
                viewModel.joinCompleted.observe(viewLifecycleOwner, Observer { success ->
                    if (success) {
                        // 회원가입 성공하면 다이얼로그 띄우고 로그인 화면으로 이동
                        Log.d(TAG, "join success")
                        loginActivity.openFragment(3)
                    } else {
                        // 회원가입 실패하면....
                        Log.d(TAG, "join failed")
                    }
                })
            } else {
                // 중복 아이디이면 다이얼로그 띄우고 종료
                Log.d(TAG, "UserId is used")
                Toast.makeText(context, "이미 사용중인 아이디입니다.", Toast.LENGTH_SHORT).show()
            }
        })
    }
}