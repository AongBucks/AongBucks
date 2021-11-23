package com.ssafy.aongbucks_user.fragment

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.ssafy.aongbucks_user.R
import com.ssafy.aongbucks_user.activity.MainActivity
import com.ssafy.aongbucks_user.config.ApplicationClass
import com.ssafy.aongbucks_user.databinding.FragmentCartBinding
import com.ssafy.aongbucks_user.databinding.FragmentPayBinding
import com.ssafy.aongbucks_user.model.dto.User
import com.ssafy.aongbucks_user.viewModel.MainActivityViewModel
import com.ssafy.aongbucks_user.viewModel.PayViewModel

class CartFragment : Fragment(){

    private val activityViewModel: MainActivityViewModel by activityViewModels()

    private lateinit var mainActivity: MainActivity
    private lateinit var binding: FragmentCartBinding

    private lateinit var user: User

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cart, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        user = ApplicationClass.sharedPreferencesUtil.getUser()
        binding.fragment = this

    }


    override fun onDestroy() {
        super.onDestroy()
        mainActivity.hideBottomNav(false)
    }
}