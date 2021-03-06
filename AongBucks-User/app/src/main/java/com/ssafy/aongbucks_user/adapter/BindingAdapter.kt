package com.ssafy.aongbucks_user.adapter

import android.util.Log
import android.view.View
import android.widget.Button
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

@BindingAdapter("rating")
fun bindRating(ratingBar : RatingBar, rating: Double) {
    ratingBar.rating = rating.toFloat()
}

@BindingAdapter("ratingAvg")
fun bindRatingAvg(view: TextView, ratingAvg : Double) {
    view.text = String.format("%.0f", ratingAvg)
}

@BindingAdapter("date")
fun bindDate(view : TextView, date : Date) {
    view.text = CommonUtils.getFormattedString(date)
}

@BindingAdapter("orderStatus")
fun bindOrderStatus(view : TextView, data : LatestOrderResponse) {
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

@BindingAdapter("countOrderText")
fun bindCountOrderText(view: Button, count: Int) {
    view.text = "${count}건 주문하기"
}

@BindingAdapter("orderState")
fun bindOrderState(view: TextView, state: Char) {
    view.text = if (state == 'Y') "제조 완료" else "접수 대기"
}