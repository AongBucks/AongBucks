package com.ssafy.aongbucks_manager.fragment

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.aongbucks_manager.R
import com.ssafy.aongbucks_manager.activity.MainActivity
import com.ssafy.aongbucks_manager.adapter.GradeAdapter
import com.ssafy.aongbucks_manager.databinding.DialogGradeInfoBinding
import com.ssafy.aongbucks_manager.databinding.FragmentGradeBinding
import com.ssafy.aongbucks_manager.dto.Grade
import com.ssafy.aongbucks_manager.reponse.GradeResponse
import com.ssafy.aongbucks_manager.service.GradeService

// 하단 주문 탭
private const val TAG = "GradeFragment_싸피"
class GradeFragment : Fragment(){

    private lateinit var gradeAdapter: GradeAdapter
    private lateinit var mainActivity: MainActivity
    private lateinit var binding:FragmentGradeBinding
    private lateinit var dialogBinding: DialogGradeInfoBinding

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

        binding.gradeSwipeRefreshLayout.setOnRefreshListener {
            initData()
            binding.gradeSwipeRefreshLayout.isRefreshing = false
            Toast.makeText(mainActivity, "등급 목록이 갱신되었습니다.", Toast.LENGTH_SHORT).show()
        }
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
        val dialog = Dialog(mainActivity)
        dialogBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.dialog_grade_info, null, false)
        dialogBinding.gradeDto = view.tag as GradeResponse
//        dialogBinding.cancelButton.setOnClickListener(dialog.dismiss())
        dialog.apply {
            setContentView(dialogBinding.root)
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialogBinding.cancelButton.setOnClickListener{
                Log.d(TAG, "onGradeClickListener: ")
                dismiss()
            }
            dialogBinding.saveButton.setOnClickListener {
                Log.d(TAG, "onSaveClickListener: ")
                var dto = view.tag as GradeResponse
                dto.discount = dialogBinding.gradeDiscountEditText.text.toString().toFloat()
                dto.standard = dialogBinding.gradeStandardEditText.text.toString().toInt()
                GradeService().updateGrade(dto)

                Toast.makeText(mainActivity, "등급 정보가 변경되었습니다.", Toast.LENGTH_SHORT).show()
                dismiss()
                initData() // 전체 갱신
            }
            show()

        }
    }
}