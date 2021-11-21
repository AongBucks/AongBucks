package com.ssafy.aongbucks_manager.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.Slide
import androidx.transition.Transition
import androidx.transition.TransitionManager
import com.ssafy.aongbucks_manager.R
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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_order, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAllOrderData()

        val transition: Transition = Slide(Gravity.RIGHT)
        transition.duration = 200
        transition.addTarget(R.id.detailContainer)

        activityViewModel.isDetailOpen.observe(viewLifecycleOwner) { isOpen ->
            Log.d(TAG, "onViewCreated: detail status : ${activityViewModel.isDetailOpen.value}")
            TransitionManager.beginDelayedTransition(binding.layout, transition);
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
                    // 원래의 목록 위치로 돌아오게 함
                    adapter!!.stateRestorationPolicy =
                        RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
                }

                // TODO: 2021-11-21 이게 맞나..........?
                activityViewModel.changeStatePosition.observe(viewLifecycleOwner) {
                    Log.d(TAG, "onViewCreated: selectedOrder observe")
                    orderAdapter.apply {
                        val p = activityViewModel.changeStatePosition.value
                        if (p != null && p > -1) {
                            list[p].orderCompleted = 'Y'
                            notifyItemChanged(p)
                            Log.d(TAG, "initAllOrderData: ${p} change!")
                        }
                    }
                }
            }
        )
    }

    fun onOrderClickListener(view: View) {
        Log.d(TAG, "onOrderClickListener: ${view.tag}")
        activityViewModel.detailOpen((view.tag as TotalOrderResponse))
    }

    // TODO: 2021-11-20 detail databinding 적용하기
    // TODO: 2021-11-20 끝을 드래그하면 새로고침되게하면 ㄱㅊ을 것 같은데?! 
    
}