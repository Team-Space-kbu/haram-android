package com.space.core_ui.adapter

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.space.core_ui.DividerItemDecoration
import com.space.core_ui.R
import com.space.core_ui.util.dateToDateTime
import com.space.shared.data.LayoutType
import com.space.shared.util.formatToDate
import timber.log.Timber


@BindingAdapter("setImgUrl")
fun setImageUrl(
    imageView: ImageView,
    url: String?
) {
    url?.let {
        Glide.with(imageView.context)
            .load(url)
            .centerCrop()
            .into(imageView)
    }
}


@BindingAdapter("layoutType")
fun setLayoutType(
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

        else -> {
            recyclerView.layoutManager =
                LinearLayoutManager(recyclerView.context, RecyclerView.HORIZONTAL, false)
        }
    }
}

@BindingAdapter("setDate")
fun setDate(
    textView: TextView,
    date: String? = "",
) {
    try {
        date?.let {
            textView.text = formatToDate(date)
        }
    } catch (e: Exception) {
        Timber.d(e.message)
        textView.text = "정보 없음"
    }
}

@BindingAdapter("setDateTime")
fun setDateTime(
    textView: TextView,
    date: String? = "",
) {
    try {
        date?.let {
            textView.text = dateToDateTime(it)
        }
    } catch (e: Exception) {
        Timber.d(e.message)
        textView.text = "정보없음"
    }
}

@BindingAdapter("dividerItem")
fun dividerItemType(
    recyclerView: RecyclerView,
    type: Boolean
) {
    if (type) {
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