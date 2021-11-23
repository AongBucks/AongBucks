package com.ssafy.aongbucks_user.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.ssafy.aongbucks_user.R
import com.ssafy.aongbucks_user.activity.MainActivity
import com.ssafy.aongbucks_user.adapter.FavoriteAdapter
import com.ssafy.aongbucks_user.adapter.ProductAdapter
import com.ssafy.aongbucks_user.config.ApplicationClass
import com.ssafy.aongbucks_user.databinding.FragmentOrderBinding
import com.ssafy.aongbucks_user.model.dto.Product
import com.ssafy.aongbucks_user.model.dto.User
import com.ssafy.aongbucks_user.viewModel.FavoriteViewModel
import com.ssafy.aongbucks_user.viewModel.ProductViewModel

private const val TAG = "OrderFragment_싸피"
class OrderFragment : Fragment() {
    private lateinit var binding : FragmentOrderBinding
    private lateinit var mainActivity : MainActivity
    private lateinit var user : User

    private val pViewModel : ProductViewModel by viewModels()
    private val fViewModel : FavoriteViewModel by viewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

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

        user = ApplicationClass.sharedPreferencesUtil.getUser()

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
        pViewModel.getProducts()
        pViewModel.products.observe(viewLifecycleOwner, {
            val products = pViewModel.products.value

            if (products!!.isEmpty()) {
                binding.noMenu.visibility = View.VISIBLE
            } else {
                binding.noMenu.visibility = View.INVISIBLE
            }

            initProductAdapter(products ?: listOf())
        })
    }

    private fun showFavoriteMenu() {
        pViewModel.getFavoriteProducts(user.id)
        pViewModel.favorites.observe(viewLifecycleOwner, {
            val favorites = pViewModel.favorites.value

            if (favorites!!.isEmpty()) {
                binding.noMenu.visibility = View.VISIBLE
            } else {
                binding.noMenu.visibility = View.INVISIBLE
            }

            initFavoriteAdapter(favorites ?: listOf())
        })
    }

    private fun initProductAdapter(products : List<Product>) {
        val adapter = ProductAdapter(requireContext(), products).apply {
            stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
            setItemClickListener(object : ProductAdapter.ItemClickListener {
                override fun onClick(View: View, position: Int, productId: Int) {
//                    mainActivity.hideBottomNav(true)
                    var bundle = bundleOf("productId" to productId)
                    mainActivity.navController.navigate(R.id.action_order_to_menuDetail, bundle)
                }
            })
        }

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            this.adapter = adapter
        }
    }
    
    private fun initFavoriteAdapter(products : List<Product>) {
        val adapter = FavoriteAdapter(requireContext(), products.toMutableList()).apply {
            stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
            setItemClickListener(object : FavoriteAdapter.ItemClickListener {
                override fun onClick(View: View, position: Int, productId: Int) {
//                    mainActivity.hideBottomNav(true)
                    var bundle = bundleOf("productId" to productId)
                    mainActivity.navController.navigate(R.id.action_order_to_menuDetail, bundle)
                }
            })
            setBtnClickListener(object : FavoriteAdapter.ButtonClickListener {
                override fun onFavorite(view: View, position: Int, productId: Int) {
                    // 즐겨찾기 목록에서 제거
                    // 서버에서도 제거해야함
                    fViewModel.delFavorite(user.id, productId)
                    fViewModel.deleted.observe(viewLifecycleOwner, { deleted ->
                        if (deleted) {
                            Toast.makeText(requireContext(), R.string.favorite_deleted,Toast.LENGTH_SHORT).show()
                            productList.removeAt(position)
                            notifyItemRemoved(position)
                            notifyItemRangeChanged(position, itemCount)
                        }
                    })
                }

                override fun onAddCart(view: View, position: Int, product: Product) {
                    // 장바구니에 추가
                }
            })
        }
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            this.adapter = adapter
        }
    }
}