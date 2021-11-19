package com.ssafy.aongbucks_manager.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.aongbucks_manager.activity.MainActivity
import com.ssafy.aongbucks_manager.adapter.OrderAdapter
import com.ssafy.aongbucks_manager.databinding.FragmentOrderBinding
import com.ssafy.aongbucks_manager.reponse.TotalOrderResponse
import com.ssafy.aongbucks_manager.service.OrderService
import com.ssafy.aongbucks_manager.viewmodel.MainActivityViewModel

// 하단 주문 탭
private const val TAG = "OrderFragment_싸피"
class OrderFragment : Fragment(){

    private val activityViewModel: MainActivityViewModel by activityViewModels()

    private lateinit var orderAdapter : OrderAdapter
    private lateinit var mainActivity: MainActivity

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

        activityViewModel.isDetailOpen.observe(viewLifecycleOwner) { isOpen ->
            binding.detailContainer.visibility = if (isOpen) View.VISIBLE else View.GONE
        }
    }

    private fun initAllOrderData() {
        val orderLiveData = OrderService().getAllOrderInfo()
        orderLiveData.observe(
            viewLifecycleOwner, { list ->
                orderAdapter = OrderAdapter(mainActivity, this@OrderFragment, list)

                binding.recyclerViewOrder.apply {
                    layoutManager = LinearLayoutManager(mainActivity)
                    adapter = orderAdapter
                    adapter!!.stateRestorationPolicy =
                        RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY // 원래의 목록 위치로 돌아오게 함
                }
            }
        )
    }

    fun onOrderClickListener(view: View) {
        Log.d(TAG, "onOrderClickListener: ${view.tag}")
        activityViewModel.detailOpen((view.tag as TotalOrderResponse))
    }

    // TODO: 2021-11-20 detail 에 table정보도 표시하기 
    // TODO: 2021-11-20 detail 에서 버튼 누르면 detail fragment gone
    // TODO: 2021-11-20 detail 에서 '제조완료'버튼을 누르면 '제조완료'표시하고, 리스트 배경색 바꾸기
    // TODO: 2021-11-20 detail databinding 적용하기
    // TODO: 2021-11-20 관리자 코드 로그인
    
}