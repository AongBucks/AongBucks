package com.ssafy.aongbucks_manager.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.aongbucks_manager.activity.MainActivity
import com.ssafy.aongbucks_manager.databinding.ListItemPaneBinding
import com.ssafy.aongbucks_manager.databinding.ListItemPaneTitleBinding
import com.ssafy.aongbucks_manager.dto.PaneMenu

private const val TAG = "MenuAdapter_싸피"
class PaneAdapter(val context: Context, var list:List<PaneMenu>) :RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    inner class PaneHolder(private val binding: ListItemPaneBinding) : RecyclerView.ViewHolder(binding.root){
        // binding을 객체로 받아 데이터와 연결해준다.
        fun bind(item: PaneMenu) {
            binding.apply {
                menuDto = item
                activity = context as MainActivity
            }
        }
    }

    inner class TitleHolder(private val binding: ListItemPaneTitleBinding) : RecyclerView.ViewHolder(binding.root){
        // binding을 객체로 받아 데이터와 연결해준다.
        fun bind(item: PaneMenu) {
            binding.apply {
                menuDto = item
            }
        }
    }

    /**
     * PaneMenu의 id값이 MainActivity.TITLE일 때만 TitleHolder를 지정하기 위해서
     * ItemViewType 을 list[position].id 로 지정해준다.
     */
    override fun getItemViewType(position: Int): Int {
        return list[position].id
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == MainActivity.TITLE) {
            var listItemPaneTitleBinding =
                ListItemPaneTitleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return TitleHolder(listItemPaneTitleBinding)
        } else {
            var listItemPaneBinding =
                ListItemPaneBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return PaneHolder(listItemPaneBinding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val dto = list[position]
        if (dto.id == MainActivity.TITLE) {
            (holder as TitleHolder).apply {
                bind(dto)
            }
        } else {
            (holder as PaneHolder).apply{
                bind(dto)
                itemView.tag = dto
            }
        }
    }

    override fun getItemCount() = list.size

}

