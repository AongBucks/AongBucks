package com.ssafy.aongbucks_manager.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.aongbucks_manager.databinding.ListItemOrderBinding
import com.ssafy.aongbucks_manager.fragment.OrderFragment
import com.ssafy.aongbucks_manager.reponse.TotalOrderResponse

private const val TAG = "OrderAdapter_싸피"
class OrderAdapter(val context: Context, val orderFragment: OrderFragment, var list:List<TotalOrderResponse>) :RecyclerView.Adapter<OrderAdapter.OrderHolder>(){

    inner class OrderHolder(private val binding: ListItemOrderBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(data:TotalOrderResponse){
            binding.apply {
                totalOrderDto = data
                fragment = orderFragment
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderHolder {
        var listItemOrderBinding =
            ListItemOrderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OrderHolder(listItemOrderBinding)
    }

    override fun onBindViewHolder(holder: OrderHolder, position: Int) {
        val dto = list[position]
        holder.apply {
            bind(dto)
            dto.position = position
            itemView.tag = dto
        }
    }

    override fun getItemCount(): Int = list.size

}

