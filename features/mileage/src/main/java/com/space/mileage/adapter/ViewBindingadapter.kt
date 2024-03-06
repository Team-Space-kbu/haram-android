package com.space.mileage.adapter

import android.graphics.Color
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.space.mileage.R
import java.text.DecimalFormat


val formatter = DecimalFormat("#,###")

@BindingAdapter("setPoint")
fun setPoint(
    textView: TextView,
    point: Int
) {
    if (point >= 0) {
        textView.setTextColor(Color.parseColor("#4B81EE"))
    } else {
        textView.setTextColor(Color.parseColor("#707070"))
    }
    textView.text = formatter.format(point)
}


@BindingAdapter("mileageType")
fun imageType(
    imageView: ImageView,
    string: String
) {
    string.let {
        Glide.with(imageView.context)
            .load(
                when {
                    string.contains("CAFE") ->
                        R.drawable.ic_mileage_coffee

                    string.contains("STUDENT") ->
                        R.drawable.ic_mileage_student

                    string.contains("MART") ->
                        R.drawable.ic_mileage_food

                    string.contains("GYM") ->
                        R.drawable.ic_mileage_gym

                    string.contains("BOOKSTORE") -> {
                        R.drawable.ic_mileage_book
                    }

                    else -> R.drawable.ic_mileage_etc
                }
            )
            .centerCrop()
            .into(imageView)
    }
}

