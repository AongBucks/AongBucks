package com.ssafy.aongbucks_user.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.aongbucks_user.R
import com.ssafy.aongbucks_user.databinding.ListItemMenuBinding
import com.ssafy.aongbucks_user.dto.Product

class MenuAdapter(var context : Context, var productList : List<Product>)
    : RecyclerView.Adapter<MenuAdapter.MenuHolder>() {

    inner class MenuHolder(private val binding : ListItemMenuBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(dto : Product) {
            binding.apply {
                product = dto
                itemView.tag = dto
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuHolder {
        val binding = DataBindingUtil.inflate<ListItemMenuBinding>(
            LayoutInflater.from(parent.context), R.layout.list_item_menu, parent, false)
        return MenuHolder(binding)
    }

    override fun onBindViewHolder(holder: MenuHolder, position: Int) {
        val product = productList[position]
        holder.bind(product)
    }

    override fun getItemCount(): Int = productList.size

}