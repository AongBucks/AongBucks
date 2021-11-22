package com.ssafy.aongbucks_user.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.aongbucks_user.model.RetrofitClient
import com.ssafy.aongbucks_user.model.dto.Comment
import com.ssafy.aongbucks_user.model.dto.Favorite
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private const val TAG = "FavoriteViewModel_싸피"
class FavoriteViewModel : ViewModel() {

    /**
     * 즐겨찾기 등록
     */

    private val _added = MutableLiveData<Boolean>()
    val added : LiveData<Boolean>
        get() = _added

    private fun insertFavorite(favorite : Favorite) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = RetrofitClient.favoriteService.addFavorite(favorite)
            if (response.isSuccessful) {
                if (response.body() != null) {
                    when (val result = response.body()!!) {
                        true -> _added.postValue(result)
                        false -> Log.d(TAG, "insertFavorite : failed")
                    }
                }
            } else {
                Log.d(TAG, "insertFavorite: ${response.code()}")
            }
        }
    }

    fun addFavorite(userId : String, productId : Int) {
        insertFavorite(Favorite(userId, productId))
    }


    /**
     * 즐겨찾기 삭제
     */

    private val _deleted = MutableLiveData<Boolean>()
    val deleted : LiveData<Boolean>
        get() = _deleted

    private fun deleteFavorite(userId : String, productId : Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = RetrofitClient.favoriteService.delFavorite(userId, productId)
            if (response.isSuccessful) {
                if (response.body() != null) {
                    when (val result = response.body()!!) {
                        true -> _deleted.postValue(result)
                        false -> Log.d(TAG, "deleteFavorite : failed")
                    }
                }
            } else {
                Log.d(TAG, "deleteFavorite: ${response.code()}")
            }
        }
    }

    fun delFavorite(userId : String, productId : Int) {
        deleteFavorite(userId, productId)
    }


}