package com.ssafy.aongbucks_manager.fragment

import androidx.fragment.app.Fragment

// 하단 주문 탭
private const val TAG = "GradeFragment_싸피"
class GradeFragment : Fragment(){
//
//    private lateinit var menuAdapter: MenuAdapter
//    private lateinit var mainActivity: MainActivity
//    private lateinit var prodList:List<Product>
//    private lateinit var binding:FragmentOrderBinding
//
//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        mainActivity = context as MainActivity
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        binding = FragmentOrderBinding.inflate(inflater, container, false)
//        return binding.root
//    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        initData()
//
//        binding.addButton.setOnClickListener{
//            //장바구니 이동
////            mainActivity.openFragment(1)
//        }
//    }
//
//    private fun initData(){
//        ProductService().getProductList(ProductCallback())
//    }
//
//    fun onMenuClickListener(view: View) {
//        Log.d(TAG, "onMenuClickListener: click!")
//        Toast.makeText(view.context, "click... ${view.tag}", Toast.LENGTH_SHORT).show()
//    }
//
//    inner class ProductCallback: RetrofitCallback<List<Product>> {
//        override fun onSuccess( code: Int, productList: List<Product>) {
//            productList.let {
//                Log.d(TAG, "onSuccess: ${productList}")
//                menuAdapter = MenuAdapter(activity!!, this@GradeFragment, productList)
//            }
//
//            binding.recyclerViewMenu.apply {
//                layoutManager = GridLayoutManager(context,5)
//                adapter = menuAdapter
//                //원래의 목록위치로 돌아오게함
//                adapter!!.stateRestorationPolicy =
//                    RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
//            }
//
//            Log.d(TAG, "ProductCallback: $productList")
//        }
//
//        override fun onError(t: Throwable) {
//            Log.d(TAG, t.message ?: "유저 정보 불러오는 중 통신오류")
//        }
//
//        override fun onFailure(code: Int) {
//            Log.d(TAG, "onResponse: Error Code $code")
//        }
//    }


}