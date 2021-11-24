package com.ssafy.aongbucks_user.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.aongbucks_user.R
import com.ssafy.aongbucks_user.databinding.ListItemOrderDetailBinding
import com.ssafy.aongbucks_user.model.response.OrderDetailResponse

class OrderDetailAdapter(var context : Context, var orderdetails : List<OrderDetailResponse>)
    : RecyclerView.Adapter<OrderDetailAdapter.OrderDetailHolder>() {

    inner class OrderDetailHolder(private val binding : ListItemOrderDetailBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(data : OrderDetailResponse) {
            binding.apply {
                detail = data
                itemView.tag = data
            }

            itemView.setOnClickListener {
                itemClickListener.onClick(it, layoutPosition, orderdetails[layoutPosition].productId)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderDetailAdapter.OrderDetailHolder {
        val binding = DataBindingUtil.inflate<ListItemOrderDetailBinding>(
            LayoutInflater.from(parent.context), R.layout.list_item_order_detail, parent, false)
        return OrderDetailHolder(binding)
    }

    override fun onBindViewHolder(holder: OrderDetailAdapter.OrderDetailHolder, position: Int) {
        val product = orderdetails[position]
        holder.bind(product)
    }

    override fun getItemCount(): Int = orderdetails.size

    interface ItemClickListener {
        fun onClick(View : View, position: Int, productId : Int)
    }

    private lateinit var itemClickListener : ItemClickListener
    fun setItemClickListener(itemClickListener : ItemClickListener) {
        this.itemClickListener = itemClickListener
    }

}