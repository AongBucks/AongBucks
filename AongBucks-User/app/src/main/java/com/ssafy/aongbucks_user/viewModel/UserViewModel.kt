package com.ssafy.aongbucks_user.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.aongbucks_user.dto.User
import com.ssafy.aongbucks_user.service.UserApi
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {

    private val _user = MutableLiveData<User>()
    val user : LiveData<User>
        get() = _user

    private fun login(user : User) {
        viewModelScope.launch {
            try {
                _user.value = UserApi.userService.login(user)
            } catch (e : Exception) {

            }
        }
    }

    fun userLogin(user : User) {
        login(user)
    }

}