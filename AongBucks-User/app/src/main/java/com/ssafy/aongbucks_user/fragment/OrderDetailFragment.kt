package com.ssafy.aongbucks_user.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
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
import com.ssafy.aongbucks_user.adapter.OrderDetailAdapter
import com.ssafy.aongbucks_user.databinding.FragmentOrderDetailBinding
import com.ssafy.aongbucks_user.model.dto.OrderDetail
import com.ssafy.aongbucks_user.model.response.LatestOrderResponse
import com.ssafy.aongbucks_user.model.response.OrderDetailResponse
import com.ssafy.aongbucks_user.viewModel.OrderViewModel

private const val TAG = "OrderDetailFragment_싸피"
class OrderDetailFragment : Fragment() {
    private lateinit var binding : FragmentOrderDetailBinding
    private lateinit var mainActivity: MainActivity
    private lateinit var order : LatestOrderResponse

    private val oViewModel : OrderViewModel by viewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
        mainActivity.hideBottomNav(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentOrderDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        order = arguments?.getParcelable("order")!!
        binding.order = order

        Log.d(TAG, "onViewCreated: $order")

        getData()

        binding.backBtn.setOnClickListener {
            mainActivity.navController.navigateUp()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mainActivity.hideBottomNav(false)
    }

    private fun getData() {
        oViewModel.getOrderDetail(order.orderId)
        oViewModel.orderDetails.observe(viewLifecycleOwner, { details ->
            initAdapter(details)
        })
    }

    private fun initAdapter(details : List<OrderDetailResponse>) {
        binding.orderDetailRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = OrderDetailAdapter(context, details).apply {
                stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
                setItemClickListener(object : OrderDetailAdapter.ItemClickListener {
                    override fun onClick(View: View, position: Int, productId: Int) {
                        val bundle = bundleOf("productId" to productId)
                        mainActivity.navController.navigate(R.id.action_orderdetail_to_menudetail, bundle)
                    }
                })
            }
        }
    }
}