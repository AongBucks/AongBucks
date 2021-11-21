package com.ssafy.aongbucks_manager.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.aongbucks_manager.R
import com.ssafy.aongbucks_manager.activity.MainActivity
import com.ssafy.aongbucks_manager.adapter.OrderDetailListAdapter
import com.ssafy.aongbucks_manager.databinding.FragmentOrderDetailBinding
import com.ssafy.aongbucks_manager.reponse.TotalOrderResponse
import com.ssafy.aongbucks_manager.service.OrderService
import com.ssafy.aongbucks_manager.viewmodel.MainActivityViewModel

// 주문상세화면, My탭  - 주문내역 선택시 팝업
private const val TAG = "OrderDetailFragment_싸피"
class OrderDetailFragment : Fragment(){

    private val activityViewModel: MainActivityViewModel by activityViewModels()

    // TODO: 2021-11-21 뒤로가기 버튼 눌렀을 때도 닫히게 하고 싶은데, mainActivity의 뒤로가기를 막질 못하겠음 ㅜㅜ
    private lateinit var backCallback: OnBackPressedCallback

    private lateinit var orderDetailListAdapter: OrderDetailListAdapter
    private lateinit var mainActivity: MainActivity

    private lateinit var binding: FragmentOrderDetailBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity

        backCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                Log.d(TAG, "handleOnBackPressed: ")
                activityViewModel.detailClose()
            }
        }
        mainActivity.onBackPressedDispatcher.addCallback(this, backCallback)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_order_detail, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fragment = this

        activityViewModel.selectedOrder.observe(
            viewLifecycleOwner, {
                initData(it)
            }
        )
    }

    override fun onDetach() {
        super.onDetach()
        Log.d(TAG, "onDetach: ")
        backCallback.remove()
    }

    private fun initData(order: TotalOrderResponse){
        val orderDetails = OrderService().getOrderDetails(order.orderId)
        orderDetails.observe(
            viewLifecycleOwner,
            { orderDetails ->
                orderDetails.let {
                    orderDetailListAdapter = OrderDetailListAdapter(mainActivity, orderDetails)
                }

                binding.totalOrderDto = order

                binding.recyclerViewOrderDetail.apply {
                    val linearLayoutManager = LinearLayoutManager(context)
                    linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
                    layoutManager = linearLayoutManager
                    adapter = orderDetailListAdapter
                    //원래의 목록위치로 돌아오게함
                    adapter!!.stateRestorationPolicy =
                        RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
                }

                Log.d(TAG, "onViewCreated: $orderDetails")
            }
        )
    }

    fun close(view: View) {
        activityViewModel.detailClose()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG, "onDestroyView: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: ")
    }
}