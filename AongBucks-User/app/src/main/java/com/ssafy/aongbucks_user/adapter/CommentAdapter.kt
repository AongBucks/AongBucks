package com.ssafy.aongbucks_user.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.aongbucks_user.R
import com.ssafy.aongbucks_user.config.ApplicationClass
import com.ssafy.aongbucks_user.databinding.ListItemCommentBinding
import com.ssafy.aongbucks_user.model.response.MenuDetailWithCommentResponse

class CommentAdapter(var context : Context, var commentList : MutableList<MenuDetailWithCommentResponse>)
    : RecyclerView.Adapter<CommentAdapter.CommentHolder>() {

    inner class CommentHolder(private val binding : ListItemCommentBinding)
        : RecyclerView.ViewHolder(binding.root) {
        val user = ApplicationClass.sharedPreferencesUtil.getUser()

        fun bind(data : MenuDetailWithCommentResponse) {
            binding.apply {
                comment = data
                itemView.tag = data
            }

            if (data.userId == user.id) {
                handleBtn(0)
            }

            binding.editBtn.setOnClickListener {
                binding.editComment.setText(binding.content.text)
                handleBtn(1)
            }

            binding.deleteBtn.setOnClickListener {
                btnClickListener.onDelete(itemView, layoutPosition, data.commentId)
            }

            binding.saveBtn.setOnClickListener {
                btnClickListener.onModify(itemView, layoutPosition, data)
                handleBtn(0)
            }

            binding.cancelBtn.setOnClickListener {
                handleBtn(0)
            }
        }

        fun handleBtn(flag : Int) {
            when (flag) {
                0 -> { // 수정, 삭제 버튼
                    binding.content.visibility = View.VISIBLE
                    binding.editBtn.visibility = View.VISIBLE
                    binding.deleteBtn.visibility = View.VISIBLE

                    binding.editComment.visibility = View.GONE
                    binding.saveBtn.visibility = View.GONE
                    binding.cancelBtn.visibility = View.GONE
                }
                1 -> { // 저장, 취소 버튼
                    binding.content.visibility = View.GONE
                    binding.editBtn.visibility = View.GONE
                    binding.deleteBtn.visibility = View.GONE

                    binding.editComment.visibility = View.VISIBLE
                    binding.saveBtn.visibility = View.VISIBLE
                    binding.cancelBtn.visibility = View.VISIBLE
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentHolder {
        val binding = DataBindingUtil.inflate<ListItemCommentBinding>(
            LayoutInflater.from(parent.context), R.layout.list_item_comment, parent, false)
        return CommentHolder(binding)
    }

    override fun onBindViewHolder(holder: CommentHolder, position: Int) {
        val comment = commentList[position]
        holder.bind(comment)
    }

    override fun getItemCount(): Int = commentList.size

    interface ButtonClickListener {
        fun onDelete(view : View, position: Int, commentId : Int)
        fun onModify(view : View, position : Int, data : MenuDetailWithCommentResponse)
    }

    private lateinit var btnClickListener : CommentAdapter.ButtonClickListener
    fun setBtnClickListener(btnClickListener : CommentAdapter.ButtonClickListener) {
        this.btnClickListener = btnClickListener
    }
}