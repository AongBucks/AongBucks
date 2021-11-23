package com.ssafy.aongbucks_user.adapter

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.ssafy.aongbucks_user.config.ApplicationClass
import com.ssafy.aongbucks_user.model.response.LatestOrderResponse
import com.ssafy.aongbucks_user.util.CommonUtils
import java.util.*


private const val TAG = "BindingAdapter_싸피"

@BindingAdapter("menuImage")
fun menuImageDrawable(view : ImageView, img : String?) {
    Glide.with(view)
        .load("${ApplicationClass.MENU_IMGS_URL}${img}")
        .into(view)
}

@BindingAdapter("gradeImage")
fun gradeImageDrawable(view : ImageView, img : String?) {
    Glide.with(view)
        .load("${ApplicationClass.GRADE_IMGS_URL}${img}")
        .fitCenter()
        .into(view)
}

@BindingAdapter("setRating")
fun setRating(ratingBar : RatingBar, rating: Double) {
    ratingBar.rating = rating.toFloat()
}

@BindingAdapter("setRatingAvg")
fun setRatingAvg(view: TextView, ratingAvg : Double) {
    view.text = String.format("%.0f", ratingAvg)
}

@BindingAdapter("makeComma")
fun addCommaToPrice(view : TextView, price : Int) {
    view.text = CommonUtils.makeComma(price)
}

@BindingAdapter("setDate")
fun setDate(view : TextView, date : Date) {
    view.text = CommonUtils.getFormattedString(date)
}

@BindingAdapter("setOrderStatus")
fun setOrderStatus(view : TextView, data : LatestOrderResponse) {
    view.text = CommonUtils.isOrderCompleted(data)
}

@BindingAdapter("price")
fun bindOrderPrice(view: TextView, price: Int) {
    view.text = CommonUtils.makeComma(price)
}

@BindingAdapter("totalPrice")
fun bindOrderTotalPrice(view: TextView, price: Int) {
    view.text = "총 " + CommonUtils.makeComma(price)
}

@BindingAdapter(value = ["menuType", "menuCount"])
fun bindDetailMenuCount(view: TextView, menuType: String?, count: Int) {
    var type = if(menuType == "coffee") "잔" else "개"
    view.text = count.toString() + type
}