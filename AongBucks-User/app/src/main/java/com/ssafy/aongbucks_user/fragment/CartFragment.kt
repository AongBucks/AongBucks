package com.ssafy.aongbucks_user.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.aongbucks_user.R
import com.ssafy.aongbucks_user.activity.MainActivity
import com.ssafy.aongbucks_user.adapter.CartListAdapter
import com.ssafy.aongbucks_user.config.ApplicationClass
import com.ssafy.aongbucks_user.databinding.FragmentCartBinding
import com.ssafy.aongbucks_user.model.dto.ShoppingCart
import com.ssafy.aongbucks_user.model.dto.User
import com.ssafy.aongbucks_user.viewModel.MainActivityViewModel

class CartFragment : Fragment(){

    private val activityViewModel: MainActivityViewModel by activityViewModels()

    private lateinit var mainActivity: MainActivity
    private lateinit var binding: FragmentCartBinding

    private lateinit var cartListAdapter: CartListAdapter

    private lateinit var user: User

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity.hideBottomNav(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cart, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        user = ApplicationClass.sharedPreferencesUtil.getUser()
        binding.fragment = this

        cartListAdapter = CartListAdapter(mainActivity, this, activityViewModel.shoppingCart)
        binding.carRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = cartListAdapter
            //원래의 목록위치로 돌아오게함
            adapter!!.stateRestorationPolicy =
                RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        }

        initData()
    }

    private fun initData() {
        cartListAdapter.cartList = activityViewModel.shoppingCart // list update
        cartListAdapter.notifyDataSetChanged()

        if (activityViewModel.getCartSize() == 0) {
            binding.emptyText.visibility = View.VISIBLE
        } else {
            binding.emptyText.visibility = View.GONE
        }

//        binding.shoppingCount.text = "총 ${activityViewModel.getSize()}개"
//        binding.shoppingMoney.text = "${activityViewModel.getTotalPrice()}원"

    }

    fun onRemoveItemListener(view: View) {
        val position = (view.tag as ShoppingCart).position
        activityViewModel.removeCart(position)
        initData()
    }

    fun onOrderClickListener(view: View) {
        if (activityViewModel.getCartSize() == 0) {
            Toast.makeText(context,"주문할 상품이 없습니다.", Toast.LENGTH_SHORT).show()
            return
        }

        // order confirm dialog

        // navigate to order complete fragment
        mainActivity.navController.navigate(R.id.action_cartFragment_to_orderCompleteFragment)
    }

    override fun onDestroy() {
        super.onDestroy()
        mainActivity.hideBottomNav(false)
    }
}