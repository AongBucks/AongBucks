package com.ssafy.aongbucks_manager.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.aongbucks_manager.databinding.ListItemMenuBinding
import com.ssafy.aongbucks_manager.dto.Product
import com.ssafy.aongbucks_manager.fragment.MenuFragment

private const val TAG = "MenuAdapter_싸피"
class ProductAdapter(val context: Context, val fContext: MenuFragment, var productList:List<Product>) :RecyclerView.Adapter<ProductAdapter.MenuHolder>(){

    inner class MenuHolder(private val binding: ListItemMenuBinding) : RecyclerView.ViewHolder(binding.root){
        // binding을 객체로 받아 데이터와 연결해준다.
        fun bind(item: Product) {
            binding.apply {
                productDto = item
                fragment = fContext
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuHolder {
        var listItemMenuBinding =
            ListItemMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MenuHolder(listItemMenuBinding)
    }

    override fun onBindViewHolder(holder: MenuHolder, position: Int) {
        val dto = productList[position]
        holder.apply{
            bind(dto)
            itemView.tag = dto
        }
    }

    override fun getItemCount() = productList.size
}

