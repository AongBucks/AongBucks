package com.ssafy.aongbucks_manager.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.ssafy.aongbucks_manager.config.ApplicationClass

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