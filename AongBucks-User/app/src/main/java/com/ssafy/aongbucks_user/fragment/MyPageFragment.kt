package com.ssafy.aongbucks_user.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.aongbucks_user.R
import com.ssafy.aongbucks_user.activity.MainActivity
import com.ssafy.aongbucks_user.adapter.OrderAdapter
import com.ssafy.aongbucks_user.config.ApplicationClass
import com.ssafy.aongbucks_user.databinding.FragmentMyPageBinding
import com.ssafy.aongbucks_user.model.api.OrderApiService
import com.ssafy.aongbucks_user.model.dto.Grade
import com.ssafy.aongbucks_user.model.response.LatestOrderResponse
import com.ssafy.aongbucks_user.viewModel.OrderViewModel
import com.ssafy.aongbucks_user.viewModel.UserViewModel

class MyPageFragment : Fragment() {
    private lateinit var binding : FragmentMyPageBinding
    private lateinit var mainActivity : MainActivity
    private lateinit var userId : String
    private lateinit var grade : Grade

    private val uViewModel : UserViewModel by viewModels()
    private val oViewModel : OrderViewModel by viewModels()


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

        userId = ApplicationClass.sharedPreferencesUtil.getUser().id
        getUserData()
        getOrderData()

        binding.logoutBtn.setOnClickListener { mainActivity.logout() }

        binding.membershipBtn.setOnClickListener {
            val bundle = bundleOf("userGrade" to grade)
            mainActivity.navController.navigate(R.id.action_mypage_to_grade, bundle)
        }
    }

    private fun getUserData() {
        uViewModel.userInfo(userId)
        uViewModel.userInfo.observe(viewLifecycleOwner, { info ->
            binding.user = info.user
            binding.grade = info.grade
            grade = info.grade
        })
    }

    private fun getOrderData() {
        oViewModel.getLastMonthOrder(userId)
        oViewModel.latestOrders.observe(viewLifecycleOwner, { orderList ->
            val orderAdapter = OrderAdapter(requireContext(), orderList).apply {
                stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
                setItemClickListener(object : OrderAdapter.ItemClickListener {
                    override fun onClick(View: View, position: Int, order: LatestOrderResponse) {
                        // 주문 상세 화면으로 넘어가기
                        val bundle = bundleOf("order" to order)
                        mainActivity.navController.navigate(R.id.action_mypage_to_orderdetail, bundle)
                    }
                })
            }
            binding.orderRecyclerView.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = orderAdapter
            }
        })
    }
}