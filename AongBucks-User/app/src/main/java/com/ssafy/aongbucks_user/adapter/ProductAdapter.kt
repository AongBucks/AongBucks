package com.ssafy.aongbucks_user.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.aongbucks_user.R
import com.ssafy.aongbucks_user.databinding.ListItemProductBinding
import com.ssafy.aongbucks_user.model.dto.Product

class ProductAdapter(var context : Context, var productList : List<Product>)
    : RecyclerView.Adapter<ProductAdapter.ProductHolder>() {

    inner class ProductHolder(private val binding : ListItemProductBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(dto : Product) {
            binding.apply {
                product = dto
                itemView.tag = dto
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        val binding = DataBindingUtil.inflate<ListItemProductBinding>(
            LayoutInflater.from(parent.context), R.layout.list_item_product, parent, false)
        return ProductHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        val product = productList[position]
        holder.bind(product)
    }

    override fun getItemCount(): Int = productList.size

}