package com.ssafy.aongbucks_user.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.aongbucks_user.R
import com.ssafy.aongbucks_user.activity.MainActivity
import com.ssafy.aongbucks_user.adapter.CartListAdapter
import com.ssafy.aongbucks_user.config.ApplicationClass
import com.ssafy.aongbucks_user.databinding.FragmentCartBinding
import com.ssafy.aongbucks_user.model.dto.Order
import com.ssafy.aongbucks_user.model.dto.User
import com.ssafy.aongbucks_user.viewModel.GradeViewModel
import com.ssafy.aongbucks_user.viewModel.MainActivityViewModel
import com.ssafy.aongbucks_user.viewModel.OrderViewModel
import com.ssafy.aongbucks_user.viewModel.PayViewModel
import java.text.SimpleDateFormat
import java.util.*

private const val TAG = "CartFragment_싸피"
class CartFragment : Fragment(){

    private val activityViewModel: MainActivityViewModel by activityViewModels()

    private val gViewModel: GradeViewModel by viewModels()
    private val oViewModel: OrderViewModel by viewModels()

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
        // list update
        cartListAdapter.cartList = activityViewModel.shoppingCart
        cartListAdapter.notifyDataSetChanged()

        if (activityViewModel.getCartSize() == 0) {
            binding.emptyText.visibility = View.VISIBLE
        } else {
            binding.emptyText.visibility = View.GONE
        }

        // discount update
        Log.d(TAG, "initData: ${user.id}")
        gViewModel.initDiscount(user.id)
        gViewModel.discount.observe(viewLifecycleOwner, {
            if (it == -1f) {
                Toast.makeText(context,"서버 에러", Toast.LENGTH_SHORT).show()
                return@observe
            }
            activityViewModel.changeDiscount(it)

            // price update
            activityViewModel.getTotalPrice()
            binding.totalDto = activityViewModel.totalCart
        })
    }

    fun onRemoveItemListener(view: View) {
        val position = view.tag as Int
        activityViewModel.removeCart(position)
        initData()
    }

    fun onOrderClickListener(view: View) {
        if (activityViewModel.getCartSize() == 0) {
            Toast.makeText(context,"주문할 상품이 없습니다.", Toast.LENGTH_SHORT).show()
            return
        }

        // order confirm
        completedOrder("pick-up")

        // navigate to order complete fragment
        mainActivity.navController.navigate(R.id.action_cartFragment_to_payFragment)
        // TODO: 2021-11-24 백스택 안 남기기! (pay 에도)
    }

    private fun completedOrder(orderTable: String){
        // 유저
        var user = ApplicationClass.sharedPreferencesUtil.getUser()

        // 시간
        val nowDate = Date(System.currentTimeMillis())
        val now = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX").format(nowDate)


        val order = Order(user.id, orderTable, now).apply {
            totalQnanty = activityViewModel.getCartSize()
            totalPrice = activityViewModel.getTotalPrice()
            topProductName = activityViewModel.shoppingCart[0].menuName
            topImg = activityViewModel.shoppingCart[0].menuImg
        }
        activityViewModel.makeDetail(order) // order detail 설정

        oViewModel.makeOrder(order)
        activityViewModel.shoppingCart.clear()

        Toast.makeText(context, "테이블 : ${orderTable}", Toast.LENGTH_SHORT).show()
    }

    /**
     * 애옹페이로 지불하기
     */
    private fun pay(price: Int) {

    }

    override fun onDestroy() {
        super.onDestroy()
        mainActivity.hideBottomNav(false)
    }
}