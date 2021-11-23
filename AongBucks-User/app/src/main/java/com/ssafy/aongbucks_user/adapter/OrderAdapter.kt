package com.ssafy.aongbucks_user.adapter

import com.ssafy.aongbucks_user.databinding.ListItemOrderBinding
import com.ssafy.aongbucks_user.model.response.LatestOrderResponse
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.aongbucks_user.R

class OrderAdapter(var context : Context, var orderList : List<LatestOrderResponse>)
    : RecyclerView.Adapter<OrderAdapter.OrderHolder>() {

    inner class OrderHolder(private val binding : ListItemOrderBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(data : LatestOrderResponse) {
            binding.apply {
                order = data
                itemView.tag = data
            }

            itemView.setOnClickListener {
                itemClickListener.onClick(it, layoutPosition, orderList[layoutPosition].orderId)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderAdapter.OrderHolder {
        val binding = DataBindingUtil.inflate<ListItemOrderBinding>(
            LayoutInflater.from(parent.context), R.layout.list_item_order, parent, false)
        return OrderHolder(binding)
    }

    override fun onBindViewHolder(holder: OrderAdapter.OrderHolder, position: Int) {
        val order = orderList[position]
        holder.bind(order)
    }

    override fun getItemCount(): Int = orderList.size

    interface ItemClickListener {
        fun onClick(View : View, position: Int, orderId : Int)
    }

    private lateinit var itemClickListener : ItemClickListener
    fun setItemClickListener(itemClickListener : ItemClickListener) {
        this.itemClickListener = itemClickListener
    }

}