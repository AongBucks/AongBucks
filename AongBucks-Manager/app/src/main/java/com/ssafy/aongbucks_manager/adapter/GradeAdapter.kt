package com.ssafy.aongbucks_manager.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.aongbucks_manager.databinding.ListItemGradeBinding
import com.ssafy.aongbucks_manager.fragment.GradeFragment
import com.ssafy.aongbucks_manager.reponse.GradeResponse

private const val TAG = "GradeAdapter_싸피"
class GradeAdapter(val context: Context, val fContext: GradeFragment, var list:List<GradeResponse>) :RecyclerView.Adapter<GradeAdapter.GradeHolder>(){

    inner class GradeHolder(private val binding: ListItemGradeBinding) : RecyclerView.ViewHolder(binding.root){
        // binding을 객체로 받아 데이터와 연결해준다.
        fun bind(item: GradeResponse) {
            binding.apply {
                gradeDto = item
                fragment = fContext
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GradeHolder {
        var listItemGradeBinding =
            ListItemGradeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GradeHolder(listItemGradeBinding)
    }

    override fun onBindViewHolder(holder: GradeHolder, position: Int) {
        val dto = list[position]
        holder.apply{
            bind(dto)
            itemView.tag = dto
            Log.d(TAG, "onBindViewHolder: ${dto}")
        }
    }

    override fun getItemCount() = list.size
}

