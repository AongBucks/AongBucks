package com.ssafy.aongbucks_user.adapter

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.ssafy.aongbucks_user.util.CommonUtils


private const val TAG = "BindingAdapter_싸피"

@BindingAdapter("imageDrawable")
fun imageDrawable(view : ImageView, img : String?) {
    val packageName = "com.ssafy.aongbucks_user"
    val imgName = img?.substring(0, img.length - 4) ?: "coffee1"
    Log.d(TAG, "imageDrawable: $imgName")
    val resId = view.resources.getIdentifier(imgName, "drawable", packageName)
    view.setImageResource(resId)
}

@BindingAdapter("setRating")
fun setRating(ratingBar : RatingBar, rating: Double) {
    ratingBar.rating = rating.toFloat()
}

@BindingAdapter("setRatingAvg")
fun setRatingAvg(view: TextView, ratingAvg : Double) {
    view.text = String.format("%.0lf", ratingAvg)
}

@BindingAdapter("makeComma")
fun addCommaToPrice(view : TextView, price : Int) {
    view.text = CommonUtils.makeComma(price)
}