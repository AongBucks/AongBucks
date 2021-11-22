package com.ssafy.aongbucks_manager.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.aongbucks_manager.activity.MainActivity
import com.ssafy.aongbucks_manager.adapter.GradeAdapter
import com.ssafy.aongbucks_manager.databinding.FragmentGradeBinding
import com.ssafy.aongbucks_manager.service.GradeService

// 하단 주문 탭
private const val TAG = "GradeFragment_싸피"
class GradeFragment : Fragment(){

    private lateinit var gradeAdapter: GradeAdapter
    private lateinit var mainActivity: MainActivity
    private lateinit var binding:FragmentGradeBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGradeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()
    }

    private fun initData(){
        val gradeLiveData = GradeService().getGradeLists()
        gradeLiveData.observe(
            viewLifecycleOwner, { list ->
                gradeAdapter = GradeAdapter(mainActivity, this@GradeFragment, list)

                binding.recyclerViewGrade.apply {
                    layoutManager = LinearLayoutManager(mainActivity)
                    adapter = gradeAdapter
                    // 원래의 목록 위치로 돌아오게 함
                    adapter!!.stateRestorationPolicy =
                        RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
                }
            }
        )
    }

    fun onGradeClickListener(view: View) {

    }
}