package com.ssafy.aongbucks_manager.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.ssafy.aongbucks_manager.R
import com.ssafy.aongbucks_manager.activity.MainActivity
import com.ssafy.aongbucks_manager.dto.PaneMenu

private const val TAG = "MainActivityViewModel_싸피"
class MainActivityViewModel: ViewModel() {

    var current: Int = MainActivity.MENU_MANAGE
        private set

    var items = mutableListOf<PaneMenu>()
        private set

    init {
        items = mutableListOf(
            PaneMenu(MainActivity.TITLE, "애옹벅스\n관리자 메뉴", null, false),
            PaneMenu(MainActivity.ORDER_MANAGE, "주문 관리", R.drawable.round_fact_check_24, false),
            PaneMenu(MainActivity.MENU_MANAGE, "메뉴 관리", R.drawable.round_free_breakfast_24, true),
            PaneMenu(MainActivity.GRADE_MANAGE, "등급 관리", R.drawable.round_manage_accounts_24, false)
        )
    }

    fun changeMenu(next: Int) {
        items[next].isSelected = true
        items[current].isSelected = false
        current = next
    }
}