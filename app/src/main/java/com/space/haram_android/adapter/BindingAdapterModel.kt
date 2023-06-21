package com.space.haram_android.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.space.haram_android.common.data.response.home.data.NewsModel
import com.space.haram_android.ui.home.HomeNewsRecycler

object BindingAdapterModel {

    @JvmStatic
    @BindingAdapter("onKeyDown")
    fun setKeyDown(view: View, boolean: Boolean) {
        if (boolean){
            val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    @JvmStatic
    @BindingAdapter("setImage")
    fun setImage(imageView: ImageView, url: String?) {
        url.let {
            Glide.with(imageView.context)
                .load(url)
                .centerCrop()
                .into(imageView)
        }
    }

}