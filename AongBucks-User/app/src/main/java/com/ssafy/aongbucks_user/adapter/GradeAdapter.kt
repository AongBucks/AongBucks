package com.ssafy.aongbucks_user.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.aongbucks_user.R
import com.ssafy.aongbucks_user.databinding.ListItemGradeBinding
import com.ssafy.aongbucks_user.model.dto.Grade

class GradeAdapter(var context : Context, var gradeList : List<Grade>)
    : RecyclerView.Adapter<GradeAdapter.GradeHolder>() {

    inner class GradeHolder(private val binding : ListItemGradeBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(data : Grade) {
            binding.apply {
                grade = data
                itemView.tag = data
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GradeAdapter.GradeHolder {
        val binding = DataBindingUtil.inflate<ListItemGradeBinding>(
            LayoutInflater.from(parent.context), R.layout.list_item_grade, parent, false)
        return GradeHolder(binding)
    }

    override fun onBindViewHolder(holder: GradeAdapter.GradeHolder, position: Int) {
        val product = gradeList[position]
        holder.bind(product)
    }

    override fun getItemCount(): Int = gradeList.size

}