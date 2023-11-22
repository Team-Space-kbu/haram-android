package com.space.core_ui.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


@BindingAdapter("imgUrl")
fun setImageUrl(imageView: ImageView, url: String) {
    url.let {
        Glide.with(imageView.context)
            .load(url)
            .centerCrop()
            .into(imageView)
    }
}