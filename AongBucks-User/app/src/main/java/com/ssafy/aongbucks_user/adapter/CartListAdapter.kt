package com.ssafy.aongbucks_user.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.aongbucks_user.databinding.ListItemCartBinding
import com.ssafy.aongbucks_user.fragment.CartFragment
import com.ssafy.aongbucks_user.model.dto.ShoppingCart


class CartListAdapter(val context: Context, val frag: CartFragment, var cartList:MutableList<ShoppingCart>) :RecyclerView.Adapter<CartListAdapter.ShoppingListHolder>(){

    inner class ShoppingListHolder(private val binding: ListItemCartBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(cart: ShoppingCart){
            binding.apply {
                cartDto = cart
                fragment = frag
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingListHolder {
        var listItemCartBinding =
            ListItemCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ShoppingListHolder(listItemCartBinding)
    }

    override fun onBindViewHolder(holder: ShoppingListHolder, position: Int) {
        val dto = cartList[position]
        holder.apply {
            dto.position = position
            bind(dto)
            itemView.tag = dto
        }
    }

    override fun getItemCount(): Int = cartList.size

}

