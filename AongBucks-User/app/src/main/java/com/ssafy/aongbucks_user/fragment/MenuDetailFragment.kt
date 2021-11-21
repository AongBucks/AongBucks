package com.ssafy.aongbucks_user.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.ssafy.aongbucks_user.R
import com.ssafy.aongbucks_user.activity.MainActivity
import com.ssafy.aongbucks_user.databinding.FragmentMenuDetailBinding
import com.ssafy.aongbucks_user.model.dto.Product
import com.ssafy.aongbucks_user.model.response.MenuDetailWithCommentResponse
import com.ssafy.aongbucks_user.viewModel.ProductViewModel

private const val TAG = "MenuDetailFragment_싸피"
class MenuDetailFragment : Fragment() {
    private lateinit var binding : FragmentMenuDetailBinding
    private lateinit var mainActivity: MainActivity
    private val viewModel : ProductViewModel by viewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMenuDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val productId = arguments?.getInt("productId")!!
        viewModel.getProductWithComments(productId)
        viewModel.productWithComments.observe(viewLifecycleOwner, {
            val menuWithComments = viewModel.productWithComments.value as List<MenuDetailWithCommentResponse>
            val menudetail = menuWithComments[0]
            val menu = Product(productId, menudetail.productName, menudetail.type, menudetail.productPrice, menudetail.productImg)
            Log.d(TAG, "onViewCreated: $menu")
            binding.menu = menu
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        mainActivity.hideBottomNav(false)
    }

}