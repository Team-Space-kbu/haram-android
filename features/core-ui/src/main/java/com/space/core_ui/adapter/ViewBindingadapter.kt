package com.space.core_ui.adapter

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.space.core_ui.DividerItemDecoration
import com.space.core_ui.R
import com.space.shared.data.LayoutType


@BindingAdapter("setImgUrl")
fun setImageUrl(
    imageView: ImageView,
    url: String
) {
    url.let {
        Glide.with(imageView.context)
            .load(url)
            .centerCrop()
            .into(imageView)
    }
}

@BindingAdapter("keyDown")
fun View.lowerKeyboardOnClick(
    lower: Boolean
) {
    if (lower) {
        setOnClickListener {
            val inputMethodManager =
                context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
        }
    }
}

@BindingAdapter("layoutType")
fun layoutType(
    recyclerView: RecyclerView,
    type: LayoutType
) {
    recyclerView.setHasFixedSize(true)
    when (type) {
        LayoutType.HORIZONTAL -> {
            recyclerView.layoutManager =
                LinearLayoutManager(recyclerView.context, RecyclerView.HORIZONTAL, false)
        }

        LayoutType.VERTICAL -> {
            recyclerView.layoutManager =
                LinearLayoutManager(recyclerView.context, RecyclerView.VERTICAL, false)
        }
    }
}

@BindingAdapter("dividerItem")
fun dividerItemType(
    recyclerView: RecyclerView,
    type: Boolean
) {
    if (type){
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                R.drawable.line_divider,
                5,
                5
            )
        )
    }
}