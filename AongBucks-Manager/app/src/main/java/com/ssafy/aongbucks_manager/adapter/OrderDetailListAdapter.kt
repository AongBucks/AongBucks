package com.ssafy.aongbucks_manager.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.aongbucks_manager.databinding.ListItemOrderDetailListBinding
import com.ssafy.aongbucks_manager.reponse.OrderDetailResponse

private const val TAG = "OrderDetailListAdapter_싸피"
class OrderDetailListAdapter(val context: Context, val list:List<OrderDetailResponse>) :RecyclerView.Adapter<OrderDetailListAdapter.OrderDetailListHolder>(){

    inner class OrderDetailListHolder(private val binding: ListItemOrderDetailListBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(dto:OrderDetailResponse){
            binding.apply {
                orderDetailDto = dto
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderDetailListHolder {
        var listItemOrderDetailListBinding =
            ListItemOrderDetailListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OrderDetailListHolder(listItemOrderDetailListBinding)
    }

    override fun onBindViewHolder(holder: OrderDetailListHolder, position: Int) {
        val dto = list[position]
        Log.d(TAG, "onBindViewHolder: ${dto}")
        holder.apply {
            bind(dto)
            itemView.tag = dto
        }
    }

    override fun getItemCount(): Int = list.size
}

