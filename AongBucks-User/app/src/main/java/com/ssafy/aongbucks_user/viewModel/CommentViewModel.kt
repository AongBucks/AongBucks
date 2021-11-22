package com.ssafy.aongbucks_user.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.aongbucks_user.model.RetrofitClient
import com.ssafy.aongbucks_user.model.dto.Comment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private const val TAG = "CommentViewModel_싸피"
class CommentViewModel : ViewModel() {

    private val _added = MutableLiveData<Boolean>()
    val added : LiveData<Boolean>
        get() = _added

    private fun insertComment(comment : Comment) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = RetrofitClient.commentService.addComment(comment)
            if (response.isSuccessful) {
                if (response.body() != null) {
                    when (val result = response.body()!!) {
                        true -> _added.postValue(result)
                        false -> Log.d(TAG, "insertComment: failed")
                    }
                }
            } else {
                Log.d(TAG, "insertComment: ${response.code()}")
            }
        }
    }

    fun addComment(comment : Comment) {
        insertComment(comment)
    }

    private val _edited = MutableLiveData<Boolean>()
    val edited : LiveData<Boolean>
        get() = _edited

    private fun updateComment(comment : Comment) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = RetrofitClient.commentService.editComment(comment)
            if (response.isSuccessful) {
                if (response.body() != null) {
                    when (val result = response.body()!!) {
                        true -> _edited.postValue(result)
                        false -> Log.d(TAG, "udpateComment: failed")
                    }
                }
            } else {
                Log.d(TAG, "updateComment: ${response.code()}")
            }
        }
    }

    fun editComment(comment : Comment) {
        updateComment(comment)
    }

    private val _deleted = MutableLiveData<Boolean>()
    val deleted : LiveData<Boolean>
        get() = _deleted

    private fun deleteComment(commentId : Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = RetrofitClient.commentService.delComment(commentId)
            if (response.isSuccessful) {
                if (response.body() != null) {
                    when (val result = response.body()!!) {
                        true -> _deleted.postValue(result)
                        false -> Log.d(TAG, "deleteComment: failed")
                    }
                }
            } else {
                Log.d(TAG, "deleteComment: ${response.code()}")
            }
        }
    }

    fun delComment(commentId : Int) {
        deleteComment(commentId)
    }


}