package com.ssafy.aongbucks_user.adapter

import android.view.View
import android.widget.RatingBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.ssafy.aongbucks_user.util.CommonUtils


@BindingAdapter("imageDrawable")
fun imageDrawable(view : View, img : String) {
//    val resId =

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