package com.ssafy.aongbucks_manager.fragment

import androidx.fragment.app.Fragment

// 주문상세화면, My탭  - 주문내역 선택시 팝업
private const val TAG = "OrderDetailFragment_싸피"
class OrderDetailFragment : Fragment(){
//    private lateinit var orderDetailListAdapter: OrderDetailListAdapter
//    private lateinit var mainActivity: MainActivity
//
//    private var orderId = -1
//
//    private lateinit var binding: FragmentOrderDetailBinding
//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        mainActivity = context as MainActivity
//    }
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        arguments?.let {
//            orderId = it.getInt("orderId")
//        }
//
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        binding = FragmentOrderDetailBinding.inflate(inflater, container, false)
//
//        return binding.root
//    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        initData()
//    }
//
//    private fun initData(){
//        val orderDetails = OrderService().getOrderDetails(orderId)
//        orderDetails.observe(
//            viewLifecycleOwner,
//            { orderDetails ->
//                orderDetails.let {
//                    orderDetailListAdapter = OrderDetailListAdapter(mainActivity, orderDetails)
//                }
//
//                binding.recyclerViewOrderDetailList.apply {
//                    val linearLayoutManager = LinearLayoutManager(context)
//                    linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
//                    layoutManager = linearLayoutManager
//                    adapter = orderDetailListAdapter
//                    //원래의 목록위치로 돌아오게함
//                    adapter!!.stateRestorationPolicy =
//                        RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
//                }
//
//                setOrderDetailScreen(orderDetails)
//
//                Log.d(TAG, "onViewCreated: $orderDetails")
//            }
//        )
//    }
//
//    // OrderDetail 페이지 화면 구성
//    private fun setOrderDetailScreen(orderDetails: List<OrderDetailResponse>){
//        val dateFormat = SimpleDateFormat("yyyy.MM.dd HH시 mm분 ss초")
//        dateFormat.timeZone = TimeZone.getTimeZone("UTC")
//
//        binding.tvOrderStatus.text = CommonUtils.isOrderCompleted(orderDetails[0])
//        binding.tvOrderDate.text = dateFormat.format(orderDetails[0].orderDate)
//        var totalPrice = 0
//        orderDetails.forEach { totalPrice += it.totalPrice }
//        binding.tvTotalPrice.text = "$totalPrice 원"
//    }
//
//
//    override fun onDestroy() {
//        super.onDestroy()
//        mainActivity.hideBottomNav(false)
//    }
//
//    companion
//    object {
//        @JvmStatic
//        fun newInstance(key:String, value:Int) =
//            OrderDetailFragment().apply {
//                arguments = Bundle().apply {
//                    putInt(key, value)
//                }
//            }
//    }
}