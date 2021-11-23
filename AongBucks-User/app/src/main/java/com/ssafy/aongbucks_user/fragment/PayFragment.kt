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
import androidx.lifecycle.LiveData
import com.ssafy.aongbucks_user.R
import com.ssafy.aongbucks_user.activity.MainActivity
import com.ssafy.aongbucks_user.config.ApplicationClass
import com.ssafy.aongbucks_user.databinding.DialogProgressBinding
import com.ssafy.aongbucks_user.databinding.FragmentPayBinding
import com.ssafy.aongbucks_user.model.dto.Pay
import com.ssafy.aongbucks_user.model.dto.User
import com.ssafy.aongbucks_user.service.PayService
import com.ssafy.aongbucks_user.util.CommonUtils
import com.ssafy.aongbucks_user.viewModel.MainActivityViewModel

private const val TAG = "PayFragment_싸피"
class PayFragment : Fragment(){

    private val activityViewModel: MainActivityViewModel by activityViewModels()
    private lateinit var mainActivity: MainActivity
    private lateinit var binding:FragmentPayBinding

    private lateinit var user: User
    private lateinit var userPay: LiveData<Pay?> // user pay info
    private lateinit var isJoined: LiveData<Boolean>

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

        binding.fragment = this
        initPayData()
    }

    private fun initPayData() {
        val dialog = Dialog(mainActivity)
        var dialogBinding : DialogProgressBinding =
            DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.dialog_progress, null, false)
        dialogBinding.progressText.text = "Pay 등록 정보를 조회하는 중입니다."

        dialog.apply {
            setContentView(dialogBinding.root)
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT)) // 배경 둥글게 하기 위함

            isJoined = PayService().isUserPayJoined(user.id)

            show()
        }

        isJoined.observe(viewLifecycleOwner, { join ->
            dialog.dismiss()
            if (join) {
                userPay = PayService().getPayInfo(user.id)
                userPay.observe(viewLifecycleOwner, { pay ->
                    binding.priceTextView.text = CommonUtils.makeComma(userPay.value!!.price)
                    binding.activeButton.visibility = View.GONE
                })
            }
        })


    }

    fun addGiftCard(view: View) {
        if (isJoined.value == false) {
            Toast.makeText(mainActivity, "애옹페이 활성화 후 이용해주세요.", Toast.LENGTH_SHORT).show()
            return
        }

        activityViewModel.changeNfcFlag(true)
        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        builder.apply {
            setTitle("알림")
            setMessage("Table NFC를 찍어주세요.\n")
            setNegativeButton("취소") { dialog, _ -> dialog.cancel()
                activityViewModel.changeNfcFlag(false)
                Toast.makeText(mainActivity, "취소되었습니다.", Toast.LENGTH_SHORT).show()
            }
        }
        val dialog = builder.create()
        dialog.show()

        activityViewModel.cardMoney.observe(viewLifecycleOwner, {
            if (activityViewModel.nfcFlag == true) { // nfc 활성화 모드일 때만

                val currentPrice = userPay.value?.price?.plus(it)

                if (currentPrice != null) {
                    userPay.value?.price = currentPrice
                    PayService().changePayPrice(userPay.value!!)
                    Toast.makeText(mainActivity, "${CommonUtils.makeComma(it)}이 등록되었습니다.", Toast.LENGTH_SHORT).show()
                }

                activityViewModel.changeNfcFlag(false) // nfc 해제
                dialog.dismiss()
           }
        })
    }

}