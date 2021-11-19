package com.ssafy.aongbucks_manager.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// 하단 주문 탭
private const val TAG = "OrderFragment_싸피"
class OrderFragment : Fragment(){
    private lateinit var orderAdapter : OrderAdapter
    private lateinit var mainActivity: MainActivity
    private lateinit var list : List<LatestOrderResponse>

    private lateinit var binding:FragmentMypageBinding
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMypageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userId = getUserData()
        UserService().getUserInfo(userId, UserInfoCallback())

        initOrderData(userId)
    }

    private fun initOrderData(id:String){
        // 최근 주문 내역
        val userLastOrderLiveData = OrderService().getLastMonthOrder(id)
        Log.d(TAG, "onViewCreated: ${userLastOrderLiveData.value}")
        userLastOrderLiveData.observe(
            viewLifecycleOwner,
            {
                list = it

                orderAdapter = OrderAdapter(mainActivity, list)
                orderAdapter.setItemClickListener(object : OrderAdapter.ItemClickListener{
                    override fun onClick(view: View, position: Int, orderid:Int) {
                        mainActivity.openFragment(2, "orderId", orderid)
                    }
                })

                binding.recyclerViewOrder.apply {
                    layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                    adapter = orderAdapter
                    //원래의 목록위치로 돌아오게함
                    adapter!!.stateRestorationPolicy =
                        RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
                }
                binding.logout.setOnClickListener {
                    mainActivity.openFragment(5)
                }

                Log.d(TAG, "onViewCreated: $it")
            }
        )

    }

    private fun setUserInfoTv(info : UserInfo) {
        val grade = info.grade
        val stamps = info.user.stamps

        val imgId = requireContext().resources.getIdentifier(
            grade.img.substring(0, grade.img.length - 4),
            "drawable",
            requireContext().packageName)

        binding.imageLevel.setImageResource(imgId)

        when (grade.title) {
            "나무" -> {
                binding.textUserLevel.text = "나무 단계"
                binding.textLevelRest.text = "최고 등급입니다."

                binding.proBarUserLevel.apply {
                    max = 100
                    progress = 100
                }
                binding.textUserNextLevel.text = "MAX"
            }
            else -> {
                binding.textUserLevel.text = "${grade.title} ${grade.step}단계"
                binding.textLevelRest.text = "다음 단계까지 ${grade.to}잔 남았습니다."

                var curUnit = 0
                for (i in userInfoList.indices) {
                    if (userInfoList[i].title == grade.title) {
                        curUnit = userInfoList[i].unit
                        break
                    }
                }

                val rest = curUnit - grade.to
                Log.d(TAG, "setUserInfoTv: ${grade.to} $curUnit")
                binding.proBarUserLevel.apply {
                    max = curUnit
                    progress = rest
                }
                binding.textUserNextLevel.text = "$rest / $curUnit"
            }
        }
    }


    private fun getUserData(): String {
        var user = ApplicationClass.sharedPreferencesUtil.getUser()
        binding.textUserName.text = user.name

        return user.id
    }

    inner class UserInfoCallback: RetrofitCallback<UserInfo> {
        override fun onSuccess(
            code: Int,
            responseData: UserInfo
        ) {
            if(responseData != null) {
                setUserInfoTv(responseData)
            }
        }

        override fun onError(t: Throwable) {
            Log.d(TAG, t.message ?: "사용자 정보 받아오는 중 통신오류")
        }

        override fun onFailure(code: Int) {
            Log.d(TAG, "UserInfoCallback onResponse: Error Code $code")
        }
    }


}