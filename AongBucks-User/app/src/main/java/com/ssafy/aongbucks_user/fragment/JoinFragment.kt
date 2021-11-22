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
import com.ssafy.aongbucks_user.model.dto.User
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
        if (id.isEmpty() || name.isEmpty() || pass.isEmpty()) {
            Toast.makeText(context, R.string.join_exits_empty, Toast.LENGTH_SHORT).show()
            return
        }

        // 중복 아이디인지 확인
        viewModel.userDuplicated(id)
        viewModel.isUsedId.observe(viewLifecycleOwner, { isUsed ->
            if (!isUsed) {
                // 중복 아이디가 아니므로 회원가입 고고
                val user = User(id, name, pass, 0, 1)
                viewModel.userJoin(user)
            }
        })

        viewModel.isUsedIdToast.observe(viewLifecycleOwner, {
            // 중복 아이디이면 다이얼로그 띄우고 종료
            Log.d(TAG, "UserId is used")
            Toast.makeText(context, viewModel.isUsedIdToast.value, Toast.LENGTH_SHORT).show()
        })

        viewModel.joinCompleted.observe(viewLifecycleOwner, {
            // 회원가입 성공하면 다이얼로그 띄우고 확인 누르면 로그인 화면으로 이동
            Log.d(TAG, "join success")
            Toast.makeText(context, R.string.join_completed, Toast.LENGTH_SHORT).show()
            loginActivity.openFragment(3)
        })

        viewModel.joinFailToast.observe(viewLifecycleOwner, {
            // 회원가입 실패하면 다이얼로그 띄우기
            Log.d(TAG, "join failed")
            Toast.makeText(context, viewModel.joinFailToast.value, Toast.LENGTH_SHORT).show()
        })
    }

}