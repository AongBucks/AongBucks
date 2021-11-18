package com.ssafy.aongbucks_manager.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ssafy.aongbucks_manager.R
import com.ssafy.aongbucks_manager.config.ApplicationClass
import com.ssafy.aongbucks_manager.dto.Product

private const val TAG = "MenuAdapter_싸피"
class MenuAdapter(var productList:List<Product>) :RecyclerView.Adapter<MenuAdapter.MenuHolder>(){

    inner class MenuHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val menuName = itemView.findViewById<TextView>(R.id.textMenuNames)
        val menuImage = itemView.findViewById<ImageView>(R.id.menuImage)

        fun bindInfo(product : Product){
            menuName.text = product.name
            Glide.with(itemView)
                .load("${ApplicationClass.MENU_IMGS_URL}${product.img}")
                .into(menuImage)

            itemView.setOnClickListener{
                itemClickListner.onClick(it, layoutPosition, productList[layoutPosition].id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_menu, parent, false)
        return MenuHolder(view)
    }

    override fun onBindViewHolder(holder: MenuHolder, position: Int) {
        holder.apply{
            bindInfo(productList[position])
        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    //클릭 인터페이스 정의 사용하는 곳에서 만들어준다.
    interface ItemClickListener {
        fun onClick(view: View,  position: Int, productId:Int)
    }
    //클릭리스너 선언
    private lateinit var itemClickListner: ItemClickListener
    //클릭리스너 등록 매소드
    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListner = itemClickListener
    }
}

