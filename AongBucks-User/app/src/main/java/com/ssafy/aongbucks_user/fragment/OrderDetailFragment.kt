package com.ssafy.aongbucks_user.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ssafy.aongbucks_user.R
import com.ssafy.aongbucks_user.activity.MainActivity
import com.ssafy.aongbucks_user.databinding.FragmentOrderDetailBinding
import com.ssafy.aongbucks_user.model.response.LatestOrderResponse

private const val TAG = "OrderDetailFragment_싸피"
class OrderDetailFragment : Fragment() {
    private lateinit var binding : FragmentOrderDetailBinding
    private lateinit var mainActivity: MainActivity
    private lateinit var order : LatestOrderResponse

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
        binding = FragmentOrderDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        order = arguments?.getParcelable("order")!!
        binding.order = order

        binding.backBtn.setOnClickListener {
            mainActivity.navController.navigateUp()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mainActivity.hideBottomNav(false)
    }
}