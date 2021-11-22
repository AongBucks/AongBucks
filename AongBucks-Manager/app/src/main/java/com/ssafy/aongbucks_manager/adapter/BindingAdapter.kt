package com.ssafy.aongbucks_manager.adapter

import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.ssafy.aongbucks_manager.R
import com.ssafy.aongbucks_manager.config.ApplicationClass
import com.ssafy.aongbucks_manager.util.CommonUtils
import java.util.*

@BindingAdapter("menuImg")
fun bindGlideMenuFromResName(view: ImageView, img: String) {
    Glide.with(view)
        .load("${ApplicationClass.MENU_IMGS_URL}${img}")
        .into(view)
}

@BindingAdapter("gradeImg")
fun bindGlideGradeFromResName(view: ImageView, img: String) {
    Glide.with(view)
        .load("${ApplicationClass.GRADE_IMGS_URL}${img}")
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

@BindingAdapter(value = ["chiefMenu","count"])
fun bindOrderMenuNames(view: TextView, menu: String, count: Int) {
    view.text = if(count>1) "${menu} 외 ${count-1}건" else menu
}

@BindingAdapter("price")
fun bindOrderPrice(view: TextView, price: Int) {
    view.text = CommonUtils.makeComma(price)
}

@BindingAdapter("totalPrice")
fun bindOrderTotalPrice(view: TextView, price: Int) {
    view.text = "총 " + CommonUtils.makeComma(price)
}

@BindingAdapter("date")
fun bindOrderTime(view: TextView, date: Date?) {
    if (date == null) return
    view.text = CommonUtils.getFormattedString(date)
}

@BindingAdapter("complete")
fun bindBackgroundByCompleted(layout: ConstraintLayout, complete: Char) {
    if (complete == 'Y') layout.setBackgroundResource(R.color.light_green)
    else layout.setBackgroundResource(R.color.white)
}

@BindingAdapter("stateText")
fun bindTextComplete(view: TextView, complete: Char) {
    if (complete == 'Y') view.text = "제조 완료"
    else view.text = "대기 중.."
}

@BindingAdapter(value = ["menuType", "menuCount"])
fun bindDetailMenuCount(view: TextView, menuType: String?, count: Int) {
    var type = if(menuType == "coffee") "잔" else "개"
    view.text = count.toString() + type
}

@BindingAdapter("percent")
fun bindSalePercent(view: TextView, discount: Float) {
    view.text = "${discount} %"
}

@BindingAdapter("score")
fun bindLevelStandard(view: TextView, standard: Int) {
    view.text = "${standard}점"
}