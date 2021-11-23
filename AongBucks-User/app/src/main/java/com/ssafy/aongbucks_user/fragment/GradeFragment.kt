package com.ssafy.aongbucks_user.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.aongbucks_user.R
import com.ssafy.aongbucks_user.activity.MainActivity
import com.ssafy.aongbucks_user.adapter.GradeAdapter
import com.ssafy.aongbucks_user.databinding.FragmentGradeBinding
import com.ssafy.aongbucks_user.model.dto.Grade
import com.ssafy.aongbucks_user.viewModel.GradeViewModel

private const val TAG = "GradeFragment_싸피"
class GradeFragment : Fragment() {
    private lateinit var mainActivity : MainActivity
    private lateinit var binding : FragmentGradeBinding
    private val gViewModel : GradeViewModel by viewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
        mainActivity.hideBottomNav(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentGradeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d(TAG, "onViewCreated: $arguments")

        binding.userGrade = arguments?.getParcelable("userGrade")!!

        getGrades()

        binding.backBtn.setOnClickListener {
            mainActivity.navController.navigateUp()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mainActivity.hideBottomNav(false)
    }

    private fun getGrades() {
        gViewModel.getGrades()
        gViewModel.grades.observe(viewLifecycleOwner, { grades ->
            Log.d(TAG, "getGrades: $grades")
            initAdapter(grades)
        })
    }

    private fun initAdapter(grades : List<Grade>) {
        binding.gradeRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = GradeAdapter(context, grades).apply {
                stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
            }
        }
    }
}