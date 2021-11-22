package com.ssafy.aongbucks_user.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ssafy.aongbucks_user.R
import com.ssafy.aongbucks_user.activity.MainActivity
import com.ssafy.aongbucks_user.databinding.FragmentMyPageBinding

class MyPageFragment : Fragment() {
    private lateinit var binding : FragmentMyPageBinding
    private lateinit var mainActivity : MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMyPageBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.logoutBtn.setOnClickListener { mainActivity.logout() }
    }
}