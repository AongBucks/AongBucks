package com.ssafy.aongbucks_manager.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssafy.aongbucks_manager.R
import com.ssafy.aongbucks_manager.adapter.PaneAdapter
import com.ssafy.aongbucks_manager.databinding.ActivityMainBinding
import com.ssafy.aongbucks_manager.dto.PaneMenu
import com.ssafy.aongbucks_manager.fragment.GradeFragment
import com.ssafy.aongbucks_manager.fragment.MenuFragment
import com.ssafy.aongbucks_manager.fragment.OrderFragment
import com.ssafy.aongbucks_manager.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    companion object {
        const val TITLE = 0
        const val ORDER_MANAGE = 1
        const val MENU_MANAGE = 2
        const val GRADE_MANAGE = 3
    }

    private val activityViewModel: MainActivityViewModel by viewModels()

    lateinit var binding: ActivityMainBinding
    lateinit var paneAdapter: PaneAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        initPaneList()
    }

    private fun initPaneList() {
        paneAdapter = PaneAdapter(this@MainActivity, activityViewModel.items)
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = paneAdapter
        }
    }

    fun onPaneClickListener(view: View) {
        val id = (view.tag as PaneMenu).id

        if (activityViewModel.current == id) return // 클릭 비활성화!

        val transaction = supportFragmentManager.beginTransaction()
        when(id) {
            ORDER_MANAGE -> transaction
                .replace(R.id.contentContainer, OrderFragment())
            MENU_MANAGE -> transaction
                .replace(R.id.contentContainer, MenuFragment())
            GRADE_MANAGE -> transaction
                .replace(R.id.contentContainer, GradeFragment())
        }
        transaction.commit()
        binding.slidingPaneLayout.open() // 생략하면 어케되지

        activityViewModel.changeMenu(id)
        paneAdapter.list = activityViewModel.items
        paneAdapter.notifyDataSetChanged()
    }

}