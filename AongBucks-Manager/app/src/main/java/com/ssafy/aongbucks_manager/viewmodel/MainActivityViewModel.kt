package com.ssafy.aongbucks_manager.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ssafy.aongbucks_manager.R
import com.ssafy.aongbucks_manager.activity.MainActivity
import com.ssafy.aongbucks_manager.dto.PaneMenu
import com.ssafy.aongbucks_manager.reponse.TotalOrderResponse

private const val TAG = "MainActivityViewModel_싸피"
class MainActivityViewModel: ViewModel() {

    var current: Int = MainActivity.ORDER_MANAGE
        private set

    var isDetailOpen = MutableLiveData<Boolean>(false)
        private set

    var selectedOrder = MutableLiveData<TotalOrderResponse>()
        private set

    var items = mutableListOf<PaneMenu>()
        private set

    var changeStatePosition = MutableLiveData(-1) // 제조 상태가 변경되면 position 기록
        private set

    init {
        items = mutableListOf(
            PaneMenu(MainActivity.TITLE, "애옹벅스 관리", null, false),
            PaneMenu(MainActivity.ORDER_MANAGE, "주문 관리", R.drawable.round_fact_check_24, true),
            PaneMenu(MainActivity.MENU_MANAGE, "메뉴 관리", R.drawable.round_free_breakfast_24, false),
            PaneMenu(MainActivity.GRADE_MANAGE, "등급 관리", R.drawable.round_manage_accounts_24, false)
        )
    }

    fun changeMenu(next: Int) {
        if (isDetailOpen.value == true && current == MainActivity.ORDER_MANAGE) {
            isDetailOpen.value = false
        }

        items[next].isSelected = true
        items[current].isSelected = false
        current = next
    }

    fun detailOpen(order: TotalOrderResponse) {
        selectedOrder.value = order
        isDetailOpen.value = true
    }

    fun detailClose() {
        isDetailOpen.value = false
    }

    fun completeSelectedState() {
        var tempOrder = selectedOrder.value ?: return
        tempOrder.orderCompleted = 'Y'

        selectedOrder.value = tempOrder
        changeStatePosition.value = tempOrder.position
    }
}