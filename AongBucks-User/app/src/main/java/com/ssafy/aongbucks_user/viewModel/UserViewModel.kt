package com.ssafy.aongbucks_user.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.aongbucks_user.model.RetrofitClient
import com.ssafy.aongbucks_user.model.dto.User
import com.ssafy.aongbucks_user.model.dto.UserInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private const val TAG = "UserViewModel_싸피"
class UserViewModel : ViewModel() {

    /**
     * 사용자 로그인
     */
    private val _user = MutableLiveData<User>()
    val user : LiveData<User>
        get() = _user

    private val _loginFailToast = MutableLiveData<String>()
    val loginFailToast : LiveData<String>
        get() = _loginFailToast

    private fun login(user : User) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = RetrofitClient.userService.login(user)
            if (response.isSuccessful) {
                if (response.body() != null) {
                    val nUser = response.body() as User

                    Log.d(TAG, "login: $nUser")
                    if (nUser.id != null) { // 로그인 성공
                        _user.postValue(nUser)
                    } else { // 로그인 실패
                        _loginFailToast.postValue("ID 또는 비밀번호를 확인해주세요.")
                    }
                }
            } else {
                Log.d(TAG, "login: ${response.code()}")
            }
        }
    }

    fun userLogin(user : User) {
        login(user)
    }


    /**
     * 사용자 회원가입
     */

    private val _joinCompleted = MutableLiveData<Boolean>()
    val joinCompleted : LiveData<Boolean>
        get() = _joinCompleted

    private val _joinFailToast = MutableLiveData<String>()
    val joinFailToast : LiveData<String>
        get() = _joinFailToast

    private fun join(user : User) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = RetrofitClient.userService.join(user)
            if (response.isSuccessful) {
                if (response.body() != null) {
                    Log.d(TAG, "join: ${response.body()}")
                    when (val result = response.body()!!) {
                        true -> _joinCompleted.postValue(result)
                        false -> _joinFailToast.postValue("회원가입에 실패했습니다.")
                    }
                }
            } else {
                Log.d(TAG, "join: ${response.code()}")
            }
        }
    }

    fun userJoin(user : User) {
        join(user)
    }


    /**
     * 중복 아이디 체크
     */

    private val _isUsedId = MutableLiveData<Boolean>()
    val isUsedId : LiveData<Boolean>
        get() = _isUsedId

    private val _isUsedIdToast = MutableLiveData<String>()
    val isUsedIdToast : LiveData<String>
        get() = _isUsedIdToast

    private fun checkUsedId(id : String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = RetrofitClient.userService.isUsedId(id)
            if (response.isSuccessful) {
                if (response.body() != null) {
                    Log.d(TAG, "checkUsedId: ${response.body()}")
                    when(val result = response.body()!!) {
                        true -> _isUsedIdToast.postValue("사용 중인 ID입니다.\n 다른 ID를 입력해주세요.")
                        false -> _isUsedId.postValue(result)
                    }

                }
            } else {
                Log.d(TAG, "checkUsedId: ${response.code()}")
            }
        }
    }

    fun userDuplicated(id : String) {
        checkUsedId(id)
    }

    /**
     * 사용자 정보 조회
     */

    private val _userInfo = MutableLiveData<UserInfo>()
    val userInfo : LiveData<UserInfo>
        get() = _userInfo


    private fun getUserInfo(id : String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = RetrofitClient.userService.getUserInfo(id)
            if (response.isSuccessful) {
                if (response.body() != null) {
                    _userInfo.postValue(response.body())
                }
            } else {
                Log.d(TAG, "getUserInfo: ${response.code()}")
            }
        }
    }

    fun userInfo(id : String) {
        getUserInfo(id)
    }

}