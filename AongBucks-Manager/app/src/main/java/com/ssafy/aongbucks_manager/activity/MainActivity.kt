package com.ssafy.aongbucks_manager.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssafy.aongbucks_manager.R
import com.ssafy.aongbucks_manager.adapter.PaneAdapter
import com.ssafy.aongbucks_manager.databinding.ActivityMainBinding
import com.ssafy.aongbucks_manager.dto.PaneMenu
import com.ssafy.aongbucks_manager.fragment.MenuFragment
import com.ssafy.aongbucks_manager.fragment.OrderFragment

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TITLE = 0
        private const val ORDER_MANAGE = 1
        private const val MENU_MANAGE = 2
        private const val GRADE_MANAGE = 3
    }

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        initPaneList()
    }

    private fun initPaneList() {
        val paneList = mutableListOf<PaneMenu>()
        paneList.add(PaneMenu(TITLE, "애옹벅스\n관리자 메뉴", null))
        paneList.add(PaneMenu(ORDER_MANAGE, "주문 관리", R.drawable.round_fact_check_24))
        paneList.add(PaneMenu(MENU_MANAGE, "메뉴 관리", R.drawable.round_free_breakfast_24))
        paneList.add(PaneMenu(GRADE_MANAGE, "등급 관리", R.drawable.round_manage_accounts_24))

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = PaneAdapter(this@MainActivity, paneList)
        }
    }

    fun onPaneClickListener(view: View) {

        when(view.id) {
//            ORDER_MANAGE -> supportFragmentManager.beginTransaction()
//                .replace(R.id.detailContainer, OrderFragment())
//                .commit()
            MENU_MANAGE -> supportFragmentManager.beginTransaction()
                .replace(R.id.detailContainer, MenuFragment())
                .commit()
//            GRADE_MANAGE -> supportFragmentManager.beginTransaction()
//                .replace(R.id.detailContainer, GradeFragment())
//                .commit()
            ORDER_MANAGE -> null
            GRADE_MANAGE -> null
        }

        binding.slidingPaneLayout.open() // 생략하면 어케되지
        Toast.makeText(view.context, "click... ${view.tag}", Toast.LENGTH_SHORT).show()
    }


}