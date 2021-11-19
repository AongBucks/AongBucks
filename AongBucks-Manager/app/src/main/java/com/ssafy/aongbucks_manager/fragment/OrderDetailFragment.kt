package com.ssafy.aongbucks_manager.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.smartstore.activity.MainActivity
import com.ssafy.smartstore.adapter.OrderDetailListAdapter
import com.ssafy.smartstore.config.ApplicationClass
import com.ssafy.smartstore.databinding.FragmentOrderDetailBinding
import com.ssafy.smartstore.dto.Order
import com.ssafy.smartstore.dto.OrderDetail
import com.ssafy.smartstore.response.OrderDetailResponse
import com.ssafy.smartstore.service.OrderService
import com.ssafy.smartstore.util.CommonUtils
import java.text.SimpleDateFormat
import java.util.*

// 주문상세화면, My탭  - 주문내역 선택시 팝업
private const val TAG = "OrderDetailFragment_싸피"
class OrderDetailFragment : Fragment(){
    private lateinit var orderDetailListAdapter: OrderDetailListAdapter
    private lateinit var mainActivity: MainActivity

    private var orderId = -1

    private lateinit var binding:FragmentOrderDetailBinding
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity.hideBottomNav(true)

        arguments?.let {
            orderId = it.getInt("orderId")
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOrderDetailBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()
    }

    private fun initData(){
        val orderDetails = OrderService().getOrderDetails(orderId)
        orderDetails.observe(
            viewLifecycleOwner,
            { orderDetails ->
                orderDetails.let {
                    orderDetailListAdapter = OrderDetailListAdapter(mainActivity, orderDetails)
                }

                binding.recyclerViewOrderDetailList.apply {
                    val linearLayoutManager = LinearLayoutManager(context)
                    linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
                    layoutManager = linearLayoutManager
                    adapter = orderDetailListAdapter
                    //원래의 목록위치로 돌아오게함
                    adapter!!.stateRestorationPolicy =
                        RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
                }

                setOrderDetailScreen(orderDetails)

                Log.d(TAG, "onViewCreated: $orderDetails")
            }
        )
    }

    // OrderDetail 페이지 화면 구성
    private fun setOrderDetailScreen(orderDetails: List<OrderDetailResponse>){
        val dateFormat = SimpleDateFormat("yyyy.MM.dd HH시 mm분 ss초")
        dateFormat.timeZone = TimeZone.getTimeZone("UTC")

        binding.tvOrderStatus.text = CommonUtils.isOrderCompleted(orderDetails[0])
        binding.tvOrderDate.text = dateFormat.format(orderDetails[0].orderDate)
        var totalPrice = 0
        orderDetails.forEach { totalPrice += it.totalPrice }
        binding.tvTotalPrice.text = "$totalPrice 원"
    }


    override fun onDestroy() {
        super.onDestroy()
        mainActivity.hideBottomNav(false)
    }

    companion
    object {
        @JvmStatic
        fun newInstance(key:String, value:Int) =
            OrderDetailFragment().apply {
                arguments = Bundle().apply {
                    putInt(key, value)
                }
            }
    }
}