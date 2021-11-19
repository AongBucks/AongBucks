package com.ssafy.aongbucks_manager.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.aongbucks_manager.activity.MainActivity
import com.ssafy.aongbucks_manager.adapter.MenuAdapter
import com.ssafy.aongbucks_manager.databinding.FragmentMenuBinding
import com.ssafy.aongbucks_manager.dto.Product
import com.ssafy.aongbucks_manager.service.ProductService
import com.ssafy.aongbucks_manager.util.RetrofitCallback

// 하단 주문 탭
private const val TAG = "MenuFragment_싸피"
class MenuFragment : Fragment(){

    private lateinit var menuAdapter: MenuAdapter
    private lateinit var mainActivity: MainActivity
    private lateinit var prodList:List<Product>
    private lateinit var binding:FragmentMenuBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()

        binding.addButton.setOnClickListener{
            //장바구니 이동
//            mainActivity.openFragment(1)
        }
    }

    private fun initData(){
        ProductService().getProductList(ProductCallback())
    }

    fun onMenuClickListener(view: View) {
        Log.d(TAG, "onMenuClickListener: click!")
        Toast.makeText(view.context, "click... ${view.tag}", Toast.LENGTH_SHORT).show()
    }

    inner class ProductCallback: RetrofitCallback<List<Product>> {
        override fun onSuccess( code: Int, productList: List<Product>) {
            productList.let {
                Log.d(TAG, "onSuccess: ${productList.size}")
                menuAdapter = MenuAdapter(mainActivity, this@MenuFragment, productList)
            }

            binding.recyclerViewMenu.apply {
                layoutManager = GridLayoutManager(context,5)
                adapter = menuAdapter
                //원래의 목록위치로 돌아오게함
                adapter!!.stateRestorationPolicy =
                    RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
            }

            Log.d(TAG, "ProductCallback: $productList")
        }

        override fun onError(t: Throwable) {
            Log.d(TAG, t.message ?: "유저 정보 불러오는 중 통신오류")
        }

        override fun onFailure(code: Int) {
            Log.d(TAG, "onResponse: Error Code $code")
        }
    }


}