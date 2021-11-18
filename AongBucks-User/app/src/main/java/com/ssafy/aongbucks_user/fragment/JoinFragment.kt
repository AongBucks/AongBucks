package com.ssafy.aongbucks_user.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ssafy.aongbucks_user.R
import com.ssafy.aongbucks_user.activity.LoginActivity
import com.ssafy.aongbucks_user.databinding.FragmentJoinBinding

class JoinFragment : Fragment() {
    private lateinit var loginActivity : LoginActivity
    private lateinit var binding : FragmentJoinBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentJoinBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    fun join() {
        loginActivity.openFragment(1)
    }
}