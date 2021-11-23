package com.ssafy.aongbucks_user.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel: ViewModel() {

    var nfcFlag = false
        private set

    private val _cardMoney = MutableLiveData<Int>()
    val cardMoney : LiveData<Int>
        get() = _cardMoney

    fun changeNfcFlag(flag: Boolean) {
        nfcFlag = flag
    }

    fun readGiftCard(price: String) {
        _cardMoney.value = price.toInt()
    }
}