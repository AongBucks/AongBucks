package com.ssafy.aongbucks_user.fragment

import android.app.Dialog
import android.app.ProgressDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
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

class PayFragment : Fragment(){
    private lateinit var mainActivity: MainActivity
    private lateinit var binding:FragmentPayBinding
    private lateinit var user: User
    private lateinit var userPay: LiveData<Pay?>

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

            userPay = PayService().getPayInfo(user.id)
            show()
        }

        userPay.observe(viewLifecycleOwner, {
            dialog.dismiss()
            if (userPay.value != null) {
                binding.priceTextView.text = CommonUtils.makeComma(userPay.value!!.price)
            }
        })
    }

}