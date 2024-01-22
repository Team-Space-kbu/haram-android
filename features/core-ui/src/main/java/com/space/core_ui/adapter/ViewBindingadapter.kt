package com.space.core_ui.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.space.core_ui.DividerItemDecoration
import com.space.core_ui.R
import com.space.shared.data.LayoutType
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale


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
    }
}

@BindingAdapter("setDate")
fun setDate(
    textView: TextView,
    date: String? = "",
) {
    try {
        val outputFormat = date?.let { SimpleDateFormat("yyyy.MM.dd", Locale.getDefault()).parse(it) }
        textView.text = outputFormat?.toString() ?: "정보없음"
    } catch (e: Exception) {
        try {
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
            val parsedDateTime = LocalDateTime.parse(date, formatter)
            val outputFormat = DateTimeFormatter.ofPattern("yyyy.MM.dd")
            textView.text = parsedDateTime.format(outputFormat)
        } catch (e: Exception) {
            e.printStackTrace()
            textView.text = "정보없음"
        }
    }
}

@BindingAdapter("setDateTime")
fun setDateTime(
    textView: TextView,
    date: String? = "",
) {
    try {
        val outputFormat = date?.let { SimpleDateFormat("yyyy.MM.dd HH시 mm분", Locale.getDefault()).parse(it) }
        textView.text = outputFormat?.toString() ?: ""
    } catch (e: Exception) {
        try {
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
            val parsedDateTime = LocalDateTime.parse(date, formatter)
            val outputFormat = DateTimeFormatter.ofPattern("yyyy.MM.dd HH시 mm분")
            textView.text = parsedDateTime.format(outputFormat)
        } catch (e: Exception) {
            e.printStackTrace()
            textView.text = " "
        }
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