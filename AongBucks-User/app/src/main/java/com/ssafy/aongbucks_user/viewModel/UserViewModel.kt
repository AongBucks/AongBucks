package com.ssafy.aongbucks_user.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.aongbucks_user.dto.User
import com.ssafy.aongbucks_user.service.UserApi
import kotlinx.coroutines.launch

private const val TAG = "UserViewModel_싸피"
class UserViewModel : ViewModel() {

    private val _user = MutableLiveData<User>()
    val user : LiveData<User>
        get() = _user


    private val _joinCompleted = MutableLiveData<Boolean>()
    val joinCompleted : LiveData<Boolean>
        get() = _joinCompleted

    private val _isUsedId = MutableLiveData<Boolean>()
    val isUsedId : LiveData<Boolean>
        get() = _isUsedId

    private fun login(user : User) {
        viewModelScope.launch {
            try {
                _user.value = UserApi.userService.login(user)
            } catch (e : Exception) {
                Log.d(TAG, "login: ${e.message}")
            }
        }
    }

    fun userLogin(user : User) {
        login(user)
    }

    fun userJoin(user : User) {
        viewModelScope.launch {
            try {
                _joinCompleted.value = UserApi.userService.join(user)
            } catch (e : Exception) {
                Log.d(TAG, "userJoin: ${e.message}")
            }
        }
    }
    
    fun userDuplicated(id : String) {
        viewModelScope.launch { 
            try {
                _isUsedId.value = UserApi.userService.isUsedId(id)
            } catch (e : Exception) {
                Log.d(TAG, "isUsedId: ${e.message}")
            }
        }
    }

}