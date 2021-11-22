package com.ssafy.aongbucks_user.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.aongbucks_user.R
import com.ssafy.aongbucks_user.databinding.ListItemFavoriteProductBinding
import com.ssafy.aongbucks_user.model.dto.Product

class FavoriteAdapter(val context : Context, val productList : MutableList<Product>)
    : RecyclerView.Adapter<FavoriteAdapter.FavoriteHolder>() {

    inner class FavoriteHolder(private val binding : ListItemFavoriteProductBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(data : Product) {
            binding.apply {
                product = data
                itemView.tag = data
            }

            binding.favoriteBtn.setOnClickListener {
                btnClickListener.onFavorite(it, layoutPosition, data.id)
            }

            binding.addCartBtn.setOnClickListener {
                btnClickListener.onAddCart(it, layoutPosition, data)
            }

            itemView.setOnClickListener {
                itemClickListener.onClick(it, layoutPosition, productList[layoutPosition].id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteAdapter.FavoriteHolder {
        val binding = DataBindingUtil.inflate<ListItemFavoriteProductBinding>(
            LayoutInflater.from(parent.context), R.layout.list_item_favorite_product, parent, false)
        return FavoriteHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteAdapter.FavoriteHolder, position: Int) {
        val product = productList[position]
        holder.bind(product)
    }

    override fun getItemCount(): Int = productList.size

    interface ItemClickListener {
        fun onClick(view : View, position: Int, productId : Int)
    }

    private lateinit var itemClickListener : ItemClickListener
    fun setItemClickListener(itemClickListener : ItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    interface ButtonClickListener {
        fun onFavorite(view : View, position : Int, productId : Int)
        fun onAddCart(view : View, position : Int, product : Product)
    }

    private lateinit var btnClickListener : ButtonClickListener
    fun setBtnClickListener(btnClickListener : FavoriteAdapter.ButtonClickListener) {
        this.btnClickListener = btnClickListener
    }

}