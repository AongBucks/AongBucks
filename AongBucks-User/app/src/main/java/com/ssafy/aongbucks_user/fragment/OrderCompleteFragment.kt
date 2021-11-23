package com.ssafy.aongbucks_user.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.aongbucks_user.R
import com.ssafy.aongbucks_user.activity.MainActivity
import com.ssafy.aongbucks_user.adapter.CartListAdapter
import com.ssafy.aongbucks_user.config.ApplicationClass
import com.ssafy.aongbucks_user.databinding.FragmentCartBinding
import com.ssafy.aongbucks_user.databinding.FragmentOrderCompleteBinding
import com.ssafy.aongbucks_user.model.dto.User
import com.ssafy.aongbucks_user.viewModel.GradeViewModel
import com.ssafy.aongbucks_user.viewModel.MainActivityViewModel
import com.ssafy.aongbucks_user.viewModel.OrderViewModel

private const val TAG = "OrderCompleteFragment_싸피"
class OrderCompleteFragment : Fragment(){

    private val activityViewModel: MainActivityViewModel by activityViewModels()

    private lateinit var mainActivity: MainActivity
    private lateinit var binding: FragmentOrderCompleteBinding

    private lateinit var user: User

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity.hideBottomNav(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_order_complete, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        user = ApplicationClass.sharedPreferencesUtil.getUser()
        binding.fragment = this
        binding.totalDto = activityViewModel.totalCart
        binding.totalPayTextView.text = activityViewModel.currentPayMoney.toString()
    }

    override fun onDestroy() {
        super.onDestroy()
        mainActivity.hideBottomNav(false)
    }
}