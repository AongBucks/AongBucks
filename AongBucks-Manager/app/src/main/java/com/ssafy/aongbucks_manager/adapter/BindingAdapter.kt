package com.ssafy.aongbucks_manager.adapter

import android.widget.ImageView
import android.widget.RemoteViews
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ssafy.aongbucks_manager.R
import com.ssafy.aongbucks_manager.config.ApplicationClass
import com.ssafy.aongbucks_manager.dto.PaneMenu

@BindingAdapter("glideImg")
fun bindGlideFromResName(view: ImageView, img: String) {
    Glide.with(view)
        .load("${ApplicationClass.MENU_IMGS_URL}${img}")
        .into(view)
}

@BindingAdapter("imgResId")
fun bindImageFromResource(view: ImageView, resId: Int) {
    Glide.with(view)
        .load("")
        .placeholder(resId)
        .into(view)
}

@BindingAdapter("backColor")
fun bindBackgroundBySelected(layout: ConstraintLayout, isSelected: Boolean) {
    layout.setBackgroundResource(if(isSelected) R.drawable.bg_pane_item_select else R.drawable.ripple_pane_item)
}
