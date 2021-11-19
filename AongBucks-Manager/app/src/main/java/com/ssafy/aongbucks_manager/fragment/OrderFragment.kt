package com.ssafy.aongbucks_manager.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.aongbucks_manager.activity.MainActivity
import com.ssafy.aongbucks_manager.adapter.OrderAdapter
import com.ssafy.aongbucks_manager.databinding.FragmentOrderBinding
import com.ssafy.aongbucks_manager.reponse.TotalOrderResponse
import com.ssafy.aongbucks_manager.service.OrderService

// 하단 주문 탭
private const val TAG = "OrderFragment_싸피"
class OrderFragment : Fragment(){
    private lateinit var orderAdapter : OrderAdapter
    private lateinit var mainActivity: MainActivity
    private lateinit var list : List<TotalOrderResponse>

    private lateinit var binding:FragmentOrderBinding
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOrderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAllOrderData()
    }

    private fun initAllOrderData() {
        val orderLiveData = OrderService().getAllOrderInfo()
        orderLiveData.observe(
            viewLifecycleOwner, { list ->
                orderAdapter = OrderAdapter(mainActivity, list)

                binding.recyclerViewOrder.apply {
                    layoutManager = GridLayoutManager(context,4)
                    adapter = orderAdapter
                    adapter!!.stateRestorationPolicy =
                        RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY // 원래의 목록 위치로 돌아오게 함
                }


            }
        )
    }
}