package com.space.mileage.adapter

import android.graphics.Color
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.space.mileage.R

@BindingAdapter("mileageType")
fun layoutType(
    textView: TextView,
    string: String
) {
    if (string == "조정") {
        textView.setTextColor(Color.parseColor("#4B81EE"))
    } else {
        textView.setTextColor(Color.parseColor("#707070"))
    }
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
                    string.contains("카페") ->
                        R.drawable.ic_mileage_coffee

                    string.contains("교학") ->
                        R.drawable.ic_mileage_student

                    string.contains("매점") ->
                        R.drawable.ic_mileage_food

                    string.contains("마트") ->
                        R.drawable.ic_mileage_food

                    string.contains("카페") ->
                        R.drawable.ic_mileage_coffee

                    else -> R.drawable.ic_mileage_etc
                }
            )
            .centerCrop()
            .into(imageView)
    }
}