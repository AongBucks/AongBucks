package com.ssafy.aongbucks_user.fragment

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
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
import com.ssafy.aongbucks_user.R
import com.ssafy.aongbucks_user.activity.MainActivity
import com.ssafy.aongbucks_user.config.ApplicationClass
import com.ssafy.aongbucks_user.databinding.DialogProgressBinding
import com.ssafy.aongbucks_user.databinding.FragmentPayBinding
import com.ssafy.aongbucks_user.model.dto.User
import com.ssafy.aongbucks_user.util.CommonUtils
import com.ssafy.aongbucks_user.viewModel.MainActivityViewModel
import com.ssafy.aongbucks_user.viewModel.OrderViewModel
import com.ssafy.aongbucks_user.viewModel.PayViewModel

private const val TAG = "PayFragment_싸피"
class PayFragment : Fragment(){

    private val activityViewModel: MainActivityViewModel by activityViewModels()
    private val payViewModel: PayViewModel by viewModels()
    private val oViewModel: OrderViewModel by viewModels()

    private lateinit var mainActivity: MainActivity
    private lateinit var binding:FragmentPayBinding
    private lateinit var dialog: Dialog

    private lateinit var user: User

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_pay, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        user = ApplicationClass.sharedPreferencesUtil.getUser()
        dialog = Dialog(mainActivity)
        binding.fragment = this

        initPayData()

        setObserver()
    }

    private fun setObserver() {
        payViewModel.isJoined.observe(viewLifecycleOwner, { join ->
            Log.d(TAG, "onViewCreated: !!!isjoin observe")
            dialog.cancel()
            if (join) {
                payViewModel.initPayInfo(user.id)
            }
        })

        payViewModel.userPay.observe(viewLifecycleOwner, {
            if (it != null) {
                Log.d(TAG, "onViewCreated: !!!userpay observe")
                binding.priceTextView.text = CommonUtils.makeComma(it.price)
                binding.activeButton.visibility = View.GONE
                binding.constraintLayout.isClickable = true
            }
        })
    }

    private fun initPayData() {
//        payViewModel.changeFlag(false) // 혹시 켜져있었다면..

        Log.d(TAG, "initPayData: ")
        var dialogBinding : DialogProgressBinding =
            DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.dialog_progress, null, false)
        dialogBinding.progressText.text = "Pay 등록 정보를 조회하는 중입니다."

        dialog.apply {
            setContentView(dialogBinding.root)
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT)) // 배경 둥글게 하기 위함

            payViewModel.initUserJoin(user.id)

            show()
        }
    }

    fun addGiftCard(view: View) {
        // TODO: 2021-11-24 한 번 nfc해두면 앱 종료 전까지 무한 충전됨 ㅜㅜ
        Log.d(TAG, "addGiftCard: ")
        if (payViewModel.isJoined.value == false) {
            Toast.makeText(mainActivity, "애옹페이 활성화 후 이용해주세요.", Toast.LENGTH_SHORT).show()
            return
        }

        activityViewModel.changeNfcFlag(true)
        payViewModel.changeFlag(true)
        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        builder.apply {
            setCancelable(false)
            setTitle("기프트카드 등록")
            setMessage("NFC를 태그해주세요.\n")
            setNegativeButton("취소") { dialog, _ ->
                dialog.cancel()
                activityViewModel.changeNfcFlag(false)
                payViewModel.changeFlag(false) // 무조건 여기서 해제됨!!
                Toast.makeText(mainActivity, "취소되었습니다.", Toast.LENGTH_SHORT).show()
            }
        }
        val dialog = builder.create()
        dialog.show()

        activityViewModel.cardMoney.observe(viewLifecycleOwner, {
            Log.d(TAG, "addGiftCard: !!!card money observe")
            if (payViewModel.flag == true) { // nfc 활성화 모드일 때만

                if (it != null) {
                    payViewModel.plusMoney(it)
                    Toast.makeText(mainActivity, "${CommonUtils.makeComma(it)}이 등록되었습니다.", Toast.LENGTH_SHORT).show()
                }

                payViewModel.changeFlag(false)
                activityViewModel.changeNfcFlag(false) // nfc 해제
                dialog.dismiss()
           }
        })
    }

    fun joinPay(view: View) {
        Log.d(TAG, "joinPay: ")
        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        builder.apply {
            setTitle("애옹페이 활성화")
            setMessage("애옹페이를 활성화 하시겠어옹?\n")
            setNeutralButton("취소") { dialog, _ ->
                dialog.cancel()
                Toast.makeText(mainActivity, "취소되었습니다.", Toast.LENGTH_SHORT).show()
            }
            setPositiveButton("활성화") { _, _ ->
                Log.d(TAG, "joinPay: 활성화")
                if (payViewModel.isJoined.value == false) {
                    payViewModel.joinUser(user.id)
                }
            }
        }
        dialog = builder.create()
        dialog.show()
    }

    fun doPay(view: View) {
        if (activityViewModel.totalCart == null || activityViewModel.finalShoppingCart == null) {
            Toast.makeText(mainActivity, "결제할 상품이 없습니다.", Toast.LENGTH_SHORT).show()
        } else {
            if(payViewModel.minusMoney(activityViewModel.totalCart!!.resultPrice)) { // 상품 금액 차감
                payViewModel.plusMoney(activityViewModel.totalCart!!.reserve) // 적립급
                Toast.makeText(mainActivity, "${activityViewModel.totalCart!!.reserve}원이 적립되었습니다.", Toast.LENGTH_SHORT).show()

                payViewModel.userPay.value?.let { activityViewModel.initPayMoney(it.price) } // 현재 상태에 반영

                // 주문 접수
                var dialogBinding : DialogProgressBinding =
                    DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.dialog_progress, null, false)
                dialogBinding.progressText.text = "주문을 접수하고 있어옹"

                dialog.apply {
                    setContentView(dialogBinding.root)
                    window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT)) // 배경 둥글게 하기 위함
                    setCancelable(false)

                    oViewModel.makeOrder(activityViewModel.finalShoppingCart!!)

                    show()
                }
                Log.d(TAG, "doPay: ${activityViewModel.finalShoppingCart}")

                oViewModel.orderId.observe(viewLifecycleOwner, {
                    activityViewModel.completeOrder(it)
                    dialog.cancel()
                    activityViewModel.shoppingCart.clear() // 장바구니 비우기
                    mainActivity.navController.navigate(R.id.action_payFragment_to_orderCompleteFragment)
                    Log.d(TAG, "doPay: 결제완료")
                })

            } else {
                Toast.makeText(mainActivity, "잔액이 부족합니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: ")
//        payViewModel.changeFlag(false) // 혹시 못 껐으면,,
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: ")
    }
}