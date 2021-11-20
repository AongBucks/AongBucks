package com.ssafy.aongbucks_user.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.ssafy.aongbucks_user.adapter.ProductAdapter
import com.ssafy.aongbucks_user.config.ApplicationClass
import com.ssafy.aongbucks_user.databinding.FragmentOrderBinding
import com.ssafy.aongbucks_user.model.dto.Product
import com.ssafy.aongbucks_user.viewModel.ProductViewModel

private const val TAG = "OrderFragment_싸피"
class OrderFragment : Fragment() {
    private lateinit var binding : FragmentOrderBinding
    private val viewModel : ProductViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentOrderBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 전체 메뉴
        showTotalMenu()

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab!!.position) {
                    0 -> {
                        // 전체 메뉴
                        showTotalMenu()
                    }
                    1 -> {
                        // 즐겨찾기 메뉴
                        showFavoriteMenu()
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) { }

            override fun onTabReselected(tab: TabLayout.Tab?) { }
        })
    }

    private fun showTotalMenu() {
        viewModel.getProducts()
        viewModel.products.observe(viewLifecycleOwner, {
            val products = viewModel.products.value

            if (products!!.isEmpty()) {
                binding.noMenuTv.visibility = View.VISIBLE
            } else {
                binding.noMenuTv.visibility = View.INVISIBLE
            }

            initAdapter(products ?: listOf())
        })
    }

    private fun showFavoriteMenu() {
        val user = ApplicationClass.sharedPreferencesUtil.getUser()
        viewModel.getFavoriteProducts(user.id)
        viewModel.favorites.observe(viewLifecycleOwner, {
            val favorites = viewModel.favorites.value

            if (favorites!!.isEmpty()) {
                binding.noMenuTv.visibility = View.VISIBLE
            } else {
                binding.noMenuTv.visibility = View.INVISIBLE
            }

            initAdapter(favorites ?: listOf())
        })
    }

    private fun initAdapter(products : List<Product>) {
        val adapter = ProductAdapter(requireContext(), products)
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext()).apply {
                orientation = LinearLayoutManager.VERTICAL
            }
            this.adapter = adapter
        }
    }

}