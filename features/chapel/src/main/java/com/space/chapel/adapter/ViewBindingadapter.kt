package com.space.chapel.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.space.chapel.R

@BindingAdapter("chapelType")
fun imageType(
    imageView: ImageView,
    string: String
) {
    string.let {
        Glide.with(imageView.context)
            .load(
                when {
                    string.contains("출석") ->
                        R.drawable.ic_chapel_attendance
                    else -> R.drawable.ic_chapel_perceive
                }
            )
            .centerCrop()
            .into(imageView)
    }
}